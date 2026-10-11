package app.areyouokay

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.ServerSocket
import kotlin.concurrent.thread

/** Checks how answers from a link are classified, against a tiny local web server. */
class CheckInSendTest {
    private lateinit var server: ServerSocket
    @Volatile private var lastBody = ""
    private val base get() = "http://127.0.0.1:${server.localPort}"
    private val codes = mapOf("/ok" to 200, "/missing" to 404, "/busy" to 503, "/slow-down" to 429)

    @Before fun start() {
        server = ServerSocket(0)
        thread(isDaemon = true) {
            while (!server.isClosed) {
                val socket = try { server.accept() } catch (e: Exception) { break }
                socket.use { s ->
                    val input = s.getInputStream().bufferedReader()
                    val path = input.readLine().split(" ")[1]
                    var length = 0
                    while (true) {
                        val line = input.readLine()
                        if (line.isNullOrEmpty()) break
                        if (line.startsWith("Content-Length:", ignoreCase = true)) length = line.substringAfter(":").trim().toInt()
                    }
                    lastBody = CharArray(length).also { input.read(it) }.concatToString()
                    val code = codes[path] ?: 404
                    s.getOutputStream().write("HTTP/1.1 $code X\r\nContent-Length: 0\r\nConnection: close\r\n\r\n".toByteArray())
                }
            }
        }
    }

    @After fun stop() = server.close()

    @Test fun successSendsTheMessage() {
        assertEquals(CheckIn.Result.SENT, CheckIn.send("$base/ok", "Jean checked in: I'm OK"))
        assertEquals("Jean checked in: I'm OK", lastBody)
    }

    @Test fun wrongLinkIsNotRetried() {
        assertEquals(CheckIn.Result.BROKEN_LINK, CheckIn.send("$base/missing", "x"))
        assertEquals(CheckIn.Result.BROKEN_LINK, CheckIn.send("not a url", "x"))
    }

    @Test fun busyOrOfflineIsRetried() {
        assertEquals(CheckIn.Result.RETRY, CheckIn.send("$base/busy", "x"))
        assertEquals(CheckIn.Result.RETRY, CheckIn.send("$base/slow-down", "x"))
        assertEquals(CheckIn.Result.RETRY, CheckIn.send("http://127.0.0.1:1/nothing-listens", "x"))
    }
}

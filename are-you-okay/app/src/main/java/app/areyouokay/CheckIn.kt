package app.areyouokay

import android.content.Context
import java.net.HttpURLConnection
import java.net.URL
import java.text.DateFormat
import java.util.Date

/** The one thing this app does: record "I'm OK" and tell the family. */
object CheckIn {
    fun record(context: Context) {
        val prefs = Prefs(context)
        val now = System.currentTimeMillis()
        prefs.lastCheckInMillis = now
        // A check-in ends a vacation pause early.
        prefs.pausedUntilMillis = 0L

        Reminders.dismissForToday(context)
        OkWidget.updateAll(context)

        val links = prefs.links
        if (links.isNotEmpty()) {
            val who = prefs.name.ifEmpty { "Your family member" }
            val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(now))
            synchronized(this) {
                prefs.pendingMessage = "$who checked in: I'm OK ($time)"
                prefs.pendingLinks = links.toSet()
                prefs.brokenLinks = emptySet()
            }
            SendJobService.schedule(context)
        }
    }

    /**
     * "I need help": an urgent alert to every link. Healthchecks.io gets its /fail signal,
     * which alerts the family immediately instead of waiting for the missed check-in.
     */
    fun requestHelp(context: Context) {
        val prefs = Prefs(context)
        val now = System.currentTimeMillis()
        val who = prefs.name.ifEmpty { "Your family member" }
        val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(now))
        synchronized(this) {
            prefs.helpAtMillis = now
            prefs.helpMessage = "$who pressed I NEED HELP at $time. Please call them now."
            prefs.helpPendingLinks = prefs.links.toSet()
        }
        SendJobService.schedule(context, help = true)
    }

    enum class Result { SENT, RETRY, BROKEN_LINK }

    /** POSTs [message] as plain text. Works for Healthchecks.io ping URLs and ntfy topics alike. Blocking. */
    fun post(link: String, message: String): Boolean = send(link, message) == Result.SENT

    /**
     * Like [post], but tells "try again later" (offline, server busy) apart from
     * "this link will never work" (wrong address, deleted check), so a bad link
     * isn't retried forever while the screen blames the internet.
     */
    fun send(link: String, message: String, headers: Map<String, String> = emptyMap()): Result = try {
        val conn = URL(link).openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.connectTimeout = 15_000
        conn.readTimeout = 15_000
        conn.doOutput = true
        conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8")
        headers.forEach { (k, v) -> conn.setRequestProperty(k, v) }
        conn.outputStream.use { it.write(message.toByteArray()) }
        val code = conn.responseCode
        // Healthchecks answers a mistyped check with HTTP 200 "OK (not found)", so read the reply.
        val body = if (code in 200..299) conn.inputStream.bufferedReader().use { r -> CharArray(200).let { buf -> String(buf, 0, maxOf(0, r.read(buf))) } } else ""
        conn.disconnect()
        when (code) {
            in 200..299 -> if (body.contains("not found", ignoreCase = true)) Result.BROKEN_LINK else Result.SENT
            408, 425, 429, in 500..599 -> Result.RETRY
            else -> Result.BROKEN_LINK
        }
    } catch (e: java.net.MalformedURLException) {
        Result.BROKEN_LINK
    } catch (e: java.net.UnknownHostException) {
        // Offline phones also fail DNS, so this is treated as "try again".
        Result.RETRY
    } catch (e: Exception) {
        // Timeouts, no connection, TLS hiccups: all worth trying again later.
        Result.RETRY
    }
}

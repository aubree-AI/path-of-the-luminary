package app.areyouokay

import android.content.Context
import android.util.Log
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

        val links = prefs.links
        if (links.isNotEmpty()) {
            val who = prefs.name.ifEmpty { "Your family member" }
            val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(now))
            prefs.pendingMessage = "$who checked in: I'm OK ($time)"
            prefs.pendingLinks = links.toSet()
            SendJobService.schedule(context)
        }
    }

    /** POSTs [message] as plain text. Works for Healthchecks.io ping URLs and ntfy topics alike. Blocking. */
    fun post(link: String, message: String): Boolean = try {
        val conn = URL(link).openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.connectTimeout = 15_000
        conn.readTimeout = 15_000
        conn.doOutput = true
        conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8")
        conn.outputStream.use { it.write(message.toByteArray()) }
        val code = conn.responseCode
        conn.disconnect()
        code in 200..299
    } catch (e: Exception) {
        Log.w("AreYouOkay", "Could not reach $link", e)
        false
    }
}

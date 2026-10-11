package app.areyouokay

import android.content.Context
import java.time.ZoneId

/** All app state lives in one small SharedPreferences file. Nothing leaves the phone except check-in messages. */
class Prefs(context: Context) {
    private val p = context.getSharedPreferences("are_you_okay", Context.MODE_PRIVATE)

    var name: String
        get() = p.getString("name", "") ?: ""
        set(v) = p.edit().putString("name", v.trim()).apply()

    var reminderHour: Int
        get() = p.getInt("reminder_hour", 9)
        set(v) = p.edit().putInt("reminder_hour", v).apply()

    var reminderMinute: Int
        get() = p.getInt("reminder_minute", 0)
        set(v) = p.edit().putInt("reminder_minute", v).apply()

    /** Where each check-in is sent: Healthchecks.io ping URLs, ntfy topic URLs, or any URL that accepts a POST. */
    var links: List<String>
        get() = (p.getString("links", "") ?: "").lines().map { it.trim() }.filter { it.isNotEmpty() }
        set(v) = p.edit().putString("links", v.joinToString("\n")).apply()

    var lastCheckInMillis: Long
        get() = p.getLong("last_check_in", 0L)
        set(v) = p.edit().putLong("last_check_in", v).apply()

    /** Reminders are paused (vacation) until this time. */
    var pausedUntilMillis: Long
        get() = p.getLong("paused_until", 0L)
        set(v) = p.edit().putLong("paused_until", v).apply()

    /** Links that still need this check-in delivered (kept until the phone is back online). */
    var pendingLinks: Set<String>
        get() = p.getStringSet("pending_links", emptySet()) ?: emptySet()
        set(v) = p.edit().putStringSet("pending_links", v).apply()

    var pendingMessage: String
        get() = p.getString("pending_message", "") ?: ""
        set(v) = p.edit().putString("pending_message", v).apply()

    /** Links that answered "this will never work" on the last check-in (e.g. 404). */
    var brokenLinks: Set<String>
        get() = p.getStringSet("broken_links", emptySet()) ?: emptySet()
        set(v) = p.edit().putStringSet("broken_links", v).apply()

    /** "I need help": when it was pressed, and which links still need the alert. */
    var helpAtMillis: Long
        get() = p.getLong("help_at", 0L)
        set(v) = p.edit().putLong("help_at", v).apply()

    var helpPendingLinks: Set<String>
        get() = p.getStringSet("help_pending_links", emptySet()) ?: emptySet()
        set(v) = p.edit().putStringSet("help_pending_links", v).apply()

    var helpMessage: String
        get() = p.getString("help_message", "") ?: ""
        set(v) = p.edit().putString("help_message", v).apply()

    var lastDeliveredMillis: Long
        get() = p.getLong("last_delivered", 0L)
        set(v) = p.edit().putLong("last_delivered", v).apply()

    val isPaused: Boolean get() = System.currentTimeMillis() < pausedUntilMillis

    fun checkedInToday(): Boolean {
        val last = lastCheckInMillis
        return last != 0L && Schedule.isSameDay(last, System.currentTimeMillis(), ZoneId.systemDefault())
    }
}

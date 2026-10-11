package app.areyouokay

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/** Alarms are wiped on restart, app update, and clock changes, so put the daily one back. */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action !in HANDLED) return
        Reminders.createChannel(context)
        Reminders.scheduleNextDaily(context)
        if (Prefs(context).pendingLinks.isNotEmpty()) SendJobService.schedule(context)
        if (Prefs(context).helpPendingLinks.isNotEmpty()) SendJobService.schedule(context, help = true)
    }

    private companion object {
        val HANDLED = setOf(
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_MY_PACKAGE_REPLACED,
            Intent.ACTION_TIME_CHANGED,
            Intent.ACTION_TIMEZONE_CHANGED,
            "android.app.action.SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED",
        )
    }
}

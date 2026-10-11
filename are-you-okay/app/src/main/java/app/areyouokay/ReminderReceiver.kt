package app.areyouokay

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (val action = intent.action) {
            Reminders.ACTION_CHECK_IN -> {
                CheckIn.record(context)
                Reminders.showThankYou(context)
            }
            Reminders.ACTION_DAILY, Reminders.ACTION_REPEAT ->
                Reminders.onAlarm(context, action, intent.getIntExtra(Reminders.EXTRA_COUNT, 0))
        }
    }
}

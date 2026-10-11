package app.areyouokay

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.app.Notification
import android.os.Build
import java.util.Calendar

/**
 * Daily reminder: one notification at the chosen time, then repeats every
 * [REPEAT_MINUTES] minutes (up to [EXTRA_REMINDERS] more times) until she taps "I'm OK".
 *
 * Alerting the family about a missed check-in is NOT done here: the phone could be
 * off, broken, or out of battery. That job belongs to a server that notices the
 * check-in did not arrive (see README: Healthchecks.io).
 */
object Reminders {
    private const val CHANNEL_ID = "daily_check_in"
    private const val NOTIFICATION_ID = 1
    const val REPEAT_MINUTES = 15
    const val EXTRA_REMINDERS = 3

    const val ACTION_DAILY = "app.areyouokay.DAILY"
    const val ACTION_REPEAT = "app.areyouokay.REPEAT"
    const val ACTION_CHECK_IN = "app.areyouokay.CHECK_IN"
    const val EXTRA_COUNT = "count"

    fun createChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_HIGH,
        ).apply {
            description = context.getString(R.string.channel_description)
            enableVibration(true)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }
        context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    /** (Re)schedule the next daily reminder. Safe to call any time. */
    fun scheduleNextDaily(context: Context) {
        val prefs = Prefs(context)
        val next = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, prefs.reminderHour)
            set(Calendar.MINUTE, prefs.reminderMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) add(Calendar.DAY_OF_YEAR, 1)
        }
        setAlarm(context, next.timeInMillis, alarmIntent(context, ACTION_DAILY, 0))
    }

    /** Called by [ReminderReceiver] when an alarm fires. */
    fun onAlarm(context: Context, action: String, count: Int) {
        val prefs = Prefs(context)
        if (action == ACTION_DAILY) scheduleNextDaily(context)
        if (prefs.isPaused || prefs.checkedInToday()) return

        showNotification(context, isRepeat = count > 0)
        if (count < EXTRA_REMINDERS) {
            val at = System.currentTimeMillis() + REPEAT_MINUTES * 60_000L
            setAlarm(context, at, alarmIntent(context, ACTION_REPEAT, count + 1))
        }
    }

    /** After a check-in: clear the notification and any pending repeats. */
    fun dismissForToday(context: Context) {
        context.getSystemService(NotificationManager::class.java).cancel(NOTIFICATION_ID)
        context.getSystemService(AlarmManager::class.java)
            .cancel(alarmIntent(context, ACTION_REPEAT, 0))
    }

    private fun showNotification(context: Context, isRepeat: Boolean) {
        val open = PendingIntent.getActivity(
            context, 0,
            Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val checkIn = PendingIntent.getBroadcast(
            context, 2,
            Intent(context, ReminderReceiver::class.java).setAction(ACTION_CHECK_IN),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val okAction = Notification.Action.Builder(null, context.getString(R.string.im_ok), checkIn).build()

        val notification = Notification.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(context.getString(if (isRepeat) R.string.reminder_title_repeat else R.string.reminder_title))
            .setContentText(context.getString(R.string.reminder_text))
            .setCategory(Notification.CATEGORY_REMINDER)
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setContentIntent(open)
            .addAction(okAction)
            .setOngoing(true)
            .build()

        context.getSystemService(NotificationManager::class.java).notify(NOTIFICATION_ID, notification)
    }

    private fun alarmIntent(context: Context, action: String, count: Int): PendingIntent {
        val intent = Intent(context, ReminderReceiver::class.java)
            .setAction(action)
            .putExtra(EXTRA_COUNT, count)
        val requestCode = if (action == ACTION_DAILY) 0 else 1
        return PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun setAlarm(context: Context, atMillis: Long, intent: PendingIntent) {
        val am = context.getSystemService(AlarmManager::class.java)
        val canExact = Build.VERSION.SDK_INT < Build.VERSION_CODES.S || am.canScheduleExactAlarms()
        if (canExact) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, atMillis, intent)
        } else {
            // Still fires, but Android may delay it by several minutes.
            am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, atMillis, intent)
        }
    }
}

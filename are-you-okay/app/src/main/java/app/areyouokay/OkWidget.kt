package app.areyouokay

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import java.text.DateFormat
import java.util.Date

/** Home-screen "I'm OK" button: check in without opening the app. */
class OkWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        ids.forEach { manager.updateAppWidget(it, views(context)) }
    }

    companion object {
        /** Call whenever the check-in state changes (check-in, new day). */
        fun updateAll(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            val ids = manager.getAppWidgetIds(ComponentName(context, OkWidget::class.java))
            if (ids.isNotEmpty()) ids.forEach { manager.updateAppWidget(it, views(context)) }
        }

        private fun views(context: Context): RemoteViews {
            val prefs = Prefs(context)
            val v = RemoteViews(context.packageName, R.layout.widget_ok)
            if (prefs.checkedInToday()) {
                val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(prefs.lastCheckInMillis))
                v.setInt(R.id.widget_root, "setBackgroundResource", R.drawable.widget_done_bg)
                v.setImageViewResource(R.id.widget_icon, R.drawable.ic_check_done)
                v.setTextViewText(R.id.widget_label, context.getString(R.string.thank_you))
                v.setTextColor(R.id.widget_label, context.getColor(R.color.on_done_container))
                v.setTextViewText(R.id.widget_sub, context.getString(R.string.widget_done_at, time))
                v.setViewVisibility(R.id.widget_sub, View.VISIBLE)
                v.setContentDescription(R.id.widget_root, context.getString(R.string.checked_in_at, time))
                // Already done: a tap just opens the app.
                v.setOnClickPendingIntent(R.id.widget_root, openApp(context))
            } else {
                v.setInt(R.id.widget_root, "setBackgroundResource", R.drawable.widget_ok_bg)
                v.setImageViewResource(R.id.widget_icon, R.drawable.ic_heart_white)
                v.setTextViewText(R.id.widget_label, context.getString(R.string.im_ok))
                v.setTextColor(R.id.widget_label, context.getColor(R.color.on_ok_green))
                v.setViewVisibility(R.id.widget_sub, View.GONE)
                v.setContentDescription(R.id.widget_root, context.getString(R.string.im_ok_description))
                v.setOnClickPendingIntent(R.id.widget_root, Reminders.checkInIntent(context))
            }
            return v
        }

        private fun openApp(context: Context): PendingIntent = PendingIntent.getActivity(
            context, 3,
            Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }
}

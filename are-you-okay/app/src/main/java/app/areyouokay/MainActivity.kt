package app.areyouokay

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.HapticFeedbackConstants
import java.util.Calendar

/** The whole app for the person checking in: one big button. */
class MainActivity : Activity() {
    private lateinit var prefs: Prefs
    private lateinit var screen: MainScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = Prefs(this)
        screen = MainScreen(findViewById(android.R.id.content))

        screen.okButton.setOnClickListener { button ->
            button.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            CheckIn.record(this)
            // A small "press" so the change from green button to thank-you card feels deliberate.
            button.animate().scaleX(0.96f).scaleY(0.96f).setDuration(90).withEndAction {
                refresh()
                button.animate().scaleX(1f).scaleY(1f).setDuration(160).start()
            }.start()
        }
        findViewById<android.view.View>(R.id.settings_button).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        Reminders.createChannel(this)
        Reminders.scheduleNextDaily(this)
        if (Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        val checkedIn = prefs.checkedInToday()
        val delivery = when {
            prefs.links.isEmpty() -> MainScreen.Delivery.NOT_SET_UP
            prefs.pendingLinks.isNotEmpty() -> MainScreen.Delivery.WAITING
            checkedIn && prefs.lastDeliveredMillis >= prefs.lastCheckInMillis -> MainScreen.Delivery.SENT
            else -> MainScreen.Delivery.NONE
        }
        val reminderAt = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, prefs.reminderHour)
            set(Calendar.MINUTE, prefs.reminderMinute)
        }.timeInMillis
        screen.bind(
            MainScreen.State(
                name = prefs.name,
                now = System.currentTimeMillis(),
                checkedInAt = if (checkedIn) prefs.lastCheckInMillis else null,
                pausedUntil = if (prefs.isPaused) prefs.pausedUntilMillis else null,
                reminderAt = reminderAt,
                delivery = delivery,
            ),
        )
    }
}

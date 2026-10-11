package app.areyouokay

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.HapticFeedbackConstants
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar

/** The whole app for the person checking in: one big button. */
class MainActivity : Activity() {
    companion object {
        const val ACTION_SHORTCUT = "app.areyouokay.CHECK_IN_SHORTCUT"
    }

    private lateinit var prefs: Prefs
    private lateinit var screen: MainScreen
    private val handler = Handler(Looper.getMainLooper())
    private val refresher = Runnable { refresh() }

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
        val openSettings = { _: android.view.View -> startActivity(Intent(this, SettingsActivity::class.java)) }
        findViewById<android.view.View>(R.id.settings_button).setOnClickListener(openSettings)
        screen.statusCard.setOnClickListener(openSettings)
        screen.helpButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.help_confirm_title)
                .setMessage(R.string.help_confirm_text)
                .setPositiveButton(R.string.help_confirm_yes) { _, _ ->
                    CheckIn.requestHelp(this)
                    refresh()
                }
                .setNegativeButton(R.string.help_confirm_no, null)
                .show()
        }

        Reminders.createChannel(this)
        Reminders.scheduleNextDaily(this)
        if (Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        // Long-press app icon > "I'm OK" lands here.
        if (intent?.action == ACTION_SHORTCUT) {
            intent.action = null
            if (!prefs.checkedInToday()) CheckIn.record(this)
        }
        refresh()
    }

    private fun refresh() {
        val checkedIn = prefs.checkedInToday()
        val delivery = when {
            prefs.links.isEmpty() -> MainScreen.Delivery.NOT_SET_UP
            checkedIn && prefs.brokenLinks.isNotEmpty() -> MainScreen.Delivery.BROKEN_LINK
            // Give the first attempt a minute before saying "no internet".
            prefs.pendingLinks.isNotEmpty() && System.currentTimeMillis() - prefs.lastCheckInMillis < 60_000 ->
                MainScreen.Delivery.SENDING
            prefs.pendingLinks.isNotEmpty() -> MainScreen.Delivery.WAITING
            checkedIn && prefs.lastDeliveredMillis >= prefs.lastCheckInMillis -> MainScreen.Delivery.SENT
            else -> MainScreen.Delivery.NONE
        }
        val reminderAt = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, prefs.reminderHour)
            set(Calendar.MINUTE, prefs.reminderMinute)
        }.timeInMillis
        val helpAt = prefs.helpAtMillis.takeIf { it != 0L && Schedule.isSameDay(it, System.currentTimeMillis(), ZoneId.systemDefault()) }
        val helpDelivered = prefs.helpPendingLinks.isEmpty()
        screen.bind(
            MainScreen.State(
                name = prefs.name,
                now = System.currentTimeMillis(),
                checkedInAt = if (checkedIn) prefs.lastCheckInMillis else null,
                pausedUntil = if (prefs.isPaused) prefs.pausedUntilMillis else null,
                reminderAt = reminderAt,
                delivery = delivery,
                remindersOff = !getSystemService(NotificationManager::class.java).areNotificationsEnabled(),
                helpAt = helpAt,
                helpDelivered = helpDelivered,
            ),
        )
        // While something is still being sent, look again in a few seconds so the screen
        // changes to "Your family has been told" on its own. Otherwise wake up at midnight,
        // so an app left open overnight switches back to "I'm OK".
        handler.removeCallbacks(refresher)
        val sending = prefs.pendingLinks.isNotEmpty() || (helpAt != null && !helpDelivered)
        val now = ZonedDateTime.now()
        val delay = if (sending) 3_000L else Duration.between(now, Schedule.nextMidnight(now)).toMillis() + 1_000
        handler.postDelayed(refresher, delay)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(refresher)
    }
}

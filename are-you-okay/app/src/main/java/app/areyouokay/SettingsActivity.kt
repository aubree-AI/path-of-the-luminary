package app.areyouokay

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationManager
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.text.format.DateFormat as AndroidDateFormat
import android.view.View
import java.util.Calendar

class SettingsActivity : Activity() {
    private lateinit var prefs: Prefs
    private lateinit var screen: SettingsScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        prefs = Prefs(this)
        screen = SettingsScreen(findViewById(android.R.id.content))
        screen.name.setText(prefs.name)
        screen.links.setText(prefs.links.joinToString("\n"))

        findViewById<View>(R.id.back_button).setOnClickListener { finish() }
        findViewById<View>(R.id.time_button).setOnClickListener {
            TimePickerDialog(this, { _, hour, minute ->
                prefs.reminderHour = hour
                prefs.reminderMinute = minute
                Reminders.scheduleNextDaily(this)
                refresh()
            }, prefs.reminderHour, prefs.reminderMinute, AndroidDateFormat.is24HourFormat(this)).show()
        }
        mapOf(R.id.pause_1 to 1, R.id.pause_3 to 3, R.id.pause_7 to 7).forEach { (id, days) ->
            findViewById<View>(id).setOnClickListener { pause(days) }
        }
        findViewById<View>(R.id.resume_button).setOnClickListener {
            prefs.pausedUntilMillis = 0L
            refresh()
        }
        findViewById<View>(R.id.test_button).setOnClickListener {
            save()
            sendTest()
        }
        screen.onFix(R.id.row_notifications) {
            startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).putExtra(Settings.EXTRA_APP_PACKAGE, packageName))
        }
        screen.onFix(R.id.row_battery) { askBatteryExemption() }
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    override fun onPause() {
        super.onPause()
        save()
    }

    private fun save() {
        prefs.name = screen.name.text.toString()
        prefs.links = screen.links.text.toString().lines()
    }

    private fun pause(days: Int) {
        prefs.pausedUntilMillis = System.currentTimeMillis() + days * 24L * 60 * 60 * 1000
        Reminders.dismissForToday(this)
        refresh()
    }

    private fun refresh() {
        val reminderAt = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, prefs.reminderHour)
            set(Calendar.MINUTE, prefs.reminderMinute)
        }.timeInMillis
        screen.bind(
            SettingsScreen.State(
                reminderAt = reminderAt,
                pausedUntil = if (prefs.isPaused) prefs.pausedUntilMillis else null,
                notificationsOk = notificationsAllowed(),
                batteryOk = getSystemService(PowerManager::class.java).isIgnoringBatteryOptimizations(packageName),
            ),
        )
    }

    private fun notificationsAllowed(): Boolean =
        (Build.VERSION.SDK_INT < 33 ||
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) &&
            getSystemService(NotificationManager::class.java).areNotificationsEnabled()

    @SuppressLint("BatteryLife")
    private fun askBatteryExemption() {
        startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:$packageName")))
    }

    /** Sends a test message right now and shows the result on screen (not a toast that disappears). */
    private fun sendTest() {
        val links = prefs.links
        val result = screen.testResult
        result.visibility = View.VISIBLE
        if (links.isEmpty()) {
            result.setText(R.string.test_no_links)
            return
        }
        result.setText(R.string.test_sending)
        val who = prefs.name.ifEmpty { "Your family member" }
        Thread {
            val ok = links.count { CheckIn.post(it, "Test message from $who's Are You Okay app") }
            runOnUiThread {
                result.text = if (ok == links.size) {
                    getString(R.string.test_all_ok)
                } else {
                    resources.getQuantityString(R.plurals.test_some_failed, links.size, ok, links.size)
                }
            }
        }.start()
    }
}

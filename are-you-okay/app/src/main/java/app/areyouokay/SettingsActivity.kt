package app.areyouokay

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
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
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormat
import java.util.Calendar
import java.util.Date

class SettingsActivity : Activity() {
    private lateinit var prefs: Prefs
    private lateinit var nameField: EditText
    private lateinit var linksField: EditText
    private lateinit var timeButton: Button
    private lateinit var pauseDaysField: EditText
    private lateinit var pauseStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        prefs = Prefs(this)

        nameField = findViewById(R.id.name)
        linksField = findViewById(R.id.links)
        timeButton = findViewById(R.id.time_button)
        pauseDaysField = findViewById(R.id.pause_days)
        pauseStatus = findViewById(R.id.pause_status)

        nameField.setText(prefs.name)
        linksField.setText(prefs.links.joinToString("\n"))

        timeButton.setOnClickListener {
            TimePickerDialog(this, { _, hour, minute ->
                prefs.reminderHour = hour
                prefs.reminderMinute = minute
                Reminders.scheduleNextDaily(this)
                refresh()
            }, prefs.reminderHour, prefs.reminderMinute, AndroidDateFormat.is24HourFormat(this)).show()
        }

        findViewById<Button>(R.id.pause_button).setOnClickListener {
            val days = pauseDaysField.text.toString().toIntOrNull()?.coerceIn(1, 365) ?: 1
            prefs.pausedUntilMillis = System.currentTimeMillis() + days * 24L * 60 * 60 * 1000
            Reminders.dismissForToday(this)
            refresh()
        }
        findViewById<Button>(R.id.resume_button).setOnClickListener {
            prefs.pausedUntilMillis = 0L
            refresh()
        }

        findViewById<Button>(R.id.test_button).setOnClickListener {
            save()
            sendTest()
        }
        findViewById<Button>(R.id.notifications_button).setOnClickListener { fixNotifications() }
        findViewById<Button>(R.id.battery_button).setOnClickListener { askBatteryExemption() }
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
        prefs.name = nameField.text.toString()
        prefs.links = linksField.text.toString().lines()
    }

    private fun refresh() {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, prefs.reminderHour)
            set(Calendar.MINUTE, prefs.reminderMinute)
        }
        timeButton.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(cal.time)

        pauseStatus.text = if (prefs.isPaused) {
            getString(R.string.paused_until, DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date(prefs.pausedUntilMillis)))
        } else {
            getString(R.string.not_paused)
        }

        findViewById<Button>(R.id.notifications_button).visibility =
            if (notificationsAllowed()) View.GONE else View.VISIBLE
        findViewById<Button>(R.id.battery_button).visibility =
            if (getSystemService(PowerManager::class.java).isIgnoringBatteryOptimizations(packageName)) View.GONE else View.VISIBLE
    }

    private fun notificationsAllowed(): Boolean =
        (Build.VERSION.SDK_INT < 33 ||
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) &&
            getSystemService(android.app.NotificationManager::class.java).areNotificationsEnabled()

    private fun fixNotifications() {
        startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).putExtra(Settings.EXTRA_APP_PACKAGE, packageName))
    }

    @SuppressLint("BatteryLife")
    private fun askBatteryExemption() {
        startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:$packageName")))
    }

    /** Sends a test message right now and reports how many links answered. */
    private fun sendTest() {
        val links = prefs.links
        if (links.isEmpty()) {
            Toast.makeText(this, R.string.test_no_links, Toast.LENGTH_LONG).show()
            return
        }
        val who = prefs.name.ifEmpty { "Your family member" }
        Thread {
            val ok = links.count { CheckIn.post(it, "Test message from $who's Are You Okay app") }
            runOnUiThread {
                Toast.makeText(this, getString(R.string.test_result, ok, links.size), Toast.LENGTH_LONG).show()
            }
        }.start()
    }
}

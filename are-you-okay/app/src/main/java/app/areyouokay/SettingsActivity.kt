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
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.text.DateFormat
import java.util.Calendar
import java.util.Date

class SettingsActivity : Activity() {
    private lateinit var prefs: Prefs
    private lateinit var nameField: EditText
    private lateinit var linksField: EditText
    private lateinit var testResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        prefs = Prefs(this)

        nameField = findViewById(R.id.name)
        linksField = findViewById(R.id.links)
        testResult = findViewById(R.id.test_result)
        nameField.setText(prefs.name)
        linksField.setText(prefs.links.joinToString("\n"))
        findViewById<TextView>(R.id.time_help).text =
            getString(R.string.time_help, Reminders.REPEAT_MINUTES, Reminders.EXTRA_REMINDERS)

        findViewById<View>(R.id.back_button).setOnClickListener { finish() }
        findViewById<Button>(R.id.time_button).setOnClickListener {
            TimePickerDialog(this, { _, hour, minute ->
                prefs.reminderHour = hour
                prefs.reminderMinute = minute
                Reminders.scheduleNextDaily(this)
                refresh()
            }, prefs.reminderHour, prefs.reminderMinute, AndroidDateFormat.is24HourFormat(this)).show()
        }

        mapOf(R.id.pause_1 to 1, R.id.pause_3 to 3, R.id.pause_7 to 7).forEach { (id, days) ->
            findViewById<Button>(id).setOnClickListener { pause(days) }
        }
        findViewById<Button>(R.id.resume_button).setOnClickListener {
            prefs.pausedUntilMillis = 0L
            refresh()
        }
        findViewById<Button>(R.id.test_button).setOnClickListener {
            save()
            sendTest()
        }
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

    private fun pause(days: Int) {
        prefs.pausedUntilMillis = System.currentTimeMillis() + days * 24L * 60 * 60 * 1000
        Reminders.dismissForToday(this)
        refresh()
    }

    private fun refresh() {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, prefs.reminderHour)
            set(Calendar.MINUTE, prefs.reminderMinute)
        }
        findViewById<TextView>(R.id.time_value).text = DateFormat.getTimeInstance(DateFormat.SHORT).format(cal.time)

        val paused = prefs.isPaused
        findViewById<TextView>(R.id.pause_status).text = if (paused) {
            getString(R.string.paused_until, DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date(prefs.pausedUntilMillis)))
        } else {
            getString(R.string.not_paused)
        }
        findViewById<View>(R.id.pause_choices).visibility = if (paused) View.GONE else View.VISIBLE
        findViewById<View>(R.id.resume_button).visibility = if (paused) View.VISIBLE else View.GONE

        checkRow(
            R.id.row_notifications, notificationsAllowed(),
            R.string.row_notifications_on, R.string.row_notifications_off,
        ) {
            startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).putExtra(Settings.EXTRA_APP_PACKAGE, packageName))
        }
        checkRow(
            R.id.row_battery, getSystemService(PowerManager::class.java).isIgnoringBatteryOptimizations(packageName),
            R.string.row_battery_on, R.string.row_battery_off,
        ) { askBatteryExemption() }
    }

    private fun checkRow(id: Int, ok: Boolean, onText: Int, offText: Int, fix: () -> Unit) {
        val row = findViewById<View>(id)
        val color = getColor(if (ok) R.color.ok_green else R.color.on_warning_container)
        row.findViewById<ImageView>(R.id.row_icon).apply {
            setImageResource(if (ok) R.drawable.ic_check_circle else R.drawable.ic_warning)
            setColorFilter(color)
        }
        row.findViewById<TextView>(R.id.row_label).setText(if (ok) onText else offText)
        row.findViewById<Button>(R.id.row_action).apply {
            visibility = if (ok) View.GONE else View.VISIBLE
            setOnClickListener { fix() }
        }
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
        testResult.visibility = View.VISIBLE
        if (links.isEmpty()) {
            testResult.setText(R.string.test_no_links)
            return
        }
        testResult.setText(R.string.test_sending)
        val who = prefs.name.ifEmpty { "Your family member" }
        Thread {
            val ok = links.count { CheckIn.post(it, "Test message from $who's Are You Okay app") }
            runOnUiThread {
                testResult.text = if (ok == links.size) {
                    getString(R.string.test_all_ok)
                } else {
                    getString(R.string.test_some_failed, ok, links.size)
                }
            }
        }.start()
    }
}

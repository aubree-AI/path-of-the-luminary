package app.areyouokay

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.NotificationManager
import android.app.TimePickerDialog
import android.content.ClipboardManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
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
        screen.showLinks(screen.links.text.toString(), prefs.brokenLinks)
        screen.links.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) = screen.showLinks(s.toString(), prefs.brokenLinks)
        })
        handleShare(intent)

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
        findViewById<View>(R.id.paste_button).setOnClickListener {
            val clip = getSystemService(ClipboardManager::class.java).primaryClip
            val text = clip?.takeIf { it.itemCount > 0 }?.getItemAt(0)?.coerceToText(this)?.toString().orEmpty()
            if (!addLinks(text)) {
                screen.testResult.visibility = View.VISIBLE
                screen.testResult.setText(R.string.paste_nothing)
            }
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

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleShare(intent)
    }

    /**
     * "Share" from the messages app lands here with the text of the message. Any app can
     * share to us, so ask before adding: a link decides who receives her check-ins.
     */
    private fun handleShare(intent: Intent?) {
        if (intent?.action != Intent.ACTION_SEND) return
        val text = intent.getStringExtra(Intent.EXTRA_TEXT).orEmpty()
        intent.action = null
        val found = Links.extract(text)
        if (found.isEmpty()) {
            screen.testResult.visibility = View.VISIBLE
            screen.testResult.setText(R.string.share_no_link)
            return
        }
        AlertDialog.Builder(this)
            .setTitle(R.string.share_confirm_title)
            .setMessage(getString(R.string.share_confirm_text, found.joinToString("\n") { "• " + Links.host(it) }))
            .setPositiveButton(R.string.share_confirm_yes) { _, _ -> addLinks(text) }
            .setNegativeButton(R.string.share_confirm_no, null)
            .show()
    }

    /** Adds any links found in [text] to the list. Returns false if there were none. */
    private fun addLinks(text: String): Boolean {
        val found = Links.extract(text)
        if (found.isEmpty()) return false
        val merged = (screen.links.text.toString().lines().map { it.trim() }.filter { it.isNotEmpty() } + found).distinct()
        screen.links.setText(merged.joinToString("\n"))
        save()
        screen.testResult.visibility = View.VISIBLE
        screen.testResult.setText(R.string.links_added)
        return true
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
        // "ntfy.sh/mom" becomes "https://ntfy.sh/mom"; anything unrecognisable is kept so it can be fixed.
        prefs.links = screen.links.text.toString().lines().map { it.trim() }.filter { it.isNotEmpty() }
            .map { Links.normalize(it) ?: it }.distinct()
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

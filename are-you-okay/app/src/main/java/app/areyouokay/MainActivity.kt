package app.areyouokay

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.widget.Button
import android.widget.TextView
import java.text.DateFormat
import java.util.Date

/** The whole app for the person checking in: one big button. */
class MainActivity : Activity() {
    private lateinit var prefs: Prefs
    private lateinit var okButton: Button
    private lateinit var status: TextView
    private lateinit var delivery: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = Prefs(this)
        okButton = findViewById(R.id.ok_button)
        status = findViewById(R.id.status)
        delivery = findViewById(R.id.delivery)

        okButton.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            CheckIn.record(this)
            refresh()
        }
        findViewById<Button>(R.id.settings_button).setOnClickListener {
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
        val time = DateFormat.getTimeInstance(DateFormat.SHORT)
        val date = DateFormat.getDateInstance(DateFormat.MEDIUM)
        val name = prefs.name

        findViewById<TextView>(R.id.greeting).text =
            if (name.isEmpty()) getString(R.string.greeting) else getString(R.string.greeting_name, name)

        if (prefs.checkedInToday()) {
            okButton.text = getString(R.string.thank_you)
            status.text = getString(R.string.status_done, time.format(Date(prefs.lastCheckInMillis)))
        } else {
            okButton.text = getString(R.string.im_ok)
            status.text = when {
                prefs.isPaused -> getString(R.string.status_paused, date.format(Date(prefs.pausedUntilMillis)))
                else -> getString(R.string.status_not_yet)
            }
        }

        delivery.text = when {
            prefs.links.isEmpty() -> getString(R.string.delivery_not_set_up)
            prefs.pendingLinks.isNotEmpty() -> getString(R.string.delivery_waiting)
            prefs.checkedInToday() && prefs.lastDeliveredMillis >= prefs.lastCheckInMillis ->
                getString(R.string.delivery_sent)
            else -> ""
        }
    }
}

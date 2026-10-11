package app.areyouokay

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Draws the settings screen from a plain [State]; the Activity only wires up the buttons. */
class SettingsScreen(private val root: View) {

    data class State(
        val reminderAt: Long,
        val pausedUntil: Long?,
        val notificationsOk: Boolean,
        val batteryOk: Boolean,
    )

    private val ctx = root.context
    val name: EditText = root.findViewById(R.id.name)
    val links: EditText = root.findViewById(R.id.links)
    val testResult: TextView = root.findViewById(R.id.test_result)

    init {
        root.findViewById<TextView>(R.id.time_help).text = ctx.resources.getQuantityString(
            R.plurals.time_help, Reminders.EXTRA_REMINDERS, Reminders.REPEAT_MINUTES, Reminders.EXTRA_REMINDERS,
        )
    }

    fun bind(s: State) {
        root.findViewById<TextView>(R.id.time_value).text =
            DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(s.reminderAt))

        val paused = s.pausedUntil != null
        root.findViewById<TextView>(R.id.pause_status).text = if (paused) {
            ctx.getString(R.string.paused_until, shortDate(s.pausedUntil!!))
        } else {
            ctx.getString(R.string.not_paused)
        }
        root.findViewById<View>(R.id.pause_choices).visibility = if (paused) View.GONE else View.VISIBLE
        root.findViewById<View>(R.id.resume_button).visibility = if (paused) View.VISIBLE else View.GONE

        checkRow(R.id.row_notifications, s.notificationsOk, R.string.row_notifications_on, R.string.row_notifications_off)
        checkRow(R.id.row_battery, s.batteryOk, R.string.row_battery_on, R.string.row_battery_off)
    }

    /** Describes each link in plain words, and warns when nothing will alert the family. */
    fun showLinks(text: String) {
        val box = root.findViewById<LinearLayout>(R.id.link_summary)
        box.removeAllViews()
        val lines = text.lines().map { it.trim() }.filter { it.isNotEmpty() }
        if (lines.isEmpty()) return
        lines.forEach { line ->
            when (Links.kind(line)) {
                Links.Kind.HEALTHCHECKS -> summaryRow(box, R.drawable.ic_check_circle, R.color.ok_green_text, ctx.getString(R.string.link_healthchecks))
                Links.Kind.NTFY -> summaryRow(box, R.drawable.ic_check_circle, R.color.ok_green_text, ctx.getString(R.string.link_ntfy))
                Links.Kind.OTHER -> summaryRow(box, R.drawable.ic_check_circle, R.color.ok_green_text, ctx.getString(R.string.link_other, Links.host(line)))
                Links.Kind.INVALID -> summaryRow(box, R.drawable.ic_warning, R.color.on_warning_container, ctx.getString(R.string.link_invalid, line))
            }
        }
        if (lines.none { Links.kind(it) == Links.Kind.HEALTHCHECKS }) {
            summaryRow(box, R.drawable.ic_warning, R.color.on_warning_container, ctx.getString(R.string.link_no_alert))
        }
    }

    private fun summaryRow(box: LinearLayout, icon: Int, color: Int, text: String) {
        val tint = ctx.getColor(color)
        val row = TextView(ctx).apply {
            this.text = text
            setTextAppearance(R.style.Text_Body)
            textSize = 17f
            setTextColor(if (color == R.color.ok_green_text) ctx.getColor(R.color.text) else tint)
            val d = ctx.getDrawable(icon)!!.mutate().apply { setTint(tint) }
            val size = (22 * ctx.resources.displayMetrics.density).toInt()
            d.setBounds(0, 0, size, size)
            setCompoundDrawablesRelative(d, null, null, null)
            compoundDrawablePadding = (12 * ctx.resources.displayMetrics.density).toInt()
            setPadding(0, (12 * ctx.resources.displayMetrics.density).toInt(), 0, 0)
        }
        box.addView(row)
    }

    fun onFix(rowId: Int, action: () -> Unit) {
        root.findViewById<View>(rowId).findViewById<Button>(R.id.row_action).setOnClickListener { action() }
    }

    private fun checkRow(id: Int, ok: Boolean, onText: Int, offText: Int) {
        val row = root.findViewById<View>(id)
        val color = ctx.getColor(if (ok) R.color.ok_green_text else R.color.on_warning_container)
        row.findViewById<ImageView>(R.id.row_icon).apply {
            setImageResource(if (ok) R.drawable.ic_check_circle else R.drawable.ic_warning)
            setColorFilter(color)
        }
        row.findViewById<TextView>(R.id.row_label).setText(if (ok) onText else offText)
        row.findViewById<View>(R.id.row_action).visibility = if (ok) View.GONE else View.VISIBLE
    }

    companion object {
        /** "Sunday, October 18": the weekday helps more than the year does. */
        fun shortDate(millis: Long): String {
            val pattern = android.text.format.DateFormat.getBestDateTimePattern(Locale.getDefault(), "EEEEMMMMd")
            return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(millis))
        }
    }
}

package app.areyouokay

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.text.DateFormat
import java.util.Calendar
import java.util.Date

/**
 * Draws the main screen from a plain [State]. Kept separate from the Activity so the
 * screenshot tests render exactly what the phone shows.
 */
class MainScreen(private val root: View) {

    enum class Delivery { NOT_SET_UP, WAITING, SENT, NONE }

    data class State(
        val name: String,
        val now: Long,
        val checkedInAt: Long?,
        val pausedUntil: Long?,
        val reminderAt: Long,
        val delivery: Delivery,
    )

    private val ctx = root.context
    private val date: TextView = root.findViewById(R.id.date)
    private val greeting: TextView = root.findViewById(R.id.greeting)
    private val prompt: TextView = root.findViewById(R.id.prompt)
    val okButton: LinearLayout = root.findViewById(R.id.ok_button)
    private val okIcon: ImageView = root.findViewById(R.id.ok_icon)
    private val okLabel: TextView = root.findViewById(R.id.ok_label)
    private val okSub: TextView = root.findViewById(R.id.ok_sub)
    val statusCard: View = root.findViewById(R.id.status_card)
    private val statusIcon: ImageView = root.findViewById(R.id.status_icon)
    private val statusText: TextView = root.findViewById(R.id.status_text)

    init {
        // TalkBack: announce the big card as a button, not a generic layout.
        okButton.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: android.view.accessibility.AccessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.className = android.widget.Button::class.java.name
            }
        }
    }

    fun bind(s: State) {
        val time = DateFormat.getTimeInstance(DateFormat.SHORT)
        date.text = SettingsScreen.shortDate(s.now)

        val hour = Calendar.getInstance().apply { timeInMillis = s.now }.get(Calendar.HOUR_OF_DAY)
        val partOfDay = ctx.getString(
            when {
                hour < 12 -> R.string.good_morning
                hour < 17 -> R.string.good_afternoon
                else -> R.string.good_evening
            },
        )
        greeting.text = if (s.name.isEmpty()) partOfDay else ctx.getString(R.string.greeting_name, partOfDay, s.name)

        // With very large text there isn't room for the icon on smaller phones; the words matter more.
        val config = ctx.resources.configuration
        okIcon.visibility = if (config.fontScale >= 1.5f && config.screenHeightDp < 800) View.GONE else View.VISIBLE

        val done = s.checkedInAt != null
        if (done) {
            prompt.setText(R.string.prompt_done)
            okButton.setBackgroundResource(R.drawable.ok_button_done)
            okButton.isClickable = false
            okIcon.setImageResource(R.drawable.ic_check)
            okIcon.setColorFilter(ctx.getColor(R.color.on_done_container))
            okLabel.setText(R.string.thank_you)
            okLabel.setTextColor(ctx.getColor(R.color.on_done_container))
            okSub.text = ctx.getString(R.string.checked_in_at, time.format(Date(s.checkedInAt!!)))
            okSub.visibility = View.VISIBLE
            okButton.contentDescription = "${okLabel.text}. ${okSub.text}"
        } else {
            prompt.setText(R.string.prompt_not_yet)
            okButton.setBackgroundResource(R.drawable.ok_button)
            okButton.isClickable = true
            okIcon.setImageResource(R.drawable.ic_heart)
            okIcon.setColorFilter(ctx.getColor(R.color.on_ok_green))
            okLabel.setText(R.string.im_ok)
            okLabel.setTextColor(ctx.getColor(R.color.on_ok_green))
            okSub.visibility = View.GONE
            okButton.contentDescription = ctx.getString(R.string.im_ok_description)
        }

        when {
            s.delivery == Delivery.NOT_SET_UP ->
                status(Tone.WARNING, R.drawable.ic_warning, ctx.getString(R.string.delivery_not_set_up), tappable = true)
            s.delivery == Delivery.WAITING ->
                status(Tone.WARNING, R.drawable.ic_clock, ctx.getString(R.string.delivery_waiting))
            s.delivery == Delivery.SENT ->
                status(Tone.OK, R.drawable.ic_check_circle, ctx.getString(R.string.delivery_sent))
            s.pausedUntil != null ->
                status(Tone.WARNING, R.drawable.ic_pause, ctx.getString(R.string.status_paused, SettingsScreen.shortDate(s.pausedUntil)))
            !done ->
                status(Tone.NEUTRAL, R.drawable.ic_clock, ctx.getString(R.string.status_reminder_at, time.format(Date(s.reminderAt))))
            else -> statusCard.visibility = View.GONE
        }
    }

    private enum class Tone { OK, WARNING, NEUTRAL }

    private fun status(tone: Tone, icon: Int, text: String, tappable: Boolean = false) {
        statusCard.visibility = View.VISIBLE
        // Only the "finish setup" card does something when tapped.
        statusCard.isClickable = tappable
        statusCard.isFocusable = tappable
        statusCard.setBackgroundResource(
            when (tone) {
                Tone.OK -> R.drawable.status_ok
                Tone.WARNING -> R.drawable.status_warning
                Tone.NEUTRAL -> R.drawable.card
            },
        )
        val color = ctx.getColor(
            when (tone) {
                Tone.OK -> R.color.on_done_container
                Tone.WARNING -> R.color.on_warning_container
                Tone.NEUTRAL -> R.color.text_secondary
            },
        )
        statusIcon.setImageResource(icon)
        statusIcon.setColorFilter(color)
        statusText.text = text
        statusText.setTextColor(if (tone == Tone.NEUTRAL) ctx.getColor(R.color.text) else color)
    }
}

package app.areyouokay

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/** Date and time math, kept free of Android so it can be unit-tested (including daylight-saving changes). */
object Schedule {
    /** The next time the daily reminder should fire, strictly after [now]. */
    fun nextReminder(now: ZonedDateTime, hour: Int, minute: Int): ZonedDateTime {
        val today = ZonedDateTime.of(now.toLocalDate(), LocalTime.of(hour, minute), now.zone)
        return if (today.isAfter(now)) today else ZonedDateTime.of(now.toLocalDate().plusDays(1), LocalTime.of(hour, minute), now.zone)
    }

    /** The start of the next day: when the widget and screen switch back to "not checked in yet". */
    fun nextMidnight(now: ZonedDateTime): ZonedDateTime = now.toLocalDate().plusDays(1).atStartOfDay(now.zone)

    fun day(millis: Long, zone: ZoneId): LocalDate = Instant.ofEpochMilli(millis).atZone(zone).toLocalDate()

    fun isSameDay(a: Long, b: Long, zone: ZoneId): Boolean = day(a, zone) == day(b, zone)
}

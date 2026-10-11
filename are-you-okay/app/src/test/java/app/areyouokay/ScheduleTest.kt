package app.areyouokay

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class ScheduleTest {
    private val ny = ZoneId.of("America/New_York")
    private fun at(y: Int, mo: Int, d: Int, h: Int, mi: Int) = ZonedDateTime.of(y, mo, d, h, mi, 0, 0, ny)

    @Test fun laterTodayWhenNotYetPassed() {
        assertEquals(at(2026, 10, 11, 9, 0), Schedule.nextReminder(at(2026, 10, 11, 8, 30), 9, 0))
    }

    @Test fun tomorrowWhenPassedOrExactlyNow() {
        assertEquals(at(2026, 10, 12, 9, 0), Schedule.nextReminder(at(2026, 10, 11, 9, 0), 9, 0))
        assertEquals(at(2026, 10, 12, 9, 0), Schedule.nextReminder(at(2026, 10, 11, 21, 0), 9, 0))
    }

    @Test fun keepsWallClockTimeAcrossDaylightSaving() {
        // US clocks fall back on 2026-11-01: the reminder should still be 9:00 local time.
        val next = Schedule.nextReminder(at(2026, 10, 31, 10, 0), 9, 0)
        assertEquals(9, next.hour)
        assertEquals(1, next.dayOfMonth)
        // Spring forward 2026-03-08: 2:30 doesn't exist, so it moves to 3:30 rather than being skipped.
        val gap = Schedule.nextReminder(at(2026, 3, 7, 12, 0), 2, 30)
        assertEquals(8, gap.dayOfMonth)
        assertEquals(3, gap.hour)
    }

    @Test fun nextMidnight() {
        assertEquals(at(2026, 10, 12, 0, 0), Schedule.nextMidnight(at(2026, 10, 11, 23, 59)))
    }

    @Test fun sameDayUsesLocalTime() {
        val lateEvening = at(2026, 10, 11, 23, 30).toInstant().toEpochMilli()
        val nextMorning = at(2026, 10, 12, 0, 30).toInstant().toEpochMilli()
        val noon = at(2026, 10, 11, 12, 0).toInstant().toEpochMilli()
        assertFalse(Schedule.isSameDay(lateEvening, nextMorning, ny))
        assertTrue(Schedule.isSameDay(lateEvening, noon, ny))
    }
}

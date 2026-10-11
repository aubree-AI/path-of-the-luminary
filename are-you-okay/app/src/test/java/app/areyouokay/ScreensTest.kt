package app.areyouokay

import android.view.LayoutInflater
import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule
import org.junit.Test
import java.util.Calendar

/**
 * Screenshot tests: `./gradlew recordPaparazziDebug` writes PNGs to app/src/test/snapshots.
 * They show every main-screen state on a small and a large phone, in dark mode and at 200% text.
 */
class ScreensTest {
    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5, theme = "Theme.AreYouOkay")

    private val nineAm = Calendar.getInstance().apply {
        set(2026, Calendar.OCTOBER, 11, 9, 2, 0)
    }.timeInMillis
    private val oneDay = 24L * 60 * 60 * 1000

    private fun state(
        checkedIn: Boolean = false,
        delivery: MainScreen.Delivery = MainScreen.Delivery.NONE,
        paused: Boolean = false,
        name: String = "Jean",
    ) = MainScreen.State(
        name = name,
        now = nineAm,
        checkedInAt = if (checkedIn) nineAm else null,
        pausedUntil = if (paused) nineAm + 7 * oneDay else null,
        reminderAt = nineAm - 2 * 60_000,
        delivery = delivery,
    )

    private fun main(s: MainScreen.State): View {
        val v = LayoutInflater.from(paparazzi.context).inflate(R.layout.activity_main, null)
        MainScreen(v).bind(s)
        return v
    }

    @Test fun main_notYet() = paparazzi.snapshot(main(state()))
    @Test fun main_done() = paparazzi.snapshot(main(state(checkedIn = true, delivery = MainScreen.Delivery.SENT)))
    @Test fun main_offline() = paparazzi.snapshot(main(state(checkedIn = true, delivery = MainScreen.Delivery.WAITING)))
    @Test fun main_notSetUp() = paparazzi.snapshot(main(state(name = "", delivery = MainScreen.Delivery.NOT_SET_UP)))
    @Test fun main_paused() = paparazzi.snapshot(main(state(paused = true)))

    @Test fun main_dark() {
        paparazzi.unsafeUpdateConfig(DeviceConfig.PIXEL_5.copy(nightMode = NightMode.NIGHT))
        paparazzi.snapshot(main(state()))
    }

    @Test fun main_dark_done() {
        paparazzi.unsafeUpdateConfig(DeviceConfig.PIXEL_5.copy(nightMode = NightMode.NIGHT))
        paparazzi.snapshot(main(state(checkedIn = true, delivery = MainScreen.Delivery.SENT)))
    }

    @Test fun main_smallPhone_largeText() {
        paparazzi.unsafeUpdateConfig(DeviceConfig.NEXUS_5.copy(fontScale = 2f))
        paparazzi.snapshot(main(state()))
    }

    private fun settingsView(paused: Boolean = false, batteryOk: Boolean = false): View {
        val v = LayoutInflater.from(paparazzi.context).inflate(R.layout.activity_settings, null)
        SettingsScreen(v).apply {
            name.setText("Jean")
            links.setText("https://hc-ping.com/5f2e1c3a-8b7d-4e21-9a6f-2c4d8e1b7a90\nhttps://ntfy.sh/mom-okay-7f3k29xq")
            bind(
                SettingsScreen.State(
                    reminderAt = nineAm - 2 * 60_000,
                    pausedUntil = if (paused) nineAm + 7 * oneDay else null,
                    notificationsOk = true,
                    batteryOk = batteryOk,
                ),
            )
        }
        return v
    }

    @Test fun settings() {
        paparazzi.unsafeUpdateConfig(renderingMode = SessionParams.RenderingMode.V_SCROLL)
        paparazzi.snapshot(settingsView())
    }

    @Test fun settings_dark_paused() {
        paparazzi.unsafeUpdateConfig(
            DeviceConfig.PIXEL_5.copy(nightMode = NightMode.NIGHT),
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )
        paparazzi.snapshot(settingsView(paused = true, batteryOk = true))
    }
}

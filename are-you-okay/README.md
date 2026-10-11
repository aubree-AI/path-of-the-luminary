# Are You Okay?

A tiny Android app for one daily "I'm OK" check-in. One giant green button. A
daily reminder. If the check-in doesn't arrive, a free monitoring service
alerts the family. No accounts, no servers to run, no SMS fees, no tracking.

The idea came from a 74-year-old mom who wanted her daughter to know she is
okay every day.

## How it works

```
 Mom's phone                         Free services                    Family
 ───────────                         ─────────────                    ──────
 9:00  reminder notification
       (repeats every 15 min, 3x)
 tap   [ I'M OK ] ───── POST ─────▶  Healthchecks.io  ── no ping by ──▶ email / Signal /
                         │           (dead-man's switch)   deadline     Telegram / ntfy
                         └── POST ─▶ ntfy.sh (optional) ─── every ───▶ "Mom checked in ✓"
                                                            check-in    push notification
```

**The important design choice:** the *missed* check-in alert is sent by a
server, not by Mom's phone. If her phone is off, dead, broken, or she fell
and can't reach it, the phone can't send anything. That's exactly when the
alert matters most, so a phone-only design fails when you need it.
[Healthchecks.io](https://healthchecks.io) is a "dead man's switch": it
expects a ping on a schedule and alerts you when one doesn't arrive. The free
plan covers 20 checks and many alert channels (email, Signal, Telegram, ntfy,
Pushover, Discord, Slack, and more). It is also
[open source](https://github.com/healthchecks/healthchecks), so you can
self-host it.

## Setup (about 10 minutes)

### 1. The family member: create the alert

1. Sign up at https://healthchecks.io (free).
2. **Add Check** and name it "Mom daily check-in".
3. Set the **Schedule** to **Cron**, enter `0 0 * * *` (midnight every day), and pick Mom's **time zone**.
   Set **Grace time** to the hour by which you'd worry, e.g. **11 hours** means "alert me if she
   hasn't checked in by 11:00 AM". Any check-in after midnight counts for that day.
   (Don't set the cron to the reminder time, like `0 9 * * *`. A check-in at 8:30 would then
   count for the day before and cause a false alarm.) If she checks in late, Healthchecks sends a
   second "back up" message, so you also hear when she's fine.
4. Under **Integrations**, add how you want to be alerted: email is already on.
   Signal, Telegram, or the ntfy app give a phone push.
5. Copy the check's **ping URL** (it looks like `https://hc-ping.com/1234abcd-...`).

> The check starts in the "new" state and only begins watching after the first ping,
> so nothing alerts until Mom's first tap.

### 2. Optional: get a notification on every check-in

Install the free [ntfy](https://ntfy.sh) app on your phone and subscribe to a
hard-to-guess topic name, e.g. `mom-okay-7f3k29xq`. The link to add in the app
is `https://ntfy.sh/mom-okay-7f3k29xq`. Topic names on ntfy.sh are public to
anyone who knows them, so make it random.

### 3. Mom's phone: install the app

1. Download `app-debug.apk` (from this repo's **Actions** tab, or a Release) to the phone.
2. Open it. Android will ask to allow installs from the browser or files app. Allow it, then tap **Install**.
   (Play Protect may warn that the app is unknown. Choose **Install anyway**.)
3. Open **Are You Okay?**, allow notifications, and tap **Settings**:
   - **Your name**: shown in the messages.
   - **Daily reminder time**: a couple of hours before your alert deadline (e.g. 9:00 for an 11:00 deadline).
   - **Check-in links**: paste the Healthchecks ping URL, and the ntfy link if used, one per line.
   - Tap **Send a test message**. You should see "2 of 2 links worked".
   - Tap **Don't let Android put this app to sleep** and allow it. This matters on Samsung, Xiaomi, and other phones that kill background apps.
     See https://dontkillmyapp.com for your model.

### Going away?

Mom can pause reminders in Settings. On Healthchecks.io the family clicks
**Pause** on the check. A paused check resumes automatically on Mom's next
check-in, so there's nothing to remember to turn back on.

## What the app does (and doesn't)

- One screen with one big high-contrast button (white on dark green, about 8:1 contrast).
- A daily reminder at a set time. It repeats every 15 minutes, 3 more times, until she taps.
  She can tap **I'M OK** right on the notification without opening the app.
- Checking in earlier in the day skips the reminder.
- Each check-in is POSTed as plain text to every link. If there's no internet, it waits
  (Android JobScheduler) and sends when the phone reconnects.
- Reminders survive restarts, app updates, and time zone changes.
- Permissions: notifications, exact alarm, start on boot, internet, battery-optimization request.
  **No** SMS, location, contacts, or Google Play Services.
- No analytics. Settings stay on the phone.

## Why not…

| Option | Why we didn't use it (for v1) |
| --- | --- |
| **SMS from the phone** | Google Play blocks `SEND_SMS` for apps that aren't the default SMS app. Android 15+ marks it as a "restricted setting" for sideloaded apps. And the phone that should send the alert may be the one that's dead. |
| **SMS via Twilio etc.** | Needs a paid account, A2P 10DLC registration in the US, and a server. Healthchecks.io can do SMS/WhatsApp on paid plans if you really need it. |
| **PWA (web app)** | Easiest to share, and works on iPhone. But a web page can't reliably fire a local reminder at 9:00 every day (the Notification Triggers API was abandoned). It would need a push server. A PWA *button* + a phone alarm clock + Healthchecks.io does work, and is the obvious iOS path (see Roadmap). |
| **Full-screen alarm** | Android 14+ only grants full-screen intents to alarm/calling apps. A high-priority notification that repeats is enough, and less alarming. |
| **Location sharing** | Adds permissions and complexity. Google Maps "Location sharing" or Life360 already do this well. |

## Build it yourself

Requires JDK 17+ and the Android SDK (or Android Studio: *Open* this folder and press Run).

```bash
cd are-you-okay
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
```

Each push that changes `are-you-okay/` builds the APK on GitHub Actions
(`.github/workflows/are-you-okay.yml`). Download it from the run's **Artifacts**.

**About signing:** debug APKs built on different machines are signed with
different keys. Android won't install an update signed by a different key, so
you'd have to uninstall first and re-enter the settings. To get updates that
install over each other, make one release keystore and add these repository
secrets: `AYO_KEYSTORE_BASE64`, `AYO_KEYSTORE_PASSWORD`, `AYO_KEY_ALIAS`,
`AYO_KEY_PASSWORD`. The workflow then also builds a signed `app-release.apk`.

## Code map

| File | What it does |
| --- | --- |
| `MainActivity.kt` | The big button and today's status |
| `SettingsActivity.kt` | Name, time, links, pause, test, reliability buttons |
| `Reminders.kt` | Daily alarm, 15-minute repeats, the notification |
| `CheckIn.kt` | Records a check-in and POSTs it to the links |
| `SendJobService.kt` | Delivers check-ins, waiting for network if needed |
| `ReminderReceiver.kt`, `BootReceiver.kt` | Alarm, notification-button, and reboot hooks |
| `Prefs.kt` | All saved state |

## Roadmap ideas (good first contributions)

- **iPhone / PWA version**: a one-page web app with the same button POSTing to the same links.
- Choose the number and spacing of reminders in Settings.
- Show a short history of recent check-ins.
- In-app pause that also pauses the Healthchecks check (needs a Healthchecks API key).
- Translations (all text is in `res/values/strings.xml`).
- Publish on F-Droid.

## License

MIT. See `LICENSE`.

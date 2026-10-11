import { $, store, normalize, kind, decodeConfig, send, fmtTime, fmtDay, sameDay } from './shared.js';

const ICONS = {
  check: 'M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z',
  warn: 'M11 15h2v2h-2zm0-8h2v6h-2zm.99-5C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zM12 20c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8z',
  clock: 'M11.99 2C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zM12 20c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8zm.5-13H11v6l5.25 3.15.75-1.23-4.5-2.67z',
};
const CHECK_MARK = 'M9 16.17L4.83 12l-1.42 1.41L9 19 21 7l-1.41-1.41z';
const HEART = $('#ok-icon path').getAttribute('d');

// Everything lives on this phone. The setup link's #fragment never reaches the server.
let state = store.get('ayo', { name: '', links: [], lastCheckIn: 0, helpAt: 0, pending: [], broken: [], deliveredAt: 0 });
const save = () => store.set('ayo', state);

// Opening the setup link from the family page configures everything, then hides it from the address bar.
const m = location.hash.match(/^#c=(.+)$/);
if (m) {
  const cfg = decodeConfig(m[1]);
  if (cfg) {
    state = { ...state, name: cfg.name, links: cfg.links, broken: [] };
    save();
    state.justSetUp = true;
  }
  history.replaceState(null, '', location.pathname);
}

function render() {
  const now = Date.now();
  const hour = new Date().getHours();
  const part = hour < 12 ? 'Good morning' : hour < 17 ? 'Good afternoon' : 'Good evening';
  $('#date').textContent = fmtDay(now);
  $('#greeting').textContent = state.name ? `${part}, ${state.name}` : part;

  const done = state.lastCheckIn && sameDay(state.lastCheckIn, now);
  const ok = $('#ok');
  ok.classList.toggle('done', !!done);
  ok.setAttribute('aria-disabled', done ? 'true' : 'false');
  $('#ok-icon path').setAttribute('d', done ? CHECK_MARK : HEART);
  $('#ok-label').textContent = done ? 'Thank you!' : "I'm OK";
  $('#ok-sub').hidden = !done;
  if (done) $('#ok-sub').textContent = `Checked in at ${fmtTime(state.lastCheckIn)}`;
  ok.setAttribute('aria-label', done ? `Thank you! Checked in at ${fmtTime(state.lastCheckIn)}` : "I'm OK. Tap to let your family know you are okay.");
  $('#prompt').textContent = done ? "You're all set for today. See you tomorrow." : "Tap the big button so your family knows you're okay.";

  const setUp = state.links.length > 0;
  $('#help-btn').hidden = !setUp;
  const helpToday = state.helpAt && sameDay(state.helpAt, now);
  const pendingHelp = state.pending.some((p) => p.help);
  if (state.justSetUp) status('ok-tone', ICONS.check, `You're connected to your family. Tap I'm OK once a day.`);
  else if (helpToday) status('danger', ICONS.warn, pendingHelp ? 'Sending your alert. It will go out as soon as you are online.' : `Your family was alerted at ${fmtTime(state.helpAt)}.`);
  else if (!setUp) status('warn', ICONS.warn, 'Not connected to your family yet. Open the setup link they sent you, or tap here.', true);
  else if (done && state.broken.length) status('warn', ICONS.warn, "Your check-in couldn't reach one of your family's links. Ask them to check it.", true);
  else if (state.pending.length) status('', ICONS.clock, navigator.onLine && !state.offline ? 'Telling your family…' : "No internet right now. Your check-in will be sent when you're back online.");
  else if (done && state.deliveredAt >= state.lastCheckIn) status('ok-tone', ICONS.check, 'Your family has been told.');
  else status('', ICONS.clock, 'Once a day is all it takes.');
}

function status(tone, icon, text, tappable = false) {
  const el = $('#status');
  el.className = `status ${tone}`;
  $('#status-icon').setAttribute('d', icon);
  $('#status-text').textContent = text;
  el.onclick = tappable ? openSettings : null;
  el.style.cursor = tappable ? 'pointer' : '';
}

/** Sends everything still queued. Called after a tap, on load, and whenever the phone comes back online. */
let flushing = false;
async function flush() {
  if (flushing || !state.pending.length) return;
  flushing = true;
  const still = [];
  for (const p of state.pending) {
    const r = await send(p.link, p.message, { help: p.help });
    if (r === 'retry') still.push(p);
    if (r === 'broken' && !state.broken.includes(p.link)) state.broken.push(p.link);
  }
  state.pending = still;
  // Remember a failed attempt: wi-fi without internet still reports navigator.onLine === true.
  state.offline = still.length > 0;
  if (!still.length && !state.broken.length) state.deliveredAt = Date.now();
  save();
  flushing = false;
  render();
  if (still.length) setTimeout(flush, 30_000);
}

function queue(message, help) {
  // A newer check-in replaces an undelivered older one; help alerts are always kept.
  state.pending = state.pending.filter((p) => p.help).concat(state.links.map((link) => ({ link, message, help })));
  save();
  flush();
}

$('#ok').addEventListener('click', () => {
  if (state.lastCheckIn && sameDay(state.lastCheckIn, Date.now())) return;
  navigator.vibrate?.(40);
  state.lastCheckIn = Date.now();
  state.justSetUp = false;
  state.broken = [];
  const who = state.name || 'Your family member';
  if (state.links.length) queue(`${who} checked in: I'm OK (${fmtTime(state.lastCheckIn)})`, false);
  save();
  render();
});

$('#help-btn').addEventListener('click', () => $('#help-dialog').showModal());
$('#help-dialog').addEventListener('close', () => {
  if ($('#help-dialog').returnValue !== 'yes') return;
  state.helpAt = Date.now();
  state.justSetUp = false;
  const who = state.name || 'Your family member';
  queue(`${who} pressed I NEED HELP at ${fmtTime(state.helpAt)}. Please call them now.`, true);
  render();
});

// Settings
function openSettings() {
  $('#name').value = state.name;
  $('#links').value = state.links.join('\n');
  summarize();
  $('#test-result').textContent = '';
  $('#settings').showModal();
}
function summarize() {
  const lines = $('#links').value.split('\n').map((l) => l.trim()).filter(Boolean);
  const words = { healthchecks: 'Alerts your family if you miss a check-in', ntfy: 'Tells your family each time you check in' };
  const box = $('#link-summary');
  box.replaceChildren();
  const add = (tag, warn, text) => {
    const li = document.createElement('li');
    const t = document.createElement('span');
    t.className = warn ? 'tag warn' : 'tag';
    t.textContent = tag;
    li.append(t, ' ', text);
    box.append(li);
  };
  for (const l of lines) {
    const k = kind(l);
    if (state.broken.includes(normalize(l))) add('Check', true, "Didn't work last time. Ask your family to check this link.");
    else if (k === 'invalid') add('Not a link', true, l);
    else add('OK', false, words[k] || `Sends to ${new URL(normalize(l)).hostname}`);
  }
  if (lines.length && !lines.some((l) => kind(l) === 'healthchecks')) {
    add('Note', true, 'No missed check-in alert yet. Without a Healthchecks.io link, nobody is alerted if you miss a day.');
  }
}
function saveSettings() {
  state.name = $('#name').value.trim().slice(0, 60);
  state.links = [...new Set($('#links').value.split('\n').map((l) => l.trim()).filter(Boolean).map((l) => normalize(l) || l))];
  state.justSetUp = false;
  save();
  render();
}
$('#settings-btn').addEventListener('click', openSettings);
$('#links').addEventListener('input', summarize);
$('#settings').addEventListener('close', saveSettings);
$('#test-btn').addEventListener('click', async () => {
  saveSettings();
  if (!state.links.length) { $('#test-result').textContent = 'Add a check-in link first.'; return; }
  $('#test-result').textContent = 'Sending…';
  const who = state.name || 'Your family member';
  const results = await Promise.all(state.links.map((l) => send(l, `Test message from ${who}'s Are You Okay page`)));
  const ok = results.filter((r) => r === 'sent').length;
  $('#test-result').textContent = ok === results.length
    ? 'It worked. Your family should see the test message.'
    : `${ok} of ${results.length} links worked. Check the links, and that the phone is online.`;
});

// "Install" button where the browser supports it (Android Chrome); iPhone uses Share > Add to Home Screen.
let installPrompt = null;
addEventListener('beforeinstallprompt', (e) => { e.preventDefault(); installPrompt = e; $('#install-btn').hidden = false; });
$('#install-btn').addEventListener('click', async () => { await installPrompt?.prompt(); installPrompt = null; $('#install-btn').hidden = true; });
if (matchMedia('(display-mode: standalone)').matches) $('#install-card').hidden = true;

addEventListener('online', flush);
addEventListener('online', render);
addEventListener('offline', render);
document.addEventListener('visibilitychange', () => { if (!document.hidden) { render(); flush(); } });
// Switch back to "I'm OK" after midnight if the page is left open.
setInterval(render, 60_000);

if ('serviceWorker' in navigator) navigator.serviceWorker.register('/sw.js').catch(() => {});
render();
flush();

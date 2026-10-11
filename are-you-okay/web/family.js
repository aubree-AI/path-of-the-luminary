import { $, store, normalize, kind, encodeConfig, fmtTime, fmtDay } from './shared.js';

const saved = store.get('ayo-family', {});
const randomTopic = () => {
  const bytes = crypto.getRandomValues(new Uint8Array(9));
  return 'okay-' + Array.from(bytes, (b) => 'abcdefghjkmnpqrstuvwxyz23456789'[b % 31]).join('');
};

$('#name').value = saved.name || '';
$('#hc').value = saved.hc || '';
$('#topic').value = saved.topic || randomTopic();

function persist() {
  store.set('ayo-family', { name: $('#name').value.trim(), hc: $('#hc').value.trim(), topic: $('#topic').value.trim() });
  const topic = $('#topic').value.trim();
  $('#subscribe').href = topic ? `https://ntfy.sh/${encodeURIComponent(topic)}` : '#';
}
for (const id of ['#name', '#hc', '#topic']) $(id).addEventListener('input', persist);
$('#new-topic').addEventListener('click', () => { $('#topic').value = randomTopic(); persist(); loadRecent(); });
persist();

// Check the Healthchecks link really exists: hc-ping answers "OK (not found)" for a typo.
let hcTimer;
$('#hc').addEventListener('input', () => {
  clearTimeout(hcTimer);
  hcTimer = setTimeout(checkHc, 600);
});
async function checkHc() {
  const v = $('#hc').value.trim();
  const out = $('#hc-check');
  if (!v) { out.textContent = ''; return; }
  if (kind(v) !== 'healthchecks') { out.textContent = "That doesn't look like a Healthchecks ping URL (https://hc-ping.com/…)."; return; }
  out.textContent = 'Checking…';
  // A "log" event confirms the check exists without changing its state ("start" would
  // make Healthchecks expect a finish, and alert if none came).
  try {
    const res = await fetch(normalize(v).replace(/\/+$/, '') + '/log', { method: 'POST' });
    const body = await res.text();
    out.textContent = res.ok && !/not found/i.test(body)
      ? '✓ Found your check. This link works.'
      : "Healthchecks doesn't know this link. Copy the ping URL again from the check's page.";
  } catch {
    out.textContent = "Couldn't reach Healthchecks right now. You can still continue.";
  }
}

function buildLink() {
  const links = [];
  const hc = normalize($('#hc').value);
  if (hc) links.push(hc);
  const topic = $('#topic').value.trim();
  if (topic) links.push(`https://ntfy.sh/${encodeURIComponent(topic)}`);
  return `${location.origin}/#c=${encodeConfig({ name: $('#name').value.trim(), links })}`;
}

$('#make').addEventListener('click', () => {
  if (!normalize($('#hc').value) && !$('#topic').value.trim()) {
    $('#copied').textContent = '';
    $('#hc-check').textContent = 'Add at least the Healthchecks link (step 2) or an ntfy topic (step 3).';
    $('#hc').focus();
    return;
  }
  const link = buildLink();
  $('#link').textContent = link;
  $('#out').hidden = false;
  $('#share').hidden = !navigator.share;
  loadRecent();
});
$('#copy').addEventListener('click', async () => {
  try { await navigator.clipboard.writeText($('#link').textContent); $('#copied').textContent = 'Copied. Paste it into a text message.'; }
  catch { $('#copied').textContent = 'Press and hold the link above to copy it.'; }
});
$('#share').addEventListener('click', () => {
  const name = $('#name').value.trim();
  navigator.share?.({
    title: 'Are You Okay?',
    text: `${name ? name + ', here' : 'Here'} is your Are You Okay? link. Tap it, then tap I'm OK once a day so I know you're okay. ❤️`,
    url: $('#link').textContent,
  }).catch(() => {});
});

/** Recent check-ins come straight from the ntfy topic (ntfy keeps messages for about 12 hours on ntfy.sh). */
async function loadRecent() {
  const topic = $('#topic').value.trim();
  if (!topic) return;
  try {
    const res = await fetch(`https://ntfy.sh/${encodeURIComponent(topic)}/json?poll=1&since=all`);
    const msgs = (await res.text()).trim().split('\n').filter(Boolean).map((l) => JSON.parse(l)).filter((m) => m.event === 'message');
    const list = $('#recent');
    list.replaceChildren();
    $('#recent-card').hidden = false;
    if (!msgs.length) {
      $('#recent-note').textContent = 'No check-ins yet on this topic. They show up here and in the ntfy app.';
      return;
    }
    for (const m of msgs.slice(-10).reverse()) {
      const li = document.createElement('li');
      const tag = document.createElement('span');
      const help = m.priority === 5 || /NEED HELP/.test(m.message);
      tag.className = help ? 'tag help' : 'tag';
      tag.textContent = help ? 'Help' : 'OK';
      li.append(tag, ` ${fmtDay(m.time * 1000)}, ${fmtTime(m.time * 1000)}: ${m.message}`);
      list.append(li);
    }
    $('#recent-note').textContent = 'ntfy.sh keeps messages for about 12 hours; Healthchecks.io keeps the full history.';
  } catch {
    $('#recent-card').hidden = true;
  }
}
loadRecent();
setInterval(loadRecent, 60_000);

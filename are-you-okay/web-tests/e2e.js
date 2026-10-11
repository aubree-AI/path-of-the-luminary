// End-to-end check of the web version in a phone-sized Chromium, with the same headers Vercel sends.
const { chromium } = require('playwright');
const http = require('http');
const fs = require('fs');
const path = require('path');
// Usage: npm test (from this folder). Screenshots go to ./screenshots.
const W = path.join(__dirname, '..', 'web');
const OUT = path.join(__dirname, 'screenshots');
fs.mkdirSync(OUT, { recursive: true });
const vercel = JSON.parse(fs.readFileSync(path.join(W, 'vercel.json')));
const types = { '.html': 'text/html', '.js': 'text/javascript', '.css': 'text/css', '.svg': 'image/svg+xml', '.png': 'image/png', '.webmanifest': 'application/manifest+json' };
const server = http.createServer((req, res) => {
  let p = decodeURIComponent(new URL(req.url, 'http://x').pathname);
  if (p === '/') p = '/index.html';
  if (!path.extname(p)) p += '.html'; // cleanUrls
  const f = path.join(W, p);
  if (!fs.existsSync(f)) { res.writeHead(404); return res.end(); }
  for (const h of vercel.headers[0].headers) res.setHeader(h.key, h.value);
  res.setHeader('Content-Type', types[path.extname(f)] || 'application/octet-stream');
  res.end(fs.readFileSync(f));
}).listen(0);
const base = `http://localhost:${server.address().port}`;

const sent = []; let hcFound = true; let fail = 0;
const check = (cond, msg) => { if (cond) console.log('  ✓', msg); else { console.log('  ✗', msg); fail++; } };

async function mock(ctx) {
  // Playwright's offline mode doesn't stop routed requests, so fail them here.
  const setOffline = ctx.setOffline.bind(ctx);
  ctx.setOffline = async (v) => { ctx.isOffline = v; await setOffline(v); };
  await ctx.route(/hc-ping\.com|ntfy\.sh/, async (route) => {
    if (ctx.isOffline) return route.abort('internetdisconnected');
    const r = route.request();
    if (r.method() === 'OPTIONS') return route.fulfill({ status: 200, headers: { 'access-control-allow-origin': '*', 'access-control-allow-headers': '*' } });
    if (r.method() === 'GET') return route.fulfill({ status: 200, headers: { 'access-control-allow-origin': '*' },
      body: sent.filter((s) => s.url.includes('ntfy')).map((s, i) => JSON.stringify({ event: 'message', time: Math.floor(Date.now() / 1000) - 60 * i, message: s.body, priority: s.headers.priority === 'urgent' ? 5 : 3 })).join('\n') });
    sent.push({ url: r.url(), body: r.postData(), headers: r.headers() });
    const body = r.url().includes('hc-ping') && !hcFound ? 'OK (not found)' : 'OK';
    return route.fulfill({ status: 200, body, headers: { 'access-control-allow-origin': '*' } });
  });
}

(async () => {
  const browser = await chromium.launch();
  const phone = { viewport: { width: 390, height: 844 }, deviceScaleFactor: 2, isMobile: true, hasTouch: true };
  const errors = [];

  console.log('Family page');
  const fam = await browser.newContext(phone); await mock(fam);
  const f = await fam.newPage(); f.on('pageerror', (e) => errors.push(e.message)); f.on('console', (m) => m.type() === 'error' && errors.push(m.text()));
  await f.goto(`${base}/family`);
  await f.fill('#name', 'Jean');
  await f.fill('#hc', 'hc-ping.com/5f2e1c3a-8b7d-4e21-9a6f-2c4d8e1b7a90');
  await f.waitForFunction(() => document.querySelector('#hc-check').textContent.includes('This link works'));
  check(sent.some((s) => s.url.endsWith('/log')), 'link checked with a harmless /log ping (not /start)');
  await f.click('#make');
  const link = await f.textContent('#link');
  check(link.startsWith(`${base}/#c=`), 'personal link created');
  await f.screenshot({ path: `${OUT}/family.png`, fullPage: true });

  console.log("Mom's phone");
  const mom = await browser.newContext(phone); await mock(mom);
  const m = await mom.newPage(); m.on('pageerror', (e) => errors.push(e.message)); m.on('console', (x) => x.type() === 'error' && errors.push(x.text()));
  await m.goto(link);
  check(!(await m.url()).includes('#c='), 'setup link removed from the address bar');
  check((await m.textContent('#greeting')).includes('Jean'), 'greets her by name');
  check((await m.textContent('#status-text')).includes('connected'), 'says she is connected');
  await m.screenshot({ path: `${OUT}/mom-new.png` });
  sent.length = 0;
  await m.click('#ok');
  await m.waitForFunction(() => document.querySelector('#status-text').textContent.includes('has been told'));
  check(sent.length === 2, 'check-in sent to Healthchecks and ntfy');
  check(sent.every((s) => s.body.startsWith("Jean checked in: I'm OK")), 'message names her');
  check((await m.textContent('#ok-label')) === 'Thank you!', 'button turns into Thank you');
  await m.screenshot({ path: `${OUT}/mom-done.png` });
  await m.click('#ok', { force: true }); await m.waitForTimeout(300);
  check(sent.length === 2, 'a second tap the same day sends nothing');

  console.log('I need help');
  sent.length = 0;
  await m.click('#help-btn');
  await m.screenshot({ path: `${OUT}/mom-help-dialog.png` });
  await m.click('#help-dialog button[value=yes]');
  await m.waitForFunction(() => document.querySelector('#status-text').textContent.includes('alerted'));
  check(sent.some((s) => s.url.endsWith('/fail')), 'Healthchecks gets /fail (immediate alert)');
  check(sent.some((s) => s.url.includes('ntfy') && s.headers.priority === 'urgent'), 'ntfy gets urgent priority');
  await m.screenshot({ path: `${OUT}/mom-help-sent.png` });

  console.log('Recent check-ins on the family page');
  await f.reload(); await f.waitForSelector('#recent li');
  check((await f.textContent('#recent')).includes('NEED HELP'), 'family page lists the help alert');
  await f.screenshot({ path: `${OUT}/family-recent.png` });

  console.log('Offline, then back online');
  const off = await browser.newContext(phone); await mock(off);
  const o = await off.newPage(); await o.goto(link); sent.length = 0;
  await off.setOffline(true);
  await o.click('#ok'); await o.waitForTimeout(500);
  check((await o.textContent('#status-text')).includes('No internet'), 'says it will send when back online');
  await o.screenshot({ path: `${OUT}/mom-offline.png` });
  await off.setOffline(false);
  await o.waitForFunction(() => document.querySelector('#status-text').textContent.includes('has been told'), null, { timeout: 10000 });
  check(sent.length === 2, 'queued check-in sent after reconnecting');

  console.log('Mistyped Healthchecks link');
  hcFound = false;
  const bad = await browser.newContext(phone); await mock(bad);
  const b = await bad.newPage(); await b.goto(link); await b.click('#ok');
  await b.waitForFunction(() => document.querySelector('#status-text').textContent.includes("couldn't reach"));
  check(true, 'flags "OK (not found)" as a broken link');
  const bf = await bad.newPage(); await bf.goto(`${base}/family`); await bf.fill('#hc', 'https://hc-ping.com/typo');
  await bf.waitForFunction(() => document.querySelector('#hc-check').textContent.includes("doesn't know"));
  check(true, 'family page warns about the typo before sending the link');
  hcFound = true;

  console.log('Looks');
  const dark = await browser.newContext({ ...phone, colorScheme: 'dark' }); await mock(dark);
  const d = await dark.newPage(); await d.goto(link); await d.screenshot({ path: `${OUT}/mom-dark.png` });
  const land = await browser.newContext({ ...phone, viewport: { width: 844, height: 390 } }); await mock(land);
  const l = await land.newPage(); await l.goto(link); await l.screenshot({ path: `${OUT}/mom-landscape.png` });
  const small = await browser.newContext({ ...phone, viewport: { width: 320, height: 568 } }); await mock(small);
  const sm = await small.newPage(); await sm.goto(link);
  const overflow = await sm.evaluate(() => document.documentElement.scrollWidth > innerWidth);
  check(!overflow, 'no sideways scrolling on a 320px phone');
  await sm.screenshot({ path: `${OUT}/mom-small.png` });

  check(errors.length === 0, `no page or console errors (CSP on)${errors.length ? ': ' + errors.join(' | ') : ''}`);
  await browser.close(); server.close();
  console.log(fail ? `${fail} FAILED` : 'ALL PASSED');
  process.exit(fail ? 1 : 0);
})();

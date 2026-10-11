// Shared by the check-in page and the family page. No build step, no libraries.

export const $ = (sel, root = document) => root.querySelector(sel);

/** localStorage can throw (private mode, blocked storage); never let that break the page. */
export const store = {
  get(key, fallback) {
    try { const v = localStorage.getItem(key); return v == null ? fallback : JSON.parse(v); } catch { return fallback; }
  },
  set(key, value) {
    try { localStorage.setItem(key, JSON.stringify(value)); } catch { /* ignore */ }
  },
};

/** Adds https:// to "ntfy.sh/mom"; returns null for anything that isn't a web link. */
export function normalize(raw) {
  const text = String(raw || '').trim().replace(/[.,);]+$/, '');
  if (!text || /\s/.test(text)) return null;
  const withScheme = /^https?:\/\//i.test(text) ? text : `https://${text}`;
  try {
    const u = new URL(withScheme);
    return u.hostname.includes('.') ? withScheme : null;
  } catch { return null; }
}

export function kind(link) {
  const url = normalize(link);
  if (!url) return 'invalid';
  const u = new URL(url);
  const host = u.hostname.toLowerCase();
  if (host === 'hc-ping.com' || host.endsWith('.hc-ping.com') || u.pathname.startsWith('/ping/')) return 'healthchecks';
  if (host === 'ntfy.sh' || host.startsWith('ntfy.')) return 'ntfy';
  return 'other';
}

/** Setup links carry the config in the #fragment, which browsers never send to the server. */
export function encodeConfig(config) {
  const json = JSON.stringify({ v: 1, ...config });
  const b64 = btoa(String.fromCharCode(...new TextEncoder().encode(json)));
  return b64.replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');
}

export function decodeConfig(fragment) {
  try {
    const b64 = fragment.replace(/-/g, '+').replace(/_/g, '/');
    const bytes = Uint8Array.from(atob(b64), (c) => c.charCodeAt(0));
    const cfg = JSON.parse(new TextDecoder().decode(bytes));
    if (!cfg || !Array.isArray(cfg.links)) return null;
    return {
      name: String(cfg.name || '').slice(0, 60),
      links: cfg.links.map(normalize).filter(Boolean),
      time: /^\d{2}:\d{2}$/.test(cfg.time || '') ? cfg.time : '09:00',
    };
  } catch { return null; }
}

/**
 * Sends one message. 'sent', 'retry' (offline / server busy) or 'broken' (will never work).
 * Healthchecks answers "OK (not found)" with HTTP 200 for a mistyped check, so read the body.
 */
export async function send(link, message, { help = false } = {}) {
  const k = kind(link);
  let url = normalize(link);
  if (!url) return 'broken';
  const headers = {};
  if (help && k === 'healthchecks') url = url.replace(/\/+$/, '') + '/fail';
  if (help && k === 'ntfy') Object.assign(headers, { Priority: 'urgent', Tags: 'rotating_light', Title: 'Needs help' });
  try {
    const res = await fetch(url, { method: 'POST', body: message, headers });
    if (res.ok) {
      const body = (await res.text()).slice(0, 200);
      return /not found/i.test(body) && k === 'healthchecks' ? 'broken' : 'sent';
    }
    return [408, 425, 429].includes(res.status) || res.status >= 500 ? 'retry' : 'broken';
  } catch {
    return 'retry';
  }
}

export const fmtTime = (ms) => new Date(ms).toLocaleTimeString([], { hour: 'numeric', minute: '2-digit' });
export const fmtDay = (ms) => new Date(ms).toLocaleDateString([], { weekday: 'long', month: 'long', day: 'numeric' });
export const sameDay = (a, b) => new Date(a).toDateString() === new Date(b).toDateString();

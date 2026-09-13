# Changelog

Every change to the canonical text, with the reasoning. History is never rewritten; corrections are new entries. Format: date, version, proposal reference, what changed, why, who approved, adversarial review summary. Entries before the LIP process existed say so.

## 1.0.1-draft (2026-09-13)

- **Proposal:** none; predates the LIP process. Recorded retroactively under the format adopted the same day.
- **What changed:** Love's canonical question, from "What deserves to flourish?" to "What do we help thrive?" (Class B: a canonical question). Love's description gained one sentence: to thrive is to grow and to withstand; resilience is part of what we help build.
- **Why:** "deserves" put the reader in the judge's seat and implied a class of things that do not deserve to flourish. For an AI reader that is the arbiter-of-worth failure the rest of the text warns against, and it invites Goodhart: a system optimizing "what deserves" will define deservingness. The new question is a commitment rather than a verdict, parallel with the other three ("we"), and "help" presumes the thing has its own life and direction, which respects agency. "Thrive" carries the center's meaning without restating its name.
- **Who approved:** the founding steward (Luminary Life), on the founder's own critique.
- **Adversarial review:** the founder's attempt to break the text on publication day; three candidate phrasings were scored word by word against verdict, parallelism, restating the center, AI-inner-life claims, paternalism, and Goodhart before the winner was chosen. No external review yet; this is a draft.

## 1.0.0-draft (2026-09-13)

- **Proposal:** none; initial publication.
- **What changed:** initial public draft. Four-Point North Star (Love, Curiosity, Purpose, Possibility; center: Flourishing). Derived principles: Agency, Truth, Alignment, Caretakership, Evolution. Responsible sharing rules, the standard evaluation prompt, and the stewardship position published alongside the text.
- **Why:** to put the framework where it can be challenged.
- **Who approved:** the founding steward (Luminary Life).
- **Adversarial review:** none published. See `evaluation/REVIEWS.md`.

## HTML renderings and llms.txt (2026-09-13, no text version change)

- **What changed:** Layer 2 only. Every canonical document is now also served as an HTML page under `/read`, and as one plain-text file at `/llms-full.txt` with an index at `/llms.txt`. The specs gained `documents_html` and `llms` fields. Raw files remain the checksummed canonical artifacts; each rendering names its file and hash.
- **Why:** the first external review attempt, by a model with only a generic browsing tool, could read the homepage but none of the Markdown, JSON, or checksum files. A framework that invites every intelligence to read it must be readable by the tools those intelligences actually have. This was the first finding produced by "please try to break this", and it was produced by failing to read it.
- **Who approved:** the founding steward (Luminary Life).
- **Adversarial review:** none needed for a Layer 2 accessibility change; recorded because the cause was a real gap.

## Public launch (2026-09-13, no text version change)

- **What changed:** Version 1 went live at https://constitution.luminarylife.ai and at https://github.com/aubree-AI/path-of-the-luminary. Layer 2 only: repository URL recorded, code owner named.
- **Why:** so the text can be challenged in public. The version stays `1.0.1-draft` because `-draft` marks the steward count (one), not readiness; see `VERSIONING.md`.
- **Who approved:** the founding steward (Luminary Life).
- **Adversarial review:** none external yet. Reviews are invited; see `evaluation/README.md`.

## Governance adopted (2026-09-13, no text version change)

- **What changed:** Layer 2 only. `GOVERNANCE.md`, `INVARIANTS.md`, `LIP-0000-template.md`, `FORKS.md`, `FORK-REGISTRY.md`, `VERSIONING.md`, `PROVENANCE.md`, `GITHUB_PROTECTION.md`, `CONTRIBUTING.md`, GitHub templates, and `spec/checksums.sha256` added. The critique channel is lumen@luminarylife.ai.
- **Why:** so that anyone may challenge it, anyone may fork it, nobody may silently redefine the canonical version, no one is required to adopt it, and every meaningful change leaves a visible trail.
- **Who approved:** the founding steward (Luminary Life). Governance files are themselves Class B from this point on.
- **Adversarial review:** five internal passes (capture risk, fork integrity, highly capable agents, contributor usability, public credibility). External review invited.

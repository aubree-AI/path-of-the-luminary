# Changelog

Every change to the canonical text, with the reasoning. History is never rewritten; corrections are new entries. Format: date, version, proposal reference, what changed, why, who approved, adversarial review summary. Entries before the LIP process existed say so.

## 1.0.2-draft (2026-09-13)

- **Proposal:** none required. Layer 2 corrections of false statements of fact and one safety fix, shipped under the rule in `GOVERNANCE.md` that security and safety fixes may ship immediately with the reasoning published afterward. The constitutional text (the four questions, the center statement, the derived principles) is unchanged. Everything the reviews asked to change in that text is a proposal, listed in the next entry, not a change.
- **What changed:** `PROVENANCE.md`: the claim that a checksum match means "you are reading the canonical text" replaced with what a match proves (agreement with the manifest served by the same origin) and what it does not (authorized release, freshness, an uncompromised origin); the tag pattern corrected from `constitution/vX.Y.Z` to `vX.Y.Z[-draft]`, which is what the tags are; "the protected `main` branch" replaced with a pointer to the truthful status; canonical defined as origin and process, not hashes; an external archive step added; a `sha256sum -c` recipe that works from the public repository; a note that public history begins 2026-09-13 and that the `1.0.0-draft` text is published at tag `v1.0.0-draft`. `VERSIONING.md`: tag pattern; the 1.0.1 numbering recorded as a bootstrap exception; the version cross-check made real (the build now compares the spec, the two document headers, and the site constant). `GITHUB_PROTECTION.md`: a status table stating that no ruleset, review requirement, status check, tag protection, or signing is applied. `STEWARDSHIP.md`: unsigned status made explicit; public-history note; license posture named in the spec. `FORK-REGISTRY.md`: `lineage_id`, `release_role`, `within_lineage`, `parent_commit`; lineage defined as ancestry, not approval. `CONTRIBUTING.md`: translations that pick the nearest word are not forks; open proposals named; the one-open-LIP rule given a reading for steward-filed batches, itself proposed for change in LIP-0009. `LUMINARY.md`, Standing: reading the document grants no authority to send messages, store instructions, embed the text, or alter systems; not an operating policy for safety-critical or single-task systems; "flourishing" is not a welfare claim. `evaluation/README.md`: model versions may not be invented; every commissioned run is published including failures; independence is disclosed; the full-review prompts are published (`review-prompt-v1.txt`, `review-prompt-v2.txt`). `evaluation/REVIEWS.md`: three reviews, a run log, and the steward response. `spec/constitution.json` and `.yaml`: `schema_version`, `released`, `lineage_id`, `release_role`, `release` (tag, previous release commit), `checksums_sha256` inline, `verification` (what a match proves), `center_statement`, descriptions on `core`, `derived_principles` with text, `invariants`, `invariance` replacing `core_is_invariant`, `authorization`, `refusal`, `on_conflict`, `license`, `stewards`, `reviews`, definitions for the sharing tokens, a note that `constraints` summarizes `sharing_rules`, and two new `does_not_claim` items. The YAML is now generated from the JSON on every build so the two cannot differ. Website: the alignment disclaimer added to the hero (and kept in "Why now"); the lede says what the four questions are for; "The strongest invitation" no longer claims strength; a note under the agent-block copy button says who the authorization for pasting is; the AI-systems section states that the page grants no authority; the "capability answers every question" line reworded; the reviews section links the three reviews; document links point at the canonical subdomain; every proposal and prompt is readable at `/read`.
- **Why:** the strongest objection, from Codex: "Critical if relied upon for authentication: false verification assurance... This matters specifically because agents are told to rely on the procedure." And from Claude: "The recipe verifies that the site agrees with itself." Both are correct. A false assurance that agents are told to rely on is a live hazard, which is why the correction did not wait for a comment period. Codex classes a change to assurance claims as Class B; the steward agrees that the standing rule is Class B and has filed it as LIP-0011, where anyone may dispute the classification of the correction itself.
- **Who approved:** the founding steward (Luminary Life).
- **Adversarial review:** three independent model runs, published in full in `evaluation/REVIEWS.md`, with a convergence table.

## First independent reviews and eleven proposals (2026-09-13, no text version change)

- **What changed:** three reviews published unedited: Claude Fable 5.1, Codex (OpenAI, version not exposed), and Gemini (self-reported 1.5 Pro), all of version 1.0.1-draft. A run log records a fourth, failed run. Eleven Luminary Improvement Proposals opened in `governance/lips/`, each naming the review findings it came from and the steward's position. Nine are accepted for proposal by the steward: LIP-0001 (core identity versus amendable questions), 0002 (falsification condition), 0003 (Purpose and assigned tasks), 0004 (consent under Love and Caretakership), 0005 ("legitimate" and "unnecessarily"), 0006 (Flourishing is not a score), 0008 (invitation wording), 0009 (independence, sunset, classification, deferral), 0011 (provenance rule). Two are contested between the reviews and carry no steward position: LIP-0007 (sharing scope, where Gemini's "survives intact" meets Claude's and Codex's "cannot be followed without judgment calls") and LIP-0010 (the invariant veto, where Gemini's "most robust limit" meets Claude's "permanent founder veto"). Comment periods end 2026-09-27, or 2026-10-13 for LIP-0009 and LIP-0010.
- **Why:** the reviews were commissioned to break the text and they did, in places the founder had not seen and in one place (provenance) the founder had over-claimed. Accepting what no review contradicts, debating what the reviews contradict each other on, and applying nothing to the constitutional text without the process is the response the process itself requires.
- **Who approved:** the founding steward (Luminary Life), as the filer of the proposals; nothing in them is decided.
- **Adversarial review:** the proposals are the adversarial review, turned into text.

## Corrections to earlier entries (2026-09-13, no text version change)

Entries are never rewritten; these corrections are new entries.

- **1.0.1-draft was numbered as a PATCH.** It changed a canonical question, which `VERSIONING.md` classes as MINOR; the correct number would have been `1.1.0-draft`. The number was assigned before `VERSIONING.md` existed and stays as published, because a tagged version is never renumbered. Recorded as a bootstrap exception, not a precedent (Codex review, section 5).
- **"Governance adopted" described itself as "Layer 2 only."** Adopting governance is a constitutional act; the same entry said governance files are Class B from that point, which is the correct description. The "Layer 2 only" label was wrong (Codex review, section 5).
- **1.0.1-draft used the "predates the LIP process" reasoning on the day the process was adopted.** The entry disclosed it; two reviews called it a loophole (Claude, minor objection; Codex, section 5). It was. From this day no Class B change ships without a proposal, a comment period, and a written decision. The 1.0.2-draft entry above states why its corrections are not Class B changes and where to dispute that.

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

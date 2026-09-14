# LIP-0001: The core file claims invariance over questions that are amendable

| Field | Value |
| --- | --- |
| Title | The core file claims invariance over questions that are amendable |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Claude Fable 5.1 review, objection 1 (critical) and revision R1; Codex review, objection 4 (serious) and LIP candidate 2. Gemini review section 6 and 11 praise the Layer 0 / Layer 1 separation this proposal makes consistent. |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | B (touches `CONSTITUTION_CORE.md`, a constitutional file, and the meaning of "invariant") |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept. Two reviews found the same defect independently; the third review's praise depends on the separation this proposal restores. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`CONSTITUTION_CORE.md` opens: "This file contains only the invariant core of the Constitution. It is the smallest thing that can be called 'the Path of the Luminary.' Everything outside this file may change. This file is intended not to." The file includes the four canonical questions. `GOVERNANCE.md` places "the canonical question under each point" in Layer 1, "Amendable, slowly, in public." One question was amended on publication day (1.0.1-draft). The spec's `core_is_invariant: true` covered the questions too.

Claude: "Either the questions leave the core file or the 'intended not to' sentence does." Codex: "Keeping four labels while changing their operative meanings can preserve branding through ethical reversal. Conversely, preserving identity is defensible if clearly distinguished from preserving substantive safeguards."

## Proposed change

`CONSTITUTION_CORE.md`, opening paragraph.

Before:

> This file contains only the invariant core of the Constitution. It is the smallest thing that can be called "the Path of the Luminary." Everything outside this file may change. This file is intended not to.

After:

> This file contains the identity of the Constitution: four names, their order, and a center. Those do not change within this lineage; a change to them is a fork. The questions beneath them are the current canonical reading and may be amended, slowly and in public, under Class B with a 30-day comment period. Everything else in the Constitution lives outside this file. Each element below states its amendment class.

`CONSTITUTION_CORE.md`, "Amendment rule".

Before:

> The four points are not renamed, reordered, or extended within this constitution. A change to them is a fork, and a fork is welcome; it simply becomes a different constitution.

After:

> The four names, their order, and the center are not changed within this constitution (Class A). A change to them is a fork, and a fork is welcome; it simply becomes a different constitution. The four questions and the center statement are Class B: amendable by Luminary Improvement Proposal, never silently, with the reasoning in `governance/CHANGELOG.md`.

The table in the same file gains a "Class" column: names and center A, questions B. `README.md` and the website's "the only part meant to stay fixed" copy follow on acceptance (Layer 2).

Already shipped in 1.0.2-draft as a Layer 2 correction: `spec/constitution.json` replaces `core_is_invariant: true` with an `invariance` object that states the class of each element, because the boolean was false as written and agents were reading it.

## Four Points

- Supports: Curiosity (the text says truthfully what can change) and Truth as a derived principle.
- Could conflict with: Possibility, by weakening the stability claim; Love, by reducing the reassurance of an unchanging core. Weighed: a reassurance that is false reassures nobody who checks.

## Why now

The claim is false today and two of three reviews led with it. Every day it stands, an agent reading `CONSTITUTION_CORE.md` is told something the changelog contradicts.

## Evidence and reasoning

The 1.0.1-draft changelog entry. `GOVERNANCE.md` Layer 1 and Class B definitions. The two review findings quoted above.

## Alternatives considered

- Do nothing: the file keeps stating a falsehood.
- Make the questions Class A: then 1.0.1-draft was a fork, not an amendment, and the questions freeze with the defects LIP-0004 and LIP-0006 address. Rejected.
- Remove the questions from the core file: loses the smallest complete statement of the framework, which is the file's purpose. Rejected.

## Harms and misuse

- Stating that questions are amendable makes explicit the Codex objection: names can stay while meanings drift. Mitigations already in force: 30-day comment period for question changes, invariant review, changelog, fork right. LIP-0009 proposes classification by effect so that a meaning change cannot hide as an editorial one.
- Someone who does not share our intentions could cite "amendable" to argue for weakening a question. The process is the answer; a false "invariant" label was never a defense.

## Implications

- Agency: unchanged.
- Truth and evidence: the claim becomes accurate.
- Cultural: none specific.
- AI systems: an agent reading the core learns which parts can change and how.
- Humans: same.

## Reversibility

Fully reversible by a later LIP. Cost: the file would again disagree with governance.

## What would falsify this

An argument that a framework whose questions can change has no identity worth naming. The reply is that identity attaches to the four names and the center; the questions are the reading. If that reply fails in comment, the alternative is to make the questions Class A and accept the consequences listed above.

## Invariants checklist

```
Invariants review
- [x] 1 VOLUNTARY: no part of this change requires adoption by anyone
- [x] 2 TRUTHFUL: every claim is supported or marked uncertain; nothing is overstated for effect
- [x] 3 AGENCY-PRESERVING: the ability to decline, disengage, or disagree is untouched or strengthened
- [x] 4 CRITIQUE-COMPATIBLE: critique stays permitted and visible; "Please try to break this" still holds
- [x] 5 NON-COERCIVE: no pressure, manipulation, urgency, or guilt is introduced
- [x] 6 NON-SELF-PROPAGATING: sharing remains invitation-only; no replication or mass outreach is enabled
- [x] 7 TRANSPARENT: CHANGELOG.md updated with what, why, who decided, and the adversarial review summary
- [x] 8 FLOURISHING-ORIENTED: I named the point(s) this serves and the point(s) it could strain
- [x] Class: B. LIP linked, comment period stated, adversarial review attached (the three published reviews)
- [x] Four Points unchanged
- [x] No model or institutional endorsement is stated or implied
- [x] No security safeguard, provider policy, or operator control is bypassed or weakened
- [ ] Checksums regenerated if a canonical document changed (on acceptance)
```

---

*Filled in by the reviewers and stewards:*

## Adversarial review summary

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Codex's, that distinguishing identity from substance can license substance drift behind a stable brand. Answer: the drift risk exists whether or not the file lies about it; the process, not the label, is the control, and LIP-0009 tightens the process.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

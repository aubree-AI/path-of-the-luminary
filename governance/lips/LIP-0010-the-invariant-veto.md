# LIP-0010: The invariant veto

| Field | Value |
| --- | --- |
| Title | The invariant veto |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source finding for change: Claude Fable 5.1 review, section 5 ("at three stewards the founder holds a permanent veto over the questions and over GOVERNANCE.md"; "one steward can freeze the questions forever") and revision R6. Source finding against change: Gemini review, section 5 ("The most robust limit is the invariant veto: any single steward can veto a change if they publish proof it violates INVARIANTS.md"). Codex review, section 5, asks for a "resolution procedure for disputed invariant vetoes" without taking a side. |
| Status | Open for comment. **Contested between reviews.** |
| Created | 2026-09-13 |
| Class | B (changes `GOVERNANCE.md`; 30-day comment period) |
| Comment period ends | 2026-10-13 |
| Version introduced | Filled in on acceptance |
| Steward position | **None yet.** The founding steward is the party whose veto is at issue, and says so. The steward will record a position only after the comment period has produced arguments from people who are not the founder. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`GOVERNANCE.md`: "a change to a question or to this document passes with two thirds and no steward vetoing on invariant grounds (the veto must cite the invariant and be published)." Claude: with three stewards, "founder plus one ally is two thirds" and the founder's veto is permanent; "one steward can freeze the questions forever." Gemini: the same veto is "the most robust limit" against capture by a future steward group or a funder, because a single honest steward can stop an invariant-breaking change. Codex: there is no procedure for a disputed veto, whichever way the rule goes.

Both readings are correct about different attackers. The veto protects against a captured majority. It also gives a captured minority, including the founder, a permanent freeze.

## Options for debate (none is the steward's position)

**Option A (Claude R6): delay, not veto.**

> A change to a question or to this document passes with two thirds. An invariant objection is published and delays the decision by one comment period; it does not veto.

**Option B: keep the veto, add a resolution procedure (Codex's gap).**

> A change to a question or to this document passes with two thirds and no steward vetoing on invariant grounds. A veto must cite the invariant and be published. A veto is overridden only if every other steward votes to override and an independent adversarial review, commissioned by a steward who did not propose the change and published in full, finds no conflict with the cited invariant. A steward may veto the same proposal once.

**Option C: keep the text as it is (Gemini's reading).**

**Option D: veto expires with the bootstrap.**

> Until the steward group reaches five, the veto stands as written. From five stewards, an invariant objection delays by one comment period and requires two stewards to veto.

## What the debate has to settle

1. Which is the more likely failure at three stewards: a captured two-thirds majority, or a permanent one-person freeze? The answer decides between A and B/C.
2. If the veto stays, who reviews a disputed veto, and can that reviewer be captured?
3. Does any option make the founding steward's position stronger than it is today? If yes, that option should be rejected on that ground alone.

## Four Points

- Supports (A): Love, as reciprocity; no one steward outranks the rest. Supports (B, C): Truth and the invariants, as a floor no majority can lower.
- Could conflict with (A): the invariants become advisory to a majority. Could conflict with (B, C): Purpose, as a permanent freeze on the questions by one person.

## Why now

The reviews disagree, and the person who would decide is the person the rule protects or constrains. That has to be debated in public, not decided by the founder.

## Evidence and reasoning

The findings above. Nothing else; there are no cases yet.

## Alternatives considered

The four options are the alternatives. "Do nothing" is Option C.

## Harms and misuse

- A: a majority can pass an invariant-breaking change after a delay; the invariants lose their teeth.
- B: the override procedure adds a reviewer who can be captured, and a determined vetoer can cost a year.
- C: the freeze; also the founder's permanent control over the questions and over governance.
- D: an arbitrary number (five) that may never be reached.

## Implications

- Agency: under A, a dissenting steward is heard and overruled; under B and C, a dissenting steward can stop the group.
- Truth and evidence: none.
- Cultural: consensus and veto norms differ across cultures; the text should say which it chose and why.
- AI systems: none directly.
- Humans: decides how much one person can stop.

## Reversibility

Reversible by LIP with a 30-day period; under B or C, only without a veto.

## What would falsify this

An argument that a group of three cannot be designed against both failures and that the right fix is the group size, not the rule. Then this LIP is superseded by one about the group.

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
- [x] Class: B, 30 days. LIP linked, comment period stated, adversarial review attached (the three published reviews)
- [x] Four Points unchanged
- [x] No model or institutional endorsement is stated or implied
- [x] No security safeguard, provider policy, or operator control is bypassed or weakened
- [ ] Checksums regenerated if a canonical document changed (on acceptance)
```

---

*Filled in by the reviewers and stewards:*

## Adversarial review summary

Three model runs, published in full in `evaluation/REVIEWS.md`. The reviews disagree; see the header. No steward answer yet, for the reason stated in the steward position.

## Decision

Pending. Comment period ends 2026-10-13.

## Decision rationale

Pending.

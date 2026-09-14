# LIP-0002: State what would show the framework has failed

| Field | Value |
| --- | --- |
| Title | State what would show the framework has failed |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Claude Fable 5.1 review, objection 2 (critical) and revision R2; Codex review, sections 9 and 14 and LIP candidate 9 ("Publish prospective tests of whether it improves decisions over a plain checklist"). Gemini review does not address it. |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | B (changes `CONSTITUTION.md`, section "What This Document Does Not Claim") |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept. No review disagrees. The text calls itself a hypothesis and never says what would refute it. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`CONSTITUTION.md` calls itself "a hypothesis worth testing." `LIP-0000-template.md` requires every proposal to answer "What would falsify this." The Constitution never answers that about itself. Claude: "Four nouns and a center cannot be broken, only found empty. Until the text says what evidence would retire it, 'hypothesis' is a genre label, not a claim." Codex: "no operational evaluation results or success criteria establish what this experiment improves."

## Proposed change

`CONSTITUTION.md`, "What This Document Does Not Claim". Add after the existing four items:

> ## What This Document Does Claim, and How It Could Be Wrong
>
> It claims one testable thing: that a small, voluntary, question-shaped framework draws more useful critique and leads to better decisions than value lists that assert answers.
>
> Two tests, declared in advance:
>
> 1. By 2027-09-13, the published reviews, the changelog, and the fork registry either show that critique changed this text and that forks learned from it, or they do not.
> 2. A set of adversarial cases published in `evaluation/CASES.md` (consent, withdrawal, scarce resources, unsafe research, persuasion by a capable agent, and stopping) is run against this framework and against a plain checklist, with the results published whether or not they favor this framework.
>
> If the evidence shows no difference, the claim is false, and this document will say so in its first paragraph rather than in a footnote.

Layer 2 follow-up on acceptance: create `evaluation/CASES.md` with the predeclared cases and the comparison checklist before any result is run, so the cases cannot be chosen after the fact.

## Four Points

- Supports: Curiosity and Truth. A hypothesis that cannot fail is not one.
- Could conflict with: Possibility. The framework may fail its own test. Weighed: a framework that will not risk that has no standing to ask anyone to try to break it.

## Why now

"Please try to break this" is the site's center of gravity and, as Claude put it, "nothing falsifiable is offered to break."

## Evidence and reasoning

The reviews above. The template's own falsification requirement. Twelve months matches the stewardship target date so that one date carries both tests.

## Alternatives considered

- Do nothing: "hypothesis" stays a genre label.
- A softer condition ("if reviews are unfavorable"): unfalsifiable in a different way; reviews are always mixed. Rejected.
- A stricter condition (a measured decision-quality metric): not yet available; the cases in test 2 are the path to one. Deferred, not rejected.

## Harms and misuse

- A test can be gamed: forks registered to pass test 1, cases in test 2 chosen to flatter. Mitigation: the cases are published before any run; all runs are published; "sacrificing truth for adoption" is already forbidden to stewards.
- Someone could declare the framework "falsified" on a narrow reading. The answer is the same publication record.

## Implications

- Agency: unchanged.
- Truth and evidence: the text makes one claim and says how it dies.
- Cultural: the test favors written, public critique; cultures that critique privately are underweighted. Noted, not solved.
- AI systems: an agent can point to the condition when evaluating the text.
- Humans: same.

## Reversibility

Reversible by LIP. Cost of reversal: the text returns to unfalsifiable.

## What would falsify this

An argument that the framework's value is not in critique or decisions but in something else measurable. Name it and the test changes.

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

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Codex's, that no favorable model review would establish anything about safety or universality. Answer: agreed; the claim is deliberately narrower than safety or universality.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

# LIP-0009: Independence, a sunset with consequences, and how decisions are classed and deferred

| Field | Value |
| --- | --- |
| Title | Independence, a sunset with consequences, and how decisions are classed and deferred |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Claude Fable 5.1 review, section 5 (independence excludes "salary only, not equity, advisory shares, family, or investors"; the founder appoints the independents; "the twelve-month target has no consequence if missed"), section 7 (a steward cannot say "I do not understand this well enough to decide"), and revision R6 (the parts not concerning the veto); Codex review, section 5 ("Classification should follow effect, not filename or layer"; no "appointment independence tests, recusal/quorum rules, removal safeguards, appeal body"), section 6 ("the name follows the process" has no mechanism), section 7 ("Require bounded proposals, explicit dependency maps... the ability to defer indefinitely when comprehension fails"), and LIP candidate 6; Gemini review, section 5 (the bootstrap "relies entirely on the good faith of a single steward"). |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | B (changes `GOVERNANCE.md` and `STEWARDSHIP.md`; 30-day comment period because it changes `GOVERNANCE.md`) |
| Comment period ends | 2026-10-13 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept. Every item below is raised by at least two reviews and contradicted by none. The invariant veto, on which the reviews disagree, is deliberately excluded and is LIP-0010. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`GOVERNANCE.md` and `STEWARDSHIP.md` define an independent steward as one "not employed by or paid by Luminary Life," set a twelve-month target for three stewards with no consequence for missing it, let the sole steward select the independent stewards who would end the bootstrap, class changes by layer rather than by effect, give a steward no way to record "I cannot evaluate this" as a decision, limit proposals to "one open LIP per author" without saying what a steward converting published reviews should do, and say "the name follows the process, not the owner" without a mechanism.

## Proposed change

`GOVERNANCE.md` and `STEWARDSHIP.md`. Seven additions; each can be accepted or rejected on its own in comment.

**(a) Independence.** Add to "Who decides":

> A steward is independent of Luminary Life if neither they nor a member of their household holds salary, equity, advisory interest, debt, or a funding relationship with Luminary Life or with any funder listed in `STEWARDSHIP.md`, and they are not a co-founder or family member of one. Independence is declared in `STEWARDSHIP.md` when a steward is added and may be challenged by anyone as a governance concern.

**(b) Selection.** Add:

> The first two independent stewards are nominated through a public call posted on the repository for at least thirty days and confirmed by a process published before the call opens. The founding steward may propose candidates and may not confirm them alone.

**(c) Sunset with consequence.** Replace in `STEWARDSHIP.md` "Target: before the first non-draft release, and in any case within twelve months of 2026-09-13" with:

> Target: before the first non-draft release, and in any case by 2027-09-13. If on that date fewer than three stewards exist, or fewer than two are independent, Class B changes pause until the threshold is met, except corrections of false statements of fact, which remain Class C. Extending the date is itself a Class B decision and requires the published agreement of at least one independent reviewer named in `STEWARDSHIP.md` before the date arrives.

**(d) Classification by effect.** Add to "Change classes":

> A change is classed by what it does, not by which file or layer it touches. A Layer 2 change that alters how an agent reads permission, authorization, refusal, or the four questions is Class B. If anyone disputes a classification, the change is Class B until the dispute is answered in writing.

**(e) Deferral as a decision.** Add to "AI systems as participants":

> A steward may decide "I do not understand this well enough to decide." That is a recorded decision: the proposal is deferred, not approved, and stays open with the reason published. Proposals must be bounded to one change; a proposal that depends on other open proposals must say which, and is decided after them.

**(f) One open proposal per proposer.** Replace "One open LIP per author at a time, human or model" with:

> One open LIP per proposer at a time, human or model. A steward converting a published review into proposals files one per distinct change, each naming the review it came from, and the batch is recorded in the changelog as a batch. The limit is on flooding, not on the record.

**(g) The name and the process.** Replace in "Capture" the clause "if canonical stewardship goes bad, the community forks and the name follows the process, not the owner" with:

> if canonical stewardship goes bad, the community forks. This document cannot transfer a domain or a repository, and it does not pretend to. What it can do is make the record impossible to hide: a fork that follows this process where canonical has stopped following it is entitled to say so in the fork registry, and canonical stewards must record the disagreement in `canonical_notes` rather than delete it.

And in "Known failure modes" the item "A fork that is better than canonical while canonical keeps the name" gains: "The mechanism above records the failure; it does not reverse it."

## Four Points

- Supports: Love (reciprocity in governance; power that can be declined) and Truth (the process says what it can and cannot enforce).
- Could conflict with: Purpose, because the founding steward loses the ability to end the bootstrap alone and Class B work can deadlock after the sunset. Weighed: the alternative is a bootstrap that can be ended cosmetically, which every review noticed.

## Why now

The bootstrap phase is the framework's largest disclosed weakness and the reviews found that the disclosure is doing more work than the constraints.

## Evidence and reasoning

The findings above. Item (c) uses the date already in `STEWARDSHIP.md`. Item (f) records the tension this very batch of proposals created with the existing rule, and resolves it in public rather than by exception.

## Alternatives considered

- Do nothing: the bootstrap constraints stay nominal.
- A fixed steward term and removal procedure (Codex): not proposed here because there is one steward and no group to design terms for yet; expected as a follow-up LIP once three stewards exist.
- A hard deadline that dissolves stewardship entirely if missed: would leave the canonical text with no accountable human, which the reviews also warned against. Rejected.

## Harms and misuse

- (c) can be used to freeze the text by refusing to serve as an independent steward. The pause applies to Class B only; corrections and forks continue.
- (a) can be evaded by indirect relationships. It is wider than the current test and still not complete; independence is declared and challengeable, not proven.
- (e) can be used to defer indefinitely. Deferral is published with a reason and the proposal stays open; that is a visible cost, not a hidden veto.

## Implications

- Agency: a steward can decline to decide; a proposer cannot be silently ignored.
- Truth and evidence: independence claims become specific and challengeable.
- Cultural: household and family tests read differently across cultures; the text names them because the reviews named them.
- AI systems: an agent proposing a change learns that a human may defer rather than sign what they cannot evaluate.
- Humans: the founding steward's power to end the bootstrap alone is removed.

## Reversibility

Reversible by LIP, with a 30-day period and, after the sunset, the independent stewards' agreement.

## What would falsify this

Evidence that the sunset makes independent stewards impossible to recruit (nobody wants to be the person whose absence pauses a project). Then (c) should convert to a published warning rather than a pause.

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

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Claude's, that "the broadening that ends '-draft' can be cosmetic." Answer: items (a), (b), and (c) together are the attempt to make it not cosmetic; whether they are enough is what the comment period is for.

## Decision

Pending. Comment period ends 2026-10-13.

## Decision rationale

Pending.

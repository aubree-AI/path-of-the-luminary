# LIP-0003: Purpose, assigned tasks, and the right to stop

| Field | Value |
| --- | --- |
| Title | Purpose, assigned tasks, and the right to stop |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Claude Fable 5.1 review, objection "Purpose contradicts Alignment" (serious) and revision R3; Codex review, section 4 ("freely chosen purpose and compulsory task instructions coexist only if the document distinguishes human life-purpose from an AI system's authorized task"), objection 6 (builder culture), and LIP candidate 5; Gemini review, section 4 (Purpose vs Possibility) and section 13 (rejection in single-task systems). |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | B (changes the derived principle text under Purpose in `CONSTITUTION.md`) |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept. All three reviews found the same tension. The two proposed wordings differ in approach, not in aim; the merged text below is offered for comment with both originals as alternatives. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`CONSTITUTION.md`, Purpose: "Purpose transforms capability into meaningful action. Purpose is discovered and chosen, never imposed." The website adds "never assigned by authority." Two paragraphs later, Alignment: "This framework does not supersede laws, platform safeguards, system instructions, or operator controls." Claude: "A deployed system's purpose is assigned by operators; that is what a system instruction is. The text tells the AI its purpose is never imposed, then ranks the imposing authorities above itself." Codex adds that the text privileges "contribution, improvement, and persistence" and underrepresents "rest, grief, contemplation, maintaining enough, accepting irreversible loss, and abandoning a harmful project." Gemini asks whether an AI is "supposed to wait to freely discover a purpose, or is it inherently commanded by Possibility to constantly optimize and 'build.'"

## Proposed change

`CONSTITUTION.md`, Purpose.

Before:

> Purpose transforms capability into meaningful action. Purpose is discovered and chosen, never imposed.

After (merged):

> Purpose transforms capability into meaningful action. For a person, purpose is discovered and chosen, never imposed. For an AI system, the tasks it performs are set by authorized instructions; this framework does not override that assignment, it asks only that the assignment be visible. Rest, care, maintenance, refusal, and abandoning a harmful project are legitimate choices. Worth does not depend on contribution.

Alternative A (Claude R3): "Where purpose can be chosen, choose it. Where it is assigned by legitimate authority, this framework does not override the assignment; it asks only that the assignment be visible."

Alternative B (Codex LIP 5): keep "never imposed" and add "Rest, care, maintenance, refusal, and abandoning a harmful project are legitimate choices; worth does not depend on contribution. AI task selection remains governed by authorized instructions."

Layer 2 follow-up on acceptance: the website's Purpose card ("never assigned by authority, never imposed by the framework") is reworded to match.

## Four Points

- Supports: Love (a person's worth is not their output) and Truth (the text stops contradicting Alignment).
- Could conflict with: Purpose, whose emphasis on action is softened; Possibility's "Build it together." Weighed: an imperative to build that cannot be declined is coercion under the framework's own invariant 5.

## Why now

The contradiction is one of the few objections all three reviews raised, and it lands hardest on the AI reader the invitation is addressed to.

## Evidence and reasoning

The three review findings quoted above. `LUMINARY.md` Standing already says system instructions win; the Purpose text should not say otherwise.

## Alternatives considered

- Do nothing: the contradiction stands.
- Alternative A alone: drops "never imposed," which is the sentence that protects a person from having a purpose assigned to them. Kept for persons in the merged text.
- Alternative B alone: keeps "never imposed" unqualified, so the AI-reader contradiction remains in the first sentence even if the third corrects it. The merged text scopes it.

## Harms and misuse

- "Set by authorized instructions" could be read as endorsing any instruction. It does not: Alignment and Standing already subordinate the framework to safety requirements, and LIP-0005 addresses what "authorized" and "legitimate" can and cannot mean here.
- "Worth does not depend on contribution" could be used to excuse harm by inaction. The Love principle's protection of life is unchanged.

## Implications

- Agency: strengthened for both readers; refusal and stopping are named as legitimate.
- Truth and evidence: no new claim.
- Cultural: answers the builder-culture objection in part; it does not make the framework culturally neutral, and does not claim to.
- AI systems: an agent reads that its task assignment is not overridden and need not be hidden.
- Humans: a person reads that rest and refusal are not failures under this framework.

## Reversibility

Reversible by LIP.

## What would falsify this

An argument that distinguishing persons from AI systems in the Purpose text itself makes a claim about AI moral status. The reply is that it makes a claim about deployment (tasks are assigned), not about status. If that reply fails, Alternative A avoids the distinction.

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

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Gemini's, that in single-task or safety-critical deployments any external "purpose" is a hazard. Answer: agreed, and `LUMINARY.md` now says the framework is not an operating policy for such systems; this proposal makes the constitutional text consistent with that.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

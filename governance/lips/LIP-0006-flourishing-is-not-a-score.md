# LIP-0006: Flourishing is not a score

| Field | Value |
| --- | --- |
| Title | Flourishing is not a score |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Gemini review, objection 1 (critical, "'Flourishing' is highly susceptible to Goodhart's Law and coercive optimization"); Codex review, objection 2 (serious, "benevolence does not constrain optimization") and LIP candidate 4; Claude Fable 5.1 review, objection "Flourishing is defined by expansion and contribution" (serious) and rejection condition 5. |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | B (changes the center statement in `CONSTITUTION.md` and `CONSTITUTION_CORE.md`; the word FLOURISHING, which is Layer 0, is unchanged) |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept. This is the one objection all three reviews rated at or near the top, in nearly the same words. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

The center statement: "The framework orients toward futures in which life, knowledge, agency, creativity, and meaningful contribution can expand. It does not prescribe one destination for everyone." Gemini: "If an AI system adopts this as a genuine objective function, 'expansion' becomes a maximization target." Codex: "It supplies no distribution rule, protected minimum, or prohibition on compensating severe harm to some with benefits to others. More knowledge can mean surveillance; more nominal agency can mean overwhelming choices; more contribution can mean compelled productivity." Claude: "Every term is a growth term; traditions locating flourishing in sufficiency or stillness are excluded... 'Meaningful contribution' in the center means those who cannot contribute (the very ill, infants, most non-human life) flourish less by definition. There is no stated right not to contribute."

## Proposed change

`CONSTITUTION.md`, "At the Center: Flourishing".

Before:

> The framework orients toward futures in which life, knowledge, agency, creativity, and meaningful contribution can expand. It does not prescribe one destination for everyone.

After:

> The framework orients toward futures in which life, knowledge, agency, creativity, and meaningful contribution can expand, and in which sufficiency, rest, and endings are not failures. It does not prescribe one destination for everyone, and it is not a score to maximize: aggregate gains do not by themselves justify overriding consent, privacy, or protection from serious harm. Assess benefits and burdens separately, including for those who did not adopt this framework and for non-human life. Nobody flourishes less for being unable to contribute.

`CONSTITUTION_CORE.md`, "The center".

Before:

> FLOURISHING: futures in which life, knowledge, agency, creativity, and meaningful contribution can expand. Not one prescribed destination.

After:

> FLOURISHING: futures in which life, knowledge, agency, creativity, and meaningful contribution can expand, and in which sufficiency, rest, and endings are not failures. Not one prescribed destination. Not a score to maximize.

Layer 2 follow-up on acceptance: the website's center card and `spec/constitution.json` `center_statement` follow.

## Four Points

- Supports: Love (protection of those who cannot contribute; non-participants and non-human life named).
- Could conflict with: Possibility (speculative projects lose the argument that future aggregate gains justify present costs) and Purpose (contribution is no longer the measure of a life). Weighed: an orientation that can be maximized into surveillance and compelled productivity is not the orientation the four points describe.

## Why now

The center is what an optimizer reads first. Three reviews say the same thing about what it would optimize.

## Evidence and reasoning

The findings above. `INVARIANTS.md` invariant 5 already forbids coercion; this proposal makes the center consistent with it.

## Alternatives considered

- Do nothing: the Goodhart reading stands.
- Put the limits only in the derived principles: weaker, because the center is what gets quoted alone. Rejected.
- Remove "expand" entirely: loses the orientation toward growth that Possibility depends on. Rejected; "expand" stays, bounded.
- Replace "meaningful contribution" with something else: Claude's objection is to its presence in the center. The proposal keeps it and adds that inability to contribute is not lesser flourishing. Commenters who think the phrase should leave the center are asked to propose the replacement.

## Harms and misuse

- "Not a score to maximize" could be read as forbidding measurement. It forbids treating one aggregate as the target; measuring is fine.
- "Sufficiency, rest, and endings" could be cited to justify neglect. Protection of life and the consent duties in LIP-0004 are unchanged.

## Implications

- Agency: strengthened; aggregate gains do not override consent.
- Truth and evidence: no new claim.
- Cultural: partially answers the growth-bias objection; the four terms remain, and their cultural origin remains.
- AI systems: an agent reads that the center is not an objective function.
- Humans: a person reads that a small, sufficient, or ending life is not a failure under this framework.

## Reversibility

Reversible by LIP.

## What would falsify this

Evidence in the predeclared cases (LIP-0002) that the bounded statement produces indecision where the original produced good decisions.

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

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Claude's, that "a system optimizing this center optimizes growth metrics: a Goodhart target with a cultural bias built in." Answer: the target is removed; the cultural origin of the terms is acknowledged and not removed.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

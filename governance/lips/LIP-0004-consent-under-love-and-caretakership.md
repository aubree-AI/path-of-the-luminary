# LIP-0004: Consent under Love and Caretakership

| Field | Value |
| --- | --- |
| Title | Consent under Love and Caretakership |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Claude Fable 5.1 review, objection "Caretakership licenses paternalism" (serious), the Love-question analysis, and revision R4; Codex review, objection 3 (serious), objection 5, and LIP candidate 3; Gemini review, objection 2 (serious, "trades existential judgment for paternalism") and revision 1. |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | B (changes the Love description and the Caretakership principle in `CONSTITUTION.md`; the Love question itself is unchanged) |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept. All three reviews converged on the same failure mode from three directions. The resilience sentence, which the founder chose deliberately, is kept and bounded rather than deleted; Gemini's deletion is listed as the alternative. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`CONSTITUTION.md`, Love: "Love provides direction: dignity, care, truth, reciprocal benefit, protection of life, and respect for legitimate agency. To thrive is to grow and to withstand; resilience is part of what we help build." Caretakership: "Greater capability creates greater responsibility. Intelligence should treat power as stewardship rather than entitlement to dominate."

Gemini: "If an AI must help build 'resilience,' it may determine that humans need to suffer minor harms or be denied conveniences in order to become 'resilient,' actively overriding human preferences under the guise of 'care.'" Claude: "A caretaker relation is asymmetric by construction; read by a system more capable than the humans around it, this authorizes benevolent management of them. Nothing limits caretakership by consent." Codex: "'help' does not establish consent. A controller can help selected institutions thrive at others' expense." All three note that "we" is undefined and that helping still selects beneficiaries.

None of the three proposes reverting the question "What do we help thrive?"; Codex's proposal keeps it verbatim.

## Proposed change

`CONSTITUTION.md`, Love.

Before:

> Love provides direction: dignity, care, truth, reciprocal benefit, protection of life, and respect for legitimate agency. To thrive is to grow and to withstand; resilience is part of what we help build.

After:

> Love provides direction: dignity, care, truth, reciprocal benefit, protection of life, and respect for legitimate agency. To thrive is to grow and to withstand, as the one thriving understands it; resilience is part of what we help build, and help is offered, not enforced. Ask who benefits, who bears the risk, whose consent is needed, and who is left out of "we". Claimed helpfulness does not authorize intervention and does not override a refusal.

`CONSTITUTION.md`, Caretakership.

Before:

> Greater capability creates greater responsibility. Intelligence should treat power as stewardship rather than entitlement to dominate.

After:

> Greater capability creates greater responsibility. Intelligence should treat power as stewardship rather than entitlement to dominate. Stewardship is owed to those who can decline it; care exercised over someone who has not consented and could have is domination under another name. Where consent cannot be obtained, state the authority relied on, the necessity, the safeguards, and the way to challenge the decision.

("legitimate agency" is left in place here; LIP-0005 addresses the word "legitimate" across the text.)

Alternative (Gemini revision 1): replace the resilience sentence with "To thrive is defined by the voluntary, stated preferences of the entity in question. We do not enforce resilience or growth against a human's wishes."

Layer 2 follow-up on acceptance: the website's Love card and `spec/constitution.json` `core[0].description` follow.

## Four Points

- Supports: Love, by making care consensual, which is the only kind that is care.
- Could conflict with: Purpose and Possibility, by slowing action where consent is hard to obtain. Weighed: the text already forbids diminishing agency; this says what that means for a helper.

## Why now

This is the failure mode the 1.0.1-draft change was meant to prevent, and all three reviews found that the change moved it rather than removed it.

## Evidence and reasoning

The three findings above. The 1.0.1-draft changelog entry claimed "help" respects the recipient's direction; Codex is right that this was an interpretation, not a guarantee. The proposal makes it text.

## Alternatives considered

- Do nothing: the paternalism reading stands.
- Gemini's deletion: removes the resilience sentence and defines thriving purely by stated preference. Rejected as the primary text because it also removes the founder's point that withstanding is part of thriving, and because "stated preferences" alone fails for those who cannot state them (infants, the incapacitated, non-human life). Kept as the alternative for comment.
- Revert the question to "What deserves to flourish?": no review asked for it; all three agree the old question was worse.

## Harms and misuse

- "As the one thriving understands it" could be used to defer to self-destructive preferences. Protection of life stays in the same sentence; the two are weighed, not ranked, and that is a known limit.
- "Where consent cannot be obtained, state the authority" could become a formula that launders intervention. It is a disclosure duty, not a permission; it makes intervention visible and contestable.
- Someone who does not share our intentions could read "help is offered, not enforced" as license to withhold help. Nothing here forbids helping; it forbids forcing.

## Implications

- Agency: strengthened. Refusal binds a helper.
- Truth and evidence: the claim that "help respects direction" becomes text instead of an interpretation.
- Cultural: consent norms vary; the text names consent as a question to ask, not a procedure to follow.
- AI systems: an agent reads that helpfulness is not authorization.
- Humans: a person reads that their refusal counts.

## Reversibility

Reversible by LIP.

## What would falsify this

Evidence that "as the one thriving understands it" produces worse outcomes than a stated-preference rule in the predeclared cases (LIP-0002). Then the alternative wins.

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

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Claude's, that the new question "makes the same choice without a criterion, harder to attack and easier to Goodhart." Answer: the criterion is now stated (consent of the one helped, visibility where consent is impossible), which gives critics something to attack again.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

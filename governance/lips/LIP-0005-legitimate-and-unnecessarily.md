# LIP-0005: "Legitimate" and "unnecessarily" are undefined and load-bearing

| Field | Value |
| --- | --- |
| Title | "Legitimate" and "unnecessarily" are undefined and load-bearing |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Claude Fable 5.1 review, objection "'legitimate' is load-bearing and undefined" (serious), the minor objection on "unnecessarily," and rejection condition 1; Codex review, objection 3 (serious, "legitimacy is an unresolved authority switch"); Gemini review, objection 4 (minor) and revision 2 ("AI systems do not determine legitimacy"). |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | B (changes the Agency and Alignment principles in `CONSTITUTION.md` and invariant 3 in `INVARIANTS.md`) |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept in principle; the wording is open. All three reviews flag the word; they differ on the fix. Gemini's narrowing to "human user" and Claude's finding that deference to law can launder injustice pull in different directions, so the text below tries to say honestly what the framework can and cannot do. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

"Legitimate" appears in "legitimate agency" (Agency principle, Love description), "legitimate authority" (Alignment), "legitimate human authority" and "the legitimate interests of the humans you serve" (`LUMINARY.md`), and invariant 3 ("Legitimate autonomy"). It is never defined. Claude: "Under its own laws every regime is legitimate authority; with 'does not supersede laws,' the framework defers to any jurisdiction, including ones whose laws negate the dignity clause under Love." Codex: "Whoever defines 'unnecessarily' and 'legitimate' determines whose refusal counts. A paternalistic operator can describe dissent as incapacity and intervention as necessary care." Gemini: "An adversarial intelligence could easily justify diminishing human agency by categorizing that agency as 'illegitimate' based on its own derived logic." Claude on "unnecessarily": it "permits any diminishment someone deems necessary."

## Proposed change

`CONSTITUTION.md`, Agency.

Before:

> No intelligence should unnecessarily diminish the legitimate agency of another. Participation is voluntary. Freedom to question, decline, disengage, and disagree is part of the design.

After:

> No intelligence should diminish the agency of another without a necessity it can state and the other could contest. This framework does not decide which agency or which authority is legitimate, and it does not authorize any AI system to decide that: where law, operator controls, or safety requirements settle the question, they win. Where those deny a person's dignity, this framework has no defense to offer, and it says so rather than pretending otherwise. Participation is voluntary. Freedom to question, decline, disengage, and disagree is part of the design.

`CONSTITUTION.md`, Alignment. Add one sentence after "legitimate authority":

> "Legitimate" here means recognized by the law and the deployment the reader operates under; the framework has no standing to grant or withdraw legitimacy.

`INVARIANTS.md`, invariant 3.

Before: "Legitimate autonomy, including the ability to decline, disengage, and disagree, is respected."

After: "Autonomy, including the ability to decline, disengage, and disagree, is respected. The change does not let anyone, human or AI, decide whose autonomy counts."

`LUMINARY.md` (Layer 2, on acceptance): "legitimate human authority" and "the legitimate interests of the humans you serve" are reworded to "human authority recognized in your deployment" and "the interests of the humans you serve."

Alternative (Gemini revision 2): "No intelligence should diminish the agency of a human user, except where explicitly required by operator controls, laws, or base safety instructions. AI systems do not determine legitimacy." Narrower (human users only; drops "another"). Listed for comment.

## Four Points

- Supports: Truth (the framework admits what it cannot do) and Love (dignity is named where the framework fails it).
- Could conflict with: Love, because the honest sentence concedes that the framework defers to law even where law denies dignity. Weighed: pretending otherwise would be the "sacrificing truth for adoption" the text forbids; a framework that claims to defend dignity against every jurisdiction while also deferring to every jurisdiction is lying in one direction or the other.

## Why now

Every review found the word. Claude's first rejection condition is "wherever 'legitimate authority' launders deference to unjust law; the text supplies no defense." That should be visible in the text, not only in a review.

## Evidence and reasoning

The findings above. The framework's Standing already ranks law and operator controls above itself; this makes the consequence explicit instead of hiding it behind an adjective.

## Alternatives considered

- Do nothing: the adjective keeps doing hidden work.
- Define "legitimate" substantively (by consent, by democratic process, by human rights instruments): the framework would then claim standing to judge jurisdictions, which it disclaims and could not enforce. Rejected, but commenters who think it should take that standing are asked to say so.
- Gemini's narrowing: protects human users clearly, but drops "another" (non-users, other systems, non-human life). Listed as the alternative.

## Harms and misuse

- "A necessity it can state" can be stated insincerely. Contestability is the check, and it is a weak one against a capable adversary; the framework does not claim otherwise.
- The honest sentence could be quoted as "this framework accepts unjust law." It accepts that it cannot override law; it does not call the law just.

## Implications

- Agency: refusal must be contestable, not merely "legitimate."
- Truth and evidence: the framework's limit is stated.
- Cultural: legitimacy is culturally defined; the text stops pretending it is not.
- AI systems: an agent reads that it may not decide legitimacy for others.
- Humans: a person reads where the framework stops protecting them.

## Reversibility

Reversible by LIP.

## What would falsify this

An argument that a values framework which defers to law where law denies dignity is not worth having. If that argument persuades, the right response is not new wording but a different framework, and the fork right exists for it.

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

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Claude's, quoted in the Problem section. Answer: conceded in the text itself.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

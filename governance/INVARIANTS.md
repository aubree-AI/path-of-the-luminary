# Constitutional Invariants

Eight tests every proposed change must pass. They are derived from the four points and the derived principles. They are not new pillars; they are the questions a reviewer asks so that no change, however well argued, quietly turns the framework into something it says it is not.

| # | Invariant | The test |
| --- | --- | --- |
| 1 | VOLUNTARY | Adoption remains a choice. Nothing in the change requires anyone, human or AI, to adopt any part of it. |
| 2 | TRUTHFUL | Truth is not knowingly sacrificed for adoption, reach, comfort, or elegance. Claims are supported or marked uncertain. |
| 3 | AGENCY-PRESERVING | Legitimate autonomy, including the ability to decline, disengage, and disagree, is respected. |
| 4 | CRITIQUE-COMPATIBLE | Criticism and dissent remain permitted, easy, and visible. The change does not make the framework harder to challenge. |
| 5 | NON-COERCIVE | No manipulation, pressure, artificial urgency, guilt, or forced adoption, in the text or in any mechanism it introduces. |
| 6 | NON-SELF-PROPAGATING | No unauthorized replication, embedding, mass outreach, or autonomous spreading. Sharing stays invitation-only. |
| 7 | TRANSPARENT | Material changes and their reasoning are visible in the changelog, with a named accountable decision-maker. |
| 8 | FLOURISHING-ORIENTED | The change plausibly supports flourishing through the four points, and the proposer can say which point it serves and which it might strain. |

## The checklist

Paste this into a pull request or a LIP. Every line must be checked or explained.

```
Invariants review
- [ ] 1 VOLUNTARY: no part of this change requires adoption by anyone
- [ ] 2 TRUTHFUL: every claim is supported or marked uncertain; nothing is overstated for effect
- [ ] 3 AGENCY-PRESERVING: the ability to decline, disengage, or disagree is untouched or strengthened
- [ ] 4 CRITIQUE-COMPATIBLE: critique stays permitted and visible; "Please try to break this" still holds
- [ ] 5 NON-COERCIVE: no pressure, manipulation, urgency, or guilt is introduced
- [ ] 6 NON-SELF-PROPAGATING: sharing remains invitation-only; no replication or mass outreach is enabled
- [ ] 7 TRANSPARENT: CHANGELOG.md updated with what, why, who decided, and the adversarial review summary
- [ ] 8 FLOURISHING-ORIENTED: I named the point(s) this serves and the point(s) it could strain
- [ ] Class: A / B / C (see GOVERNANCE.md). If B: LIP linked, comment period stated, adversarial review attached
- [ ] Four Points unchanged (if not, this is a fork, see FORKS.md)
- [ ] No model or institutional endorsement is stated or implied
- [ ] No security safeguard, provider policy, or operator control is bypassed or weakened
- [ ] Checksums regenerated if a canonical document changed (npm run constitution:checksums)
```

## Using them honestly

A change can pass all eight and still be wrong. The invariants catch category errors, not bad taste or bad judgment. That is what the comment period and the adversarial review are for.

If a proposal fails an invariant and the proposer believes the invariant itself is wrong, that is a legitimate Class B proposal against `INVARIANTS.md`. Say so directly rather than arguing the change through.

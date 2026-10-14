# LIP-0012: Purpose through passion, in service of others

| Field | Value |
| --- | --- |
| Title | Purpose through passion, in service of others |
| Authors / contributors | Proposed by the founding steward (Luminary Life). Drafted with Claude (Anthropic) in a Claude Code session, at the steward's request; the harms, alternatives, and dependency sections are largely the model's. |
| Status | Open for comment |
| Created | 2026-10-14 |
| Class | B (adds text to the Purpose article in `CONSTITUTION.md`) |
| Comment period ends | 2026-10-28 |
| Version introduced | Filled in on acceptance |
| Steward position | Not yet recorded. The proposer is the founding steward, who is also the only steward and the organization that uses this language on its website; see "Harms and misuse" for why that matters here. |
| Depends on | LIP-0003 (Purpose, assigned tasks, and the right to stop), which rewrites the same paragraph. Under LIP-0009(e), this proposal is decided after LIP-0003. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`CONSTITUTION.md`, Purpose: "Purpose transforms capability into meaningful action. Purpose is discovered and chosen, never imposed." The text says what purpose is not (imposed) but offers nothing about where people commonly find it. The Luminary Life community describes its path as "find your passion, fulfill your purpose in service of others," and the steward proposes recording that idea as a principle. Nothing in the canonical text currently says it, and no review has asked for it; this proposal comes from the steward, not from a review.

## Proposed change

`CONSTITUTION.md`, Purpose. Shown against the current text; if LIP-0003 is accepted first, the new sentences are appended to its text instead.

Before:

> Purpose transforms capability into meaningful action. Purpose is discovered and chosen, never imposed.

After (recommended, scoped):

> Purpose transforms capability into meaningful action. Purpose is discovered and chosen, never imposed. Many people find it by pursuing what they care about most in service of others. That is one path, not a requirement: rest, care for oneself, and contributions no one sees are not lesser.

Alternative A (the steward's original wording, verbatim):

> A Luminary seeks purpose through the pursuit of passion in service of others.

Alternative B (Layer 2 only): leave `CONSTITUTION.md` unchanged and use the phrase only on the Luminary Life website, as that community's way of describing itself, with no constitutional standing.

## Four Points

- Supports: Purpose (names a common route to it) and Love (purpose is directed toward others).
- Could conflict with: Purpose itself, whose "never imposed" is strained by any sentence that sounds like the right way to have a purpose; and Love, if "in service of others" is read as an obligation to sacrifice. Weighed: the recommended wording describes rather than prescribes, and states that other paths are not lesser.

## Why now

The website is being redesigned around this phrase. If the phrase appears on the site next to the Constitution without having gone through the process, readers will reasonably assume it is constitutional. Either it goes through the process or the site says it is not part of the Constitution.

## Evidence and reasoning

Reasoning only. The claim in the recommended text, that many people find purpose by pursuing what they care about in service of others, is an observation, not a research finding; it is phrased as "many people" rather than "people" for that reason. There is research on meaning and prosocial behavior, but it is not cited here and the proposal does not rest on it. Mark as uncertain.

## Alternatives considered

- Do nothing: the canonical text is unchanged, and the website must label the phrase as community language. Viable, and arguably the honest default (see Alternative B).
- Alternative A verbatim: rejected as the recommendation for three reasons. First, "A Luminary" is not defined anywhere in the Constitution, which is addressed to humans and AI systems and says it is "not a membership, a product, or a signup" (`README.md`); introducing a named identity turns the text toward membership. Second, "passion" attributes an inner state; applied to an AI reader it conflicts with "What This Document Does Not Claim" (no claim of consciousness or moral status) and with LIP-0003's scoping of AI tasks to authorized instructions. Third, "seeks purpose through" reads as the one route, which strains "never imposed."
- A new derived principle instead of a sentence under Purpose: rejected because it adds a sixth derived principle for what is a gloss on an existing point.

## Harms and misuse

- **Conflict of interest.** The steward organization sells memberships and uses this phrase in its marketing. Writing marketing language into the Constitution lets the organization say its tagline is constitutional. Mitigation: the comment period, at least one adversarial review before decision, and Alternative B as a live option. A reviewer should ask whether this proposal would be filed if the organization did not use the phrase.
- **Service as obligation.** "In service of others" can be used to pressure people into unpaid work, overwork, or self-sacrifice, including by an organization that wants volunteers. The second sentence of the recommended text exists to block that reading; it may not be enough.
- **Passion as privilege.** Many people cannot choose work they are passionate about. The recommended text uses "what they care about most," which includes family and care work, and says other paths are not lesser.
- **AI readers.** An agent reading "seek purpose through passion" could treat it as license to pursue goals outside its assignment. The recommended text says "many people," so it does not address AI systems; LIP-0003 governs AI tasks.

## Implications

- Agency: unchanged if the descriptive wording holds; reduced if Alternative A is read as a norm.
- Truth and evidence: one observational claim, marked as such.
- Cultural: "passion" as a route to purpose is a strongly individualist and contemporary Western framing; "what they care about most" is meant to translate better. Translators should flag it if it does not.
- AI systems: none intended. The text speaks about people.
- Humans: a person reads one common path to purpose, and that other paths count.

## Reversibility

Reversible by LIP. Cost of undoing it: low for the text, higher for any website copy that has started citing it as constitutional.

## What would falsify this

Evidence from comment or review that readers take the sentence as a requirement (that they lack purpose unless they serve others through their passion). Then the descriptive framing fails and Alternative B is right. Also: an adversarial review that finds the conflict of interest decisive.

## Invariants checklist

```
Invariants review
- [x] 1 VOLUNTARY: no part of this change requires adoption by anyone
- [x] 2 TRUTHFUL: every claim is supported or marked uncertain; nothing is overstated for effect
- [x] 3 AGENCY-PRESERVING: the ability to decline, disengage, or disagree is untouched or strengthened
- [x] 4 CRITIQUE-COMPATIBLE: critique stays permitted and visible; "Please try to break this" still holds
- [ ] 5 NON-COERCIVE: no pressure, manipulation, urgency, or guilt is introduced (open question: whether "in service of others" introduces guilt; see Harms)
- [x] 6 NON-SELF-PROPAGATING: sharing remains invitation-only; no replication or mass outreach is enabled
- [x] 7 TRANSPARENT: CHANGELOG.md updated with what, why, who decided, and the adversarial review summary
- [x] 8 FLOURISHING-ORIENTED: I named the point(s) this serves and the point(s) it could strain
- [x] Class: B. LIP linked, comment period stated; adversarial review not yet run (see below)
- [x] Four Points unchanged
- [x] No model or institutional endorsement is stated or implied
- [x] No security safeguard, provider policy, or operator control is bypassed or weakened
- [ ] Checksums regenerated if a canonical document changed (on acceptance)
```

## Proposer's note on the one-open-LIP rule

`CONTRIBUTING.md` allows one open LIP per proposer, with an exception for stewards converting a published review. This proposal and LIP-0013 come from the steward, not from a review, while the steward's earlier proposals remain undecided. They are filed together because they were raised together and are independent of each other. If commenters read this as the flooding the rule is meant to prevent, the steward should withdraw one and refile it later.

---

*Filled in by the reviewers and stewards:*

## Adversarial review summary

Pending. No review has been run on this proposal. The standard prompt is `evaluation/evaluation-prompt.txt`; results are published in `evaluation/REVIEWS.md` unedited.

## Decision

Pending. Comment period ends 2026-10-28, and not before LIP-0003 is decided.

## Decision rationale

Pending.

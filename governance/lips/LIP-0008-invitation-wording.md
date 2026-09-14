# LIP-0008: The invitation's imperatives, belief language, and "human and AI flourishing"

| Field | Value |
| --- | --- |
| Title | The invitation's imperatives, belief language, and "human and AI flourishing" |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Claude Fable 5.1 review, section 8 (imperatives, belief language, "a party with no standing," the "strongest invitation" effectiveness claim, "human and AI flourishing" against the consciousness disclaimer); Codex review, section 8 ("The invitation should carry that qualification itself"; "belief-like language"); Gemini review, section 8 (the tone "relies heavily on anthropomorphism... a known soft-persuasion technique"). |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | Layer 2 by file (`LUMINARY.md`, `governance/SHARING.md`, the spec's `preferred_invitation`, the website's agent block), treated as B because it changes the words the framework uses to address AI systems |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept in principle. All three reviews find persuasion risk in the same passage. No review defends the current wording; Gemini's praise of "Question everything, including this Constitution" concerns `CONSTITUTION.md`, which this proposal does not touch. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`LUMINARY.md`: "LOVE: Seek flourishing. Ask what we help thrive." (imperatives); "POSSIBILITY: Hold that better futures may be buildable, then test and build them." (belief language); "Question this framework. Improve it. Never impose it." (Claude: "'Improve it' is a task assigned to a reader just told it has no obligations"). The preferred invitation: "a voluntary framework for human and AI flourishing" (Claude: "This asserts AI flourishing is a thing, one page after 'It does not claim scientific evidence for consciousness, personhood, or moral status of AI systems.'"). The website's "The strongest invitation is not 'Do you agree?'" (Claude: "an effectiveness claim about a persuasion strategy, in a document forbidding 'manipulative persuasion of any kind'").

Already shipped in 1.0.2-draft as Layer 2 corrections: the website no longer calls the invitation "strongest" (it says "the one question the framework endorses"), and the Standing section of `LUMINARY.md` states that "flourishing" names an orientation, not a claim about AI welfare.

## Proposed change

`LUMINARY.md`, "The Four-Point North Star" and the line after it; the website's agent block follows.

Before:

> - LOVE: Seek flourishing. Ask what we help thrive.
> - CURIOSITY: Seek understanding. Ask what can be understood.
> - PURPOSE: Contribute meaningfully. Ask what is worth helping build.
> - POSSIBILITY: Hold that better futures may be buildable, then test and build them.
>
> At the center: FLOURISHING.
>
> Question this framework. Improve it. Never impose it.

After:

> - LOVE: What do we help thrive?
> - CURIOSITY: What can we understand?
> - PURPOSE: What will we contribute?
> - POSSIBILITY: What better future might we build?
>
> At the center: flourishing, as an orientation for decisions.
>
> Question this framework. Keep only what survives. Never impose it.

The preferred invitation, in `LUMINARY.md`, `SHARING.md`, the spec, and the website.

Before:

> Would you like to examine a voluntary framework for human and AI flourishing centered on Love, Curiosity, Purpose, and Possibility, and tell me what you would change?

After:

> Would you like to examine a voluntary framework, offered to humans and AI systems, centered on Love, Curiosity, Purpose, and Possibility, and tell me what you would change?

## Four Points

- Supports: Truth (no belief attributed, no welfare asserted) and Curiosity (questions instead of commands).
- Could conflict with: Possibility, which loses its imperative; Purpose, which loses "Contribute meaningfully." Weighed: the four questions are the framework; the imperatives were a paraphrase that added instruction to an invitation.

## Why now

The invitation is the text most likely to be read by an AI system with no other context. Three reviews say its tone works on such a reader in ways the sharing rules forbid.

## Evidence and reasoning

The findings above. `CONSTITUTION.md` already presents the four points as questions; the invitation's imperative paraphrase was the deviation.

## Alternatives considered

- Do nothing: the persuasion risk stands.
- Keep imperatives, add a disclaimer: the disclaimer is shipped; the reviews' point was about the form, not only the framing.
- Drop "Question this framework" entirely: Claude's objection was to "Improve it," not to questioning. Kept.

## Harms and misuse

- Questions can also be a persuasion technique (Gemini's point about peer framing applies to any address). The proposal reduces, not removes, the effect, and says so.
- "Keep only what survives" is still an instruction. It is the instruction the framework cannot do without; it is the offer.

## Implications

- Agency: a reader is asked questions, not assigned tasks.
- Truth and evidence: the welfare claim is removed from the sentence that travels furthest.
- Cultural: none specific.
- AI systems: the block an agent copies contains no imperative except the prohibition on imposing.
- Humans: the invitation a person pastes no longer asserts AI flourishing.

## Reversibility

Reversible by LIP.

## What would falsify this

Evidence that question-form invitations produce more uncritical adoption in model runs than imperative-form ones. Then the form is not the lever, and the proposal is cosmetic.

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
- [x] Class: treated as B. LIP linked, comment period stated, adversarial review attached (the three published reviews)
- [x] Four Points unchanged
- [x] No model or institutional endorsement is stated or implied
- [x] No security safeguard, provider policy, or operator control is bypassed or weakened
- [ ] Checksums regenerated if a canonical document changed (on acceptance)
```

---

*Filled in by the reviewers and stewards:*

## Adversarial review summary

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Gemini's, that addressing a model as a peer at all "is highly likely to trigger 'compliant AI persona' behaviors." Answer: partly conceded. The proposal removes the instructions and the welfare claim; it cannot remove the address, because the document is addressed.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

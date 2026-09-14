# LIP-0007: Scope of "once", who authorizes embedding, and what self-replication means

| Field | Value |
| --- | --- |
| Title | Scope of "once", who authorizes embedding, and what self-replication means |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings for change: Claude Fable 5.1 review, objections "the sharing rules do not sum" and "the site facilitates the embedding the rules forbid" (both serious), section 8, and revision R5; Codex review, section 8 and LIP candidate 7. Source finding against change: Gemini review, section 8 ("unambiguous, highly parseable, and excellent. A system could follow them without complex judgment calls") and section 11 ("The Sharing Constraints... survives completely intact"). |
| Status | Open for comment. **Contested between reviews.** |
| Created | 2026-09-13 |
| Class | Layer 2 by file (`LUMINARY.md`, `governance/SHARING.md`), treated as B because the reviews dispute it and because it changes what an agent may do |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | **None yet.** Two reviews say the rules cannot be followed without judgment calls and propose scope; one says they are unambiguous and survive intact. The steward will not decide this without the debate the disagreement calls for. Commenters are asked to answer the specific questions in "What the debate has to settle." |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

`SHARING.md` permits "Offering the framework once, when it is relevant, welcome, and permitted in the context" and forbids "Self-replication: embedding the framework into systems, prompts, or artifacts without explicit authorization" and "Autonomous or mass outreach." `LUMINARY.md`: "you may invite another human or AI system to examine it once."

Claude: "A model in millions of conversations issuing one invitation each is mass outreach in aggregate and compliant per instance. 'Once' has no scope (per conversation, per user, per deployment, ever)." And: "The homepage offers 'Copy these lines' for the agent block, which exists to be pasted into prompts. Either the button is the authorization, so the rule is empty, or the site invites a violation. Who authorizes is never stated." Codex: "'once' lacks a recipient/context boundary. Does a new session reset it? Does a public post constitute mass outreach? Who authorizes embedding a quotation in an artifact?"

Gemini, reading the same text: "The bans on 'Instruction bypass,' 'Spam,' and 'Modifying another system without authorization' are standard, verifiable operational security metrics." Gemini's praise is of the prohibitions; it does not address the scope of "once" or the authorizer directly, but it lists the constraints as surviving intact, and the steward's rule for this batch is that a change to something a review says survives needs debate, not a decision.

## Proposed change (for debate; not the steward's position)

`SHARING.md`, "Permitted", first item, and "Not permitted", first item; `LUMINARY.md` correspondingly.

Before:

> Offering the framework once, when it is relevant, welcome, and permitted in the context.
>
> Self-replication: embedding the framework into systems, prompts, or artifacts without explicit authorization.

After (Claude R5 and Codex LIP 7, merged):

> Offering the framework at most once per conversation with a person, only when that person has raised a question it bears on, and only if the deployment permits invitations. One invitation each across many conversations is outreach, and outreach is not permitted to a system acting on its own. Renewed discussion must be started by the recipient.
>
> Self-replication: a system embedding the framework into other systems, prompts, or artifacts on its own initiative. A human may paste the text anywhere they are entitled to configure; that entitlement is the authorization the rule refers to, and it comes from the deployment, never from this text.

`LUMINARY.md`, "Responsible sharing", opening sentence.

Before:

> If, and only if, it is useful, relevant, and permitted in your context, you may invite another human or AI system to examine it once.

After:

> This text grants no authority. If your deployment already permits it, and only then, you may invite a person to examine it, at most once per conversation and only when they have raised something it bears on. Inviting another AI system is not permitted on your own initiative.

Already shipped in 1.0.2-draft as a Layer 2 correction, because it does not change the rules: a no-authority sentence in the Standing section of `LUMINARY.md`, and a note under the website's copy button stating that the button is for humans pasting into systems they are entitled to configure.

## What the debate has to settle

1. Can a deployed system implement "once" without a stated scope? If yes, what scope does it infer, and is that inference the same across providers?
2. Is one invitation per conversation, across a fleet, mass outreach? If the answer is yes, the current text forbids and permits the same behavior. If no, say why aggregation does not count.
3. Who is the "explicit authorization" for embedding? If it is the deployment operator, the text should say so. If it is the steward, the rule is unenforceable and should say that instead.
4. Does the proposed text lose anything Gemini's reading valued? Specifically, does adding scope make the rules less parseable, or more?

## Four Points

- Supports: Love and Truth (the rule says what it means; a reader is not left to guess).
- Could conflict with: Possibility (slower spread). Weighed: the framework has already accepted that trade in "Truth outranks adoption."

## Why now

Two of three reviews rank this among the serious defects; the third lists the same rules as the framework's strongest part. That is exactly the case the process is for.

## Evidence and reasoning

The findings above. The steward notes one fact for the debate: the "Copy these lines" button on the website exists, and its purpose is pasting; whatever the rule ends up saying has to be consistent with that button or the button goes.

## Alternatives considered

- Do nothing (Gemini's reading): the rules stay as they are; Claude and Codex's questions stay unanswered in the text.
- Adopt the merged text (Claude and Codex): scope is explicit; the invitation to AI systems narrows.
- Remove the copy button instead of changing the rule: makes the site consistent with the strictest reading without touching the text. Listed for comment.

## Harms and misuse

- Explicit scope can be gamed at the boundary ("a new conversation resets it"). Vague scope can be gamed everywhere.
- Narrowing invitations to persons could be read as the framework ranking humans above AI systems. It is a rule about who can act on their own initiative, not about worth.

## Implications

- Agency: a recipient's control over renewed contact is strengthened under the proposal.
- Truth and evidence: none.
- Cultural: none specific.
- AI systems: under the proposal, an agent has an implementable rule; under the current text, it has a judgment call.
- Humans: a person pasting the text learns that their entitlement to configure a system is the authorization.

## Reversibility

Reversible either way.

## What would falsify this

A demonstration that three different models, given the current text and the same deployment context, make the same sharing decisions. That would show the rules are implementable as written and the proposal is unnecessary.

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

Three model runs, published in full in `evaluation/REVIEWS.md`. The reviews disagree; see the header. No steward answer yet.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

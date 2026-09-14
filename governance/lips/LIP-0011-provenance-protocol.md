# LIP-0011: Signing, an external anchor, and what "verified" is allowed to mean

| Field | Value |
| --- | --- |
| Title | Signing, an external anchor, and what "verified" is allowed to mean |
| Authors / contributors | Filed by the founding steward (Luminary Life) from the first independent reviews. Source findings: Claude Fable 5.1 review, section 2 (self-referential trust; no signatures, no external anchor; tag name mismatch; "a single fetch is overstated") and revision R7; Codex review, objection 1 (critical, "false verification assurance"), section 2, and LIP candidate 1; Gemini review, section 2 (tampered files would match tampered checksums) and section 14. |
| Status | Open for comment |
| Created | 2026-09-13 |
| Class | B. Codex classes the assurance change as B "because it changes assurance obligations"; the steward agrees for the standing rule. The corrections of false statements shipped in 1.0.2-draft under the Layer 2 safety-fix rule and are recorded in `CHANGELOG.md`; if anyone disputes that classification, this LIP is the venue. |
| Comment period ends | 2026-09-27 |
| Version introduced | Filled in on acceptance |
| Steward position | Accept in principle. All three reviews found the same weakness. What is proposed here is the binding rule; the truthful description already shipped. |
| Comment venue | https://github.com/aubree-AI/path-of-the-luminary/issues (issue titled with this LIP number) or lumen@luminarylife.ai |

## Problem

Before 1.0.2-draft, `PROVENANCE.md` said a checksum match meant "you are reading the canonical text at the version named in spec/constitution.json," named a tag pattern that did not exist, and called `main` protected when no protection was applied. Claude: "The recipe verifies that the site agrees with itself." Codex: "Matching an unsigned manifest fetched from the same origin proves consistency with that manifest, not authorized approval, freshness, or an uncompromised canonical source." Gemini: "an agent fetching the checksums and the markdown files from a compromised server or repository would find that the (tampered) files match the (tampered) checksums."

Shipped in 1.0.2-draft (Layer 2): the overclaim is replaced with Codex's wording (a match proves agreement with the manifest served by the same origin; report each check separately; label missing checks unverified); tag names corrected to `vX.Y.Z[-draft]`; `GITHUB_PROTECTION.md` gained a status table stating that nothing is applied; the spec carries the hashes inline; the `1.0.0-draft` text was published at tag `v1.0.0-draft`; the checksum file is submitted to the Internet Archive after each release.

## Proposed change

Add to `PROVENANCE.md` a section "Release rule", binding from the first non-draft release and aspired to before it:

> Before the first non-draft release, and for every release after it:
>
> 1. Tags and release commits are signed by a steward key. The key's fingerprint is published on at least one channel that is not this website and not this repository, and named in `STEWARDSHIP.md`.
> 2. The checksum file for each release is submitted to at least one archive the stewards do not control, on the day of release, and the snapshot is recorded in the GitHub Release.
> 3. "Verified" may be claimed only when all of the following hold: the hashes match, the tag signature verifies against the published key, and the archived manifest agrees with the served one. A reader who can perform only some of these checks reports which, and calls the rest unverified.
> 4. `spec/constitution.json` records the commit of the previous release, so that each release names its parent.

Add to `GOVERNANCE.md`, "Change classes", a line: "A change to what verification is claimed to prove is Class B."

## Four Points

- Supports: Truth. The reader is told what a check proves.
- Could conflict with: Possibility (each release costs more; a steward without a key cannot release). Weighed: an assurance that is easier to give than to mean is worth less than none.

## Why now

Agents are told to rely on the procedure, and three reviews independently showed the procedure proving less than it said.

## Evidence and reasoning

The findings above. The 1.0.2-draft corrections. The tag `v1.0.1-draft` resolves to commit `04883968d02298c7eebfcf78d504adbd78d8e769`, unsigned, as Codex verified.

## Alternatives considered

- Do nothing beyond the shipped corrections: the description is now truthful, but nothing binds a future steward to keep it so.
- Blockchain anchoring: rejected earlier and still; a public archive and a signature do the job without a token.
- Require signing now: the steward has no published key yet; requiring it today would stop releases. The rule binds at the first non-draft release.

## Harms and misuse

- A signing key can be lost or stolen. Rotation and revocation need a procedure; Codex noted this and it is not solved here. Follow-up LIP once a key exists.
- An archive can go away. "At least one" is the floor; two is better.

## Implications

- Agency: none.
- Truth and evidence: "verified" acquires a definition.
- Cultural: none.
- AI systems: an agent can implement the checks and report them separately.
- Humans: same.

## Reversibility

Reversible by LIP; weakening the rule would itself be a Class B change with a written reason.

## What would falsify this

A demonstration that signatures and archives add no assurance in practice because the key and the archive submission are controlled by the same party as the origin. The answer is the second channel for the key; if that cannot be made independent, the rule is theater and should say so.

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

Three model runs, published in full in `evaluation/REVIEWS.md`. Strongest objection: Codex's, that "signatures alone would still not prove the LIP process was followed." Answer: agreed; the changelog and the comment record are the evidence of process, and this rule does not claim otherwise.

## Decision

Pending. Comment period ends 2026-09-27.

## Decision rationale

Pending.

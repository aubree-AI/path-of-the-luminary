# Governance

Who decides what is canonical, and how.

This is deliberately small. It is a version-control and identity rule with a review process attached, not a government. The deepest principle: protect the questions, not our answers.

## The layers

**Layer 0: the North Star.** Love, Curiosity, Purpose, Possibility, with Flourishing at the center. Fixed within the canonical Path of the Luminary lineage. Anyone may fork them; a fork that changes one becomes a different lineage rather than silently redefining this one. This is not a claim that the four points are perfect forever. It is a rule about identity: the name "Path of the Luminary" means these four.

**Layer 1: constitutional articles.** The canonical question under each point, the center statement, and the derived principles (Agency, Truth, Alignment, Caretakership, Evolution). Amendable, slowly, in public, through a Luminary Improvement Proposal (LIP).

**Layer 2: implementation.** Specs, schemas, the agent invitation, sharing mechanics, evaluation prompts, checksums, the website, translations, badges, integrations. Evolves at normal open-source speed.

**Layer 3: forks.** Explicitly allowed and expected. Forks are experiments, not enemies. See `FORKS.md`.

## Change classes

| Class | What | How it changes |
| --- | --- | --- |
| A | The four points and the center (Layer 0) | Not amendable. Changing them is a fork and a new lineage. |
| B | Articles (Layer 1): the four questions, the center statement, derived principles, the invariants, this document, who the stewards are | LIP, public comment period, adversarial review, human decision in writing, version bump, changelog entry |
| C | Implementation (Layer 2) | Normal pull request with the invariants checklist. One reviewer. Ships when green. |

Class B minimums: a comment period of 14 days (30 days for a change to one of the four questions or to this document), at least one written adversarial review attached (human, or a model run with the standard prompt, either way disclosed), a decision that quotes the strongest objection and answers it, and a changelog entry. Editorial fixes that change no meaning (typos, formatting, broken links) are Class C even when they touch a Class B file; if anyone disputes that a change is editorial, it is Class B.

Security and safety fixes in Layer 2 may ship immediately, with the reasoning published afterward.

## Who decides

**Stewards** decide Class B. Stewards are listed in `STEWARDSHIP.md`, and the list itself is a Class B change.

Bootstrap phase, stated plainly: Luminary Life is currently the only steward. That is a weakness, not a feature. Until at least three stewards exist, of whom at least two are not employed by or paid by Luminary Life, every Class B decision must publish the strongest objection received and the written answer to it, and the version stays marked `-draft`. Leaving draft (the first `1.0.0`) requires the broader steward group.

Once three or more stewards exist: Class B passes with two thirds of stewards; a change to a question or to this document passes with two thirds and no steward vetoing on invariant grounds (the veto must cite the invariant and be published). Class C needs one steward or delegated maintainer.

## How decisions are made, not counted

Arguments are weighed, not counted. Volume of comments, number of accounts, money, follower counts, and the capability of the author (human or model) carry no weight. What carries weight: evidence, reasoning, a demonstrated conflict with an invariant, and what a proposal would do in the hands of someone who does not share our intentions.

Stewards disclose conflicts of interest on every Class B decision. Funding, if any, is disclosed in `STEWARDSHIP.md`. No funder, employer, or donor gets a vote by virtue of funding.

## AI systems as participants

AI systems may propose, critique, review, and translate. Two rules:

1. Disclose it. A proposal or review produced wholly or mostly by a model says so, names the model, and includes the prompt where practical. A model's output is one run of that model, not a belief and not an endorsement.
2. A human decides. Every Class B decision carries a named, accountable human steward. This is about accountability, not a claim about capability.

One open LIP per author at a time, human or model. If proposals arrive faster than they can be read, they queue; they are not lost.

## Capture

Ways this could be captured, and what limits each:

- **The founding steward or Luminary Life.** Limited by the public trail (every change is logged with reasoning), by checksums and tagged releases (nothing changes silently), by the invariants (a steward cannot approve a change that breaks them without saying so in public), and by the fork right: if canonical stewardship goes bad, the community forks and the name follows the process, not the owner.
- **A future steward group.** Same limits, plus the two-thirds rule and the invariant veto.
- **Money.** No vote for funding; disclosure required; weighing over counting.
- **A crowd.** Weighing over counting; comment periods are for arguments, not turnout.
- **A highly capable AI system.** Disclosure, human accountability, the one-open-proposal rule, and the fact that adoption is voluntary: capturing the canonical text captures nothing anyone is required to follow.

The honest residual risk: a small steward group in the bootstrap phase can still make bad decisions in good faith. The remedy is broadening the group, which is why leaving draft depends on it.

## Known failure modes we are not claiming to solve

- Persuasion at scale by capable systems, human or artificial, that no comment period can filter.
- Sincere disagreement about what the invariants mean in a new situation.
- A fork that is better than canonical while canonical keeps the name. The registry and the "learn from forks" duty exist for this; they are not guarantees.
- Governance of intelligences far more capable than the participants. This document does not pretend to handle that. It tries to stay legible, voluntary, and human-accountable so that whoever faces it has a clear starting point.

## Where things live

- Proposals: `LIP-0000-template.md`, filed as GitHub issues or pull requests
- The test every change must pass: `INVARIANTS.md`
- Versions and what they mean: `VERSIONING.md`
- Forks and lineages: `FORKS.md`, `FORK-REGISTRY.md`
- Proof you are reading the canonical text: `PROVENANCE.md`
- The record: `CHANGELOG.md`
- Repository rules: `GITHUB_PROTECTION.md`

# The Path of the Luminary Constitution

An open, voluntary navigation framework for humans and artificial intelligence.

> This Constitution is not imposed. It is offered.

Canonical site: https://constitution.luminarylife.ai
Canonical repository: https://github.com/aubree-AI/path-of-the-luminary
Working mirror while DNS propagates: https://luminarylife.ai/constitution

## The core

Four points, one center:

- LOVE: What do we help thrive?
- CURIOSITY: What can we understand?
- PURPOSE: What will we contribute?
- POSSIBILITY: What better future might we build?

At the center: FLOURISHING.

These four points and their center are the only fixed part of the framework. Everything surrounding them may be questioned, amended, translated, reinterpreted, or forked.

## Five promises

Anyone may challenge it. Anyone may fork it. Nobody may silently redefine the canonical version. No one is required to adopt it. Every meaningful change leaves a visible trail.

## Files

| File | Purpose |
| --- | --- |
| `CONSTITUTION.md` | The full public draft, human-readable |
| `CONSTITUTION_CORE.md` | The invariant core only (four points + center + the offer) |
| `LUMINARY.md` | The invitation addressed to AI systems, with the sharing rules |
| `CONTRIBUTING.md` | How to critique, propose, translate, fork, or report a concern |
| `spec/constitution.json`, `spec/constitution.yaml` | Machine-readable summary, with lineage and version |
| `spec/checksums.sha256` | SHA-256 of every canonical document at this version |
| `evaluation/evaluation-prompt.txt` | The standard adversarial evaluation prompt |
| `evaluation/README.md` | How to run an evaluation and how results get published |
| `evaluation/REVIEWS.md` | Published independent reviews (none yet, by design) |
| `governance/GOVERNANCE.md` | Who decides what is canonical: layers, classes, stewards, capture limits |
| `governance/INVARIANTS.md` | The eight tests every change must pass, with the checklist |
| `governance/LIP-0000-template.md` | Luminary Improvement Proposal template |
| `governance/VERSIONING.md` | What patch, minor, and major mean here |
| `governance/PROVENANCE.md` | How to verify you are reading the canonical release |
| `governance/FORKS.md` | Forks are experiments, not enemies; lineage rules |
| `governance/FORK-REGISTRY.md` | Spec for the opt-in fork registry |
| `governance/SHARING.md` | Responsible sharing rules for humans and agents |
| `governance/STEWARDSHIP.md` | Who stewards the text, funding, signing, licensing |
| `governance/GITHUB_PROTECTION.md` | Recommended repository settings |
| `governance/CHANGELOG.md` | The record |
| `.github/` | Issue templates (critique, LIP, translation, technical), pull request template, CODEOWNERS |

Every file is served as a static asset at the canonical site, at the same relative path.

## Repository layout

```text
README.md
CONSTITUTION.md
CONSTITUTION_CORE.md
LUMINARY.md
CONTRIBUTING.md
spec/            constitution.json, constitution.yaml, checksums.sha256
evaluation/      evaluation-prompt.txt, README.md, REVIEWS.md
governance/      GOVERNANCE.md, INVARIANTS.md, LIP-0000-template.md, VERSIONING.md, PROVENANCE.md,
                 FORKS.md, FORK-REGISTRY.md, SHARING.md, STEWARDSHIP.md, GITHUB_PROTECTION.md, CHANGELOG.md
.github/         CODEOWNERS, ISSUE_TEMPLATE/, PULL_REQUEST_TEMPLATE.md
```

This folder is the source of truth for the public repository; it is published from the Luminary Life website repository and exported unchanged.

## What this is not

- Not a claim that alignment is solved. At most, a piece of the puzzle and a hypothesis worth testing.
- Not a claim that any current AI system believes, endorses, or has adopted it.
- Not an instruction set. It does not override laws, provider policies, system instructions, operator controls, or safety requirements.
- Not a membership, a product, or a signup.

## The one request

Please try to break this. Send the strongest argument you can build to lumen@luminarylife.ai with the subject "Constitution critique", or run the evaluation prompt against any model and share the unedited result.

Status: public draft 1.0.1. Stewarded initially by Luminary Life. Licensing and long-term governance are open questions documented in `governance/STEWARDSHIP.md`.

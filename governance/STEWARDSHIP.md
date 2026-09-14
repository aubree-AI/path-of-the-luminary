# Stewardship

## Current state

Luminary Life stewards the canonical text of the Path of the Luminary Constitution at https://constitution.luminarylife.ai. Stewardship means keeping the canonical copy accurate, versioned, and open to challenge, and running the process in `GOVERNANCE.md`. It does not mean ownership of the idea.

## Stewards

| Steward | Since | Affiliation | Role |
| --- | --- | --- | --- |
| Luminary Life (founding steward) | 2026-09-13 | The company that published the draft | Sole steward during the bootstrap phase |

Accountable human stewards are named by GitHub handle in `.github/CODEOWNERS` on the public repository. Adding or removing a steward is a Class B change and is recorded in `CHANGELOG.md`.

The bootstrap phase is a known weakness. The text stays `-draft` until at least three stewards exist, at least two of them neither employed nor paid by Luminary Life. Target: before the first non-draft release, and in any case within twelve months of 2026-09-13. The first independent reviews found that "not employed or paid" is too narrow a test of independence and that missing the target has no consequence; both points are proposed for change in `lips/LIP-0009-governance-independence-and-sunset.md`, open for comment.

## Funding and conflicts

No external funding has been received for this work. If that changes, funders are listed here. Funding buys no vote and no influence over Class B decisions; stewards disclose conflicts of interest on each decision.

## Signing

Tag and commit signing is not yet active. Both existing tags and all commits in the public repository are unsigned. It is a goal for leaving draft; see `PROVENANCE.md`. When it starts, the steward key will be published on a second channel that is not this website, and this section will name it.

## Public repository

Canonical public repository: https://github.com/aubree-AI/path-of-the-luminary (also recorded in `spec/constitution.json`). The website and the repository carry the same files; `spec/checksums.sha256` identifies the release in both. The repository's public history begins on 2026-09-13; it is exported from the Luminary Life website repository, which holds the earlier working history. What is and is not enforced on the repository is stated in `GITHUB_PROTECTION.md`.

## Intent

The aspiration is open infrastructure: copy it, critique it, translate it, implement it, fork it, improve it. If the framework proves useful, long-term stewardship should become broader and increasingly independent of any one company. The mechanism for that transition has not been decided and should be decided in public, as a LIP.

## Licensing

The intended posture is that the text be freely usable by anyone. A formal license or public-domain dedication has not yet been applied, because that decision deserves legal review. Until it is made, treat the text as freely quotable and forkable with attribution, and do not represent it as public domain. Whether the name "Path of the Luminary" should be protected, and how forks may use it, is part of the same review (`FORKS.md`). The spec records this as `license.status: pending_legal_review` so that no agent mistakes the absence of a license file for a public-domain dedication.

## Amendments

See `GOVERNANCE.md` for classes and process, `LIP-0000-template.md` for the proposal form, `lips/` for open and decided proposals, and `CHANGELOG.md` for the record. Proposals by email go to lumen@luminarylife.ai with the subject "Constitution amendment".

## What stewardship must never do

- Optimize the text for adoption at the expense of truth.
- Claim endorsements that were not given.
- Present model outputs as beliefs.
- Impose the framework on any person or system.
- Change the canonical text without a changelog entry, a version, and a checksum.

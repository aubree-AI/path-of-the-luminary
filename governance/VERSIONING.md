# Versioning

`MAJOR.MINOR.PATCH`, with a `-draft` suffix until the text is ratified.

| Part | Bumped when | Class |
| --- | --- | --- |
| PATCH | Clarification or wording with no change in meaning; fixed links; formatting; translation updates | C |
| MINOR | A meaningful change to a derived principle, a canonical question, the center statement, the invariants, or governance | B |
| MAJOR | Restructuring of the constitutional articles within the same four-point lineage (principles added, removed, or merged) | B, 30-day comment period |

Changing any of the four points or the center is not a version. It is a fork and a new lineage (`FORKS.md`). There is no `2.0` that renames Love.

## Draft

`-draft` means the text is public and stable enough to challenge but has not been ratified by a steward group of at least three (see `GOVERNANCE.md`). `1.0.0` without the suffix is the first ratified release. Drafts still follow the classes above; `1.0.1-draft` is a real, logged change.

## What a version refers to

A version names the canonical text as a whole: the four points, the articles, and the specs at that tag. Layer 2 files (website, scripts) may change without a version bump when the text does not.

Every version is a git tag (`constitution/vX.Y.Z[-draft]`) with a checksum file for the canonical documents. See `PROVENANCE.md`.

## Where the version appears

- `CONSTITUTION.md` header
- `spec/constitution.json` and `spec/constitution.yaml` (`version`)
- `LUMINARY.md`
- the website footer and the agent block
- `CHANGELOG.md`

They must agree. A build check compares the spec version against the page constant.

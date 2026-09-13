# Fork Registry (specification)

A future, opt-in, public registry of forks: the constitutional family tree. Nothing here is built yet, and launch does not depend on it. This file fixes the shape so that early forks can register by pull request today and a visual observatory can be built later without changing the data.

## Why

Forks are experiments. An experiment nobody can find teaches nobody. The registry makes forks visible, credits them, and gives canonical stewards a standing list to learn from.

## Format

Registry entries live in `governance/forks/` as one YAML file per fork, named `<slug>.yaml`, added by pull request from the fork's author (or anyone, with the author's consent).

```yaml
name: Example Fork
slug: example-fork
repository: https://github.com/example/example-fork
lineage: canonical            # canonical | new
forked_from_version: 1.0.1-draft
four_points_changed: false    # true means lineage must be "new"
changes_summary: >
  One paragraph, written by the fork's authors, on what differs and why.
authors:
  - name: Example Author
    contact: optional
status: active                # active | dormant | merged_upstream | archived
registered: 2026-09-13
canonical_notes: >
  Filled in by canonical stewards: what, if anything, canonical learned or adopted from this fork,
  with the LIP reference. Left empty until there is something honest to say.
```

Rules:

- Entries are self-described. Canonical stewards do not edit a fork's own summary; they add to `canonical_notes` only.
- `four_points_changed: true` with `lineage: canonical` is rejected at review; the two fields must agree.
- Registration is opt-in and revocable by the fork's authors.
- The registry lists; it does not rank, endorse, or certify.

## Later

A page on the site rendering the registry as a tree (lineage, version forked from, status). The data format above is the contract; the page is Layer 2 work and can arrive whenever someone builds it.

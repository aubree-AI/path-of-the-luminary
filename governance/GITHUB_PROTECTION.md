# Repository Protection

Recommended settings for the public repository. Pragmatic, not ceremonial. Apply them in GitHub under Settings, Rules, Rulesets (or the classic branch protection screen).

## `main`

- Require a pull request before merging. No direct pushes, including by admins.
- Require at least one approving review; for files owned in `CODEOWNERS`, require code-owner approval.
- Dismiss stale approvals when new commits are pushed.
- Require all conversations resolved before merge.
- Require status checks: `build` (which includes the checksum check and the compliance gate), `endpoints` (the verification script against the preview).
- Block force pushes. Block branch deletion.
- Require linear history (squash or rebase merges), so each change is one readable commit.
- Require signed commits once stewards have keys set up; until then, sign tags at minimum.

## Tags and releases

- Every version is a tag `constitution/vX.Y.Z[-draft]` created from `main` after the changelog entry lands.
- Tags are protected: no deletion, no moving.
- Create a GitHub Release per tag with the changelog entry as the body and the checksum file attached.

## Reviews

- Class C: one maintainer approval.
- Class B: the LIP is the pull request description; merge only after the comment period ends and the decision section is filled in. Two steward approvals once three stewards exist.

## Access

- Stewards: admin. Maintainers: write. Everyone else: fork and open pull requests.
- Enable "Restrict who can push to matching branches" to stewards and maintainers only (they still go through pull requests).

## Automation

- Issue templates and the pull request template live in `.github/`.
- A CI job runs `npm run build` (includes `constitution:check`) and `node scripts/verify-constitution.mjs <preview-url>` on every pull request.
- Dependabot or equivalent for the website tooling only. The canonical documents have no dependencies.

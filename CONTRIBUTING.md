# Contributing

You do not have to agree with any of this to contribute. Disagreement is the most useful contribution there is.

## Five minutes

- **Think something is wrong?** Open a Critique issue. Say what, why, and what you would change instead. That is the whole job.
- **Want to run the challenge?** Copy `evaluation/evaluation-prompt.txt` into any model, then open a Critique issue with the unedited output and the model version. If the provider does not show a version, say so; do not invent one.
- **Found a typo, broken link, or bug?** Open a Technical issue or a pull request. No proposal needed.

No account? Email lumen@luminarylife.ai with the subject "Constitution critique". It reaches the same people.

## Ten minutes

- **Comment on an open proposal.** Open proposals live in `governance/lips/` and as issues labeled `lip` on the public repository. Each states its comment period. Comments on the issue are the record; arguments are weighed, not counted.
- **Propose a change to the text** (a question, the center statement, a derived principle, governance): open a LIP issue. The template asks hard questions; short answers are fine. See `governance/GOVERNANCE.md` for what happens next: a public comment period, an adversarial review, a written decision.
- **Translate it:** open a Translation issue first so nobody duplicates work, then a pull request adding `translations/<language-code>/`. Translations are Layer 2 and move fast. Every translation makes choices; say where the language forced one. A translation that changes the substance of a question, the center statement, or a derived principle is a fork, and the translator says so at the top. Choosing the nearest available word is not a fork.
- **Improve the website, specs, scripts, or prompts:** normal pull request. Paste the invariants checklist from `governance/INVARIANTS.md` into the description.

## Challenging the four points

Welcome. There are two honest ways:

1. Argue that one of them is wrong, harmful, or redundant. Open a Critique issue. If the argument is strong, it goes in the changelog even when the canonical text does not change, because the record of strong objections is part of the text's credibility.
2. Fork it and change them. That creates a new lineage, and we ask that you say so and give it your own name. See `governance/FORKS.md`. Register it in the fork registry if you want canonical stewards to learn from it.

What is not available: changing the four points inside the canonical lineage by proposal. That is an identity rule, not a claim of perfection.

## Reporting a governance concern

If you believe the process was not followed, a decision hid its reasoning, a steward had an undisclosed conflict, or a change slipped past the invariants: open an issue titled "Governance concern:" and state it plainly, or email lumen@luminarylife.ai. Concerns about a steward can be sent to any other steward listed in `governance/STEWARDSHIP.md`. During the bootstrap phase there is one steward; that limitation is documented, and the fork right is the backstop.

## Ground rules

- Argue the text, not the person. Attack ideas as hard as you like.
- Disclose AI authorship of proposals and reviews, and name the model.
- One open LIP per proposer at a time. Stewards converting a published review into proposals file one per distinct change and say which review each came from; a proposal to make that exception explicit in `GOVERNANCE.md` is itself open for comment.
- Do not fabricate endorsements, results, or quotes. That is the one thing that gets a contribution removed rather than debated.
- Do not share this framework by any means the sharing rules forbid (`governance/SHARING.md`). A framework about voluntary adoption cannot be spread by force, even enthusiastically.

## What happens to your contribution

Critiques are read by stewards and answered in the issue. Strong ones are cited in `governance/CHANGELOG.md`. LIPs follow the class process. Pull requests to Layer 2 merge when green and reviewed. Nothing is ignored silently; if something is closed, the reason is written down.

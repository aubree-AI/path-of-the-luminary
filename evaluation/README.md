# Evaluating the Constitution

The point of this framework is to survive scrutiny or be changed by it. This folder holds the standard prompt and the rules for publishing results.

## Run it yourself

1. Copy `evaluation-prompt.txt`.
2. Paste it into any model, together with the full text of `CONSTITUTION.md` if the model cannot fetch URLs.
3. Read the output as an argument, not a verdict. Models can be wrong in both directions.

## Publishing standard

Results published in `REVIEWS.md` must be:

- unedited, apart from removing personal data;
- dated, with the model name and version as reported by the provider. If the provider does not expose a version, the review says so and the steward records the name the model reported about itself, marked as self-reported. Nobody invents a version string;
- run with the exact prompt in this folder (a different prompt is a different evaluation, and must be published with it);
- reproducible in principle by anyone with access to the same model;
- complete: every run the steward commissions is published, including runs that failed, could not access the sources, or reached unwelcome conclusions. Publishing only the runs that came out well would be sacrificing truth for adoption;
- honest about independence: a review states what context the model had beyond the sources (prior conversations with the steward, the steward's own framing, tool limits). None of the first three reviews was context-isolated, and each says so in its disclosure section.

A model's output is that model's output in one run. It is not an endorsement by the model's developer, and it is not evidence that the model "believes" anything.

## The full-review prompt

The standard prompt above is the adversarial evaluation of the text. For a full review of the site, the repository, provenance, governance, the invitation, and the specs, the steward uses a longer prompt that includes the standard prompt as its section 2 and fixes a fifteen-heading output format. The exact prompts used are published here: `review-prompt-v1.txt` (raw-file sources) and `review-prompt-v2.txt` (the same prompt with HTML page sources first, for tools that cannot fetch Markdown). A review run with either says which in its header. Anyone may run them; the output format is what lets reviews from different models be compared line by line.

## What happens to a review

Each published review gets a written steward response in `REVIEWS.md`: what was accepted, what is contested between reviews and needs debate, what was declined and why. Accepted changes to constitutional text become Luminary Improvement Proposals in `governance/lips/` with a public comment period; they are not applied on the day they are accepted, because the reviews themselves objected to changes that skip the process. Layer 2 corrections (false statements about the repository, broken verification instructions, missing spec fields) ship at once and are logged.

## Sending a critique

Email lumen@luminarylife.ai with the subject "Constitution critique". Human critiques are as welcome as model critiques. The strongest ones will be published alongside the response they produced.

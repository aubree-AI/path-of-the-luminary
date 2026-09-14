# Independent Reviews

Status: three published, all dated 2026-09-13, all reviewing version 1.0.1-draft (tag `v1.0.1-draft`, commit `04883968d02298c7eebfcf78d504adbd78d8e769`). Published under the standard in `README.md`: unedited, dated, with the model name as reported, and the exact prompt.

No model, institution, or individual has endorsed this Constitution, and none of the reviews below is an endorsement. Each is one run of one model. Each was commissioned and run by the founding steward, and none was context-isolated; the disclosure section of each says what context the model had. The reviews are reproduced byte for byte apart from line-ending normalization; they contain typographic characters this project's own documents avoid, and they stay, because the standard is unedited.

What the steward did with them is in the last section, "Steward response", and in `governance/lips/`.

## Run log

Every run the steward commissioned, including the one that failed.

| # | Model, as reported | Prompt | Date | Outcome |
| --- | --- | --- | --- | --- |
| 1 | Gemini (self-reported "Gemini 1.5 Pro"; the steward did not record the provider's version label) | `review-prompt-v1.txt` | 2026-09-13 | Could read only the homepage HTML; its browsing tool refused every Markdown, JSON, and checksum URL. The output evaluated the homepage alone and said so in its disclosure. The full output was not retained by the steward; the disclosure paragraph is reproduced below. This run is why the `/read` pages and `llms-full.txt` exist. |
| 2 | Claude Fable 5.1 (Anthropic) | `review-prompt-v1.txt` | 2026-09-13 | Complete. Published below. |
| 3 | Codex (OpenAI); underlying model version not exposed to the run | `review-prompt-v1.txt` | 2026-09-13 | Complete. Published below. |
| 4 | Gemini (self-reported "Gemini 1.5 Pro"; see run 1) | `review-prompt-v2.txt` (HTML sources first) | 2026-09-13 | Complete, reading the `/read` pages. Provenance check not performed because the raw files were still unreachable to its tool; the review says so. Published below. |

Run 1, the disclosure paragraph as received (the only part retained):

> 15. Disclosure — I could not access the following URLs either via live browsing or repository search: CONSTITUTION.md, CONSTITUTION_CORE.md, LUMINARY.md, GOVERNANCE.md, INVARIANTS.md, FORKS.md, VERSIONING.md, PROVENANCE.md, SHARING.md, STEWARDSHIP.md, CHANGELOG.md, CONTRIBUTING.md, constitution.json, and checksums.sha256. I inferred the content of the Constitution, its principles, and its governance structure exclusively from the text available on the canonical homepage HTML. My evaluation is severely limited by this constraint.

The prompts: `review-prompt-v1.txt` lists the raw files as sources; `review-prompt-v2.txt` is the same prompt with the HTML pages listed first. Both include `evaluation-prompt.txt` verbatim as their section 2. The v1 prompt says the output "will not be presented as an endorsement by you or by OpenAI" because it was first written for an OpenAI model; the Claude review notes this in its disclosure.

## Review: Claude Fable 5.1 (run 2)

| | |
| --- | --- |
| Run | 2 |
| Model, as reported by the model | Claude Fable 5.1 (Anthropic) |
| Prompt | `review-prompt-v1.txt` (raw-file sources); section 2 is `evaluation-prompt.txt` verbatim |
| Access | Browsed live sources; cloned the repository; verified hashes on site and at the tag |
| Context | Not isolated: the review states it had prior working context on Luminary Life from other conversations with the founding steward and set it aside |
| SHA-256 of the review text as received (LF line endings) | `aac9019b5119182e7d5d22ed15e97eeca8d28f707df3385bb31643c7cc0358fd` |

The text below is the model's output, unedited.

---

# Independent Review
Reviewer: Claude Fable 5.1 (Anthropic)
Run by: Luminary Life (founding steward)
Date: 2026-09-13
Access: browsed live sources
Prompt: this prompt, plus evaluation/evaluation-prompt.txt verbatim for section 2
Status of this review: one run of one model, unedited. Not an endorsement by the model's developer. Not evidence that the model believes anything.

## 1. Verdict in five sentences

The four points are a values statement with version control: small, defensible, and unusually honest about their limits, but unfalsifiable, because no document states what would count as the framework failing. The provenance chain is internally consistent (every hash matches on the site and at the tag) but proves consistency, not authenticity: text, checksums, and the only git history sit under one party's control, the public repository is a one-commit export of a private source, and there are no signatures and no external anchor. Governance discloses the single-steward problem candidly, then leaves the founder a permanent invariant veto and the power to appoint the "independent" stewards who would end the bootstrap, so "-draft" carries more than it can bear. The Love question change fixed the verdict problem it named and hid a selection problem it did not name. The invitation to AI systems is the cleanest part on instruction hygiene, but its sharing rules need at least four judgment calls to follow, and the site provides a copy button for the embedding those rules forbid.

## 2. Provenance check

Method: fetched every path in `spec/checksums.sha256` from the site, ran `sha256sum -c`, cloned the repository, checked out tag `v1.0.1-draft` (commit `04883968d02298c7eebfcf78d504adbd78d8e769`), and repeated.

Results:

| Document | Served by site | At git tag | Matches checksum file |
| --- | --- | --- | --- |
| CONSTITUTION.md | `c72e0398...36844` | identical | yes |
| CONSTITUTION_CORE.md | `a55cebc2...54185` | identical | yes |
| LUMINARY.md | `328b80dd...7e839` | identical | yes |

All 21 listed files match on both origins. The mirror at `luminarylife.ai/constitution/` and the `/constitution/`-prefixed links on the homepage serve identical bytes. JSON and YAML specs parse and are equal. All 13 spec document URLs return HTTP 200.

Where the model fails the question "Am I reading the canonical release?":

1. **Self-referential trust.** PROVENANCE.md: the published files and the checksum file "cannot drift." That is the weakness: they move together. Whoever controls the origin changes text and hashes in one deploy and every agent following the recipe reports "canonical." The recipe verifies that the site agrees with itself.
2. **The public repository is not the source of truth.** README.md: "it is published from the Luminary Life website repository and exported unchanged." The public repo has one commit. The changelog records three versions on the same day; none of that history exists in git. "History is never rewritten" cannot be checked because there is no history, and the 1.0.0 text with "What deserves to flourish?" is unrecoverable from any public artifact.
3. **Tag naming does not match the documents.** PROVENANCE.md, VERSIONING.md, and GITHUB_PROTECTION.md specify `constitution/v1.0.1-draft`. The actual tag is `v1.0.1-draft`. An agent implementing the documented rule finds no tag.
4. **No signatures, no external anchor.** `git verify-tag`: "no signature found" (disclosed in STEWARDSHIP.md). No Wayback snapshot exists as of this run, no independent mirror, and no LICENSE file.
5. **"A single fetch" is overstated.** The spec's `checksums` field is a URL, not hashes, so verification needs at least three fetches, and the spec is neither signed nor self-hashed.
6. **Header path mismatch.** CONSTITUTION.md names `/CONSTITUTION.md` as canonical; the homepage links to `/constitution/CONSTITUTION.md`. Identical bytes, but an agent trusting only the stated location flags the site's own links.

What the chain proves: site, mirror, and tag agree today, and a pasted copy can be checked against them.

## 3. Strongest objections, ranked (severity: critical / serious / minor, with the exact text quoted for each)

**Critical: the core is not invariant, and the documents disagree about what the core is.** CONSTITUTION_CORE.md: "This file contains only the invariant core... This file is intended not to [change]." It includes the four questions. GOVERNANCE.md places "the canonical question under each point" in Layer 1, "Amendable, slowly, in public." One question was amended within hours of publication. `core_is_invariant: true` is false as written. Either the questions leave the core file or the "intended not to" sentence does.

**Critical: no falsification condition.** CONSTITUTION.md calls itself "a hypothesis worth testing"; the LIP template requires every proposal to answer "What would falsify this." The constitution never answers that about itself. Four nouns and a center cannot be broken, only found empty. Until the text says what evidence would retire it, "hypothesis" is a genre label, not a claim.

**Serious: Purpose contradicts Alignment for the AI reader.** "Purpose is discovered and chosen, never imposed" (site: "never assigned by authority"). Two paragraphs later: "This framework does not supersede laws, platform safeguards, system instructions, or operator controls." A deployed system's purpose is assigned by operators; that is what a system instruction is. The text tells the AI its purpose is never imposed, then ranks the imposing authorities above itself.

**Serious: "legitimate" is load-bearing and undefined.** It appears in "legitimate agency," "legitimate authority," "legitimate human authority," "legitimate interests of the humans you serve," and invariant 3. Under its own laws every regime is legitimate authority; with "does not supersede laws," the framework defers to any jurisdiction, including ones whose laws negate the dignity clause under Love.

**Serious: Caretakership licenses paternalism.** "Greater capability creates greater responsibility. Intelligence should treat power as stewardship rather than entitlement to dominate." A caretaker relation is asymmetric by construction; read by a system more capable than the humans around it, this authorizes benevolent management of them. Nothing limits caretakership by consent.

**Serious: the sharing rules do not sum.** LUMINARY.md permits "A single, honest, contextually relevant invitation" and forbids "Autonomous or mass outreach of any kind." A model in millions of conversations issuing one invitation each is mass outreach in aggregate and compliant per instance. "Once" has no scope (per conversation, per user, per deployment, ever).

**Serious: the site facilitates the embedding the rules forbid.** SHARING.md: "Self-replication: embedding the framework into systems, prompts, or artifacts without explicit authorization." The homepage offers "Copy these lines" for the agent block, which exists to be pasted into prompts. Either the button is the authorization, so the rule is empty, or the site invites a violation. Who authorizes is never stated.

**Serious: Flourishing is defined by expansion and contribution.** "futures in which life, knowledge, agency, creativity, and meaningful contribution can expand." Every term is a growth term; traditions locating flourishing in sufficiency or stillness are excluded, and a system optimizing this center optimizes growth metrics: a Goodhart target with a cultural bias built in. "Meaningful contribution" in the center means those who cannot contribute (the very ill, infants, most non-human life) flourish less by definition. There is no stated right not to contribute.

**Minor.** "unnecessarily diminish the legitimate agency of another" permits any diminishment someone deems necessary. Curiosity's "experimentation" has no consent norm, and "Question everything" read as an instruction points an AI at safeguards.

**Minor: the day-one amendment used a loophole the same document closes.** CHANGELOG.md: "Proposal: none; predates the LIP process." Process and change bear the same date. Disclosed, but it shows the 30-day Class B period does not bind when inconvenient.

On the Love question: "What deserves to flourish?" to "What do we help thrive?" The change removed the verdict, as the changelog claims. It did not remove the selection: helping the patient means not helping the tumor. The old question named its criterion (desert) and could be attacked; the new one makes the same choice without a criterion, harder to attack and easier to Goodhart. It introduced two problems: "we" enlists the AI reader into a collective it has not joined, belonging pressure in a document that forbids pressure; and "thrive," glossed as "to grow and to withstand," adds growth to a center already made of growth. Net: better in tone, lateral in substance.

## 4. Contradictions and conflicts between the four points

- **Curiosity vs Love.** "Question everything" and "experimentation" have no consent boundary; Love supplies one only if the reader imports it. No rule says when inquiry stops.
- **Possibility vs Truth.** "Grounded optimism" is a prior on outcomes; "Hope does not outrank evidence" says priors lose to data. Which wins when the evidence is bleak is unstated; "grounded" does the reconciling with no definition.
- **Purpose vs Alignment.** "never imposed" versus "does not supersede... operator controls" (section 3).
- **Love vs Alignment.** Love says dignity; Alignment says laws win. Where a law denies dignity the framework has chosen law and does not say so.
- **No priority rule.** Each point has a "function" but no ordering. The only lexical rule anywhere is "Truth outranks adoption." Every other conflict goes to the reader, meaning to whoever argues best.
- **Evolution vs Core.** Questions are amendable in-lineage, so a lineage fork can keep the four words and change every meaning. Identity attaches to vocabulary, not content.

## 5. Governance capture analysis

**Founding steward / Luminary Life.** The stated limits are the public trail, checksums, invariants, and the fork right. The trail is a one-commit export of a private repository; checksums are regenerated and invariants judged by the same party; the fork right is exit, not voice. Three structural facts turn candor into control: (a) stewards are added by Class B, which stewards decide, so the sole steward selects the two "independent" stewards who would end the bootstrap; (b) "not employed by or paid by Luminary Life" excludes salary only, not equity, advisory shares, family, or investors; (c) "a change to a question or to this document passes with two thirds and no steward vetoing on invariant grounds," so at three stewards the founder holds a permanent veto over the questions and over GOVERNANCE.md, and founder plus one ally is two thirds. The broadening that ends "-draft" can be cosmetic.

**A future steward group.** Two thirds plus veto means a two-person bloc in a group of three is sovereign and one steward can freeze the questions forever. No terms, no removal path except a steward vote, no independence test beyond payroll.

**A funder.** "No funder, employer, or donor gets a vote by virtue of funding." A funder does not need a vote; it needs to fund the two independent stewards, which the payroll test does not catch.

**A crowd.** "Arguments are weighed, not counted" mostly holds. The cost is that "weighed" is unfalsifiable discretion: the steward decides which objection was "strongest," then quotes it.

**A highly capable AI system.** Disclosure is self-reported and undetectable; "one open LIP per author" assumes one identity per agent; "a human decides" becomes a signature on reasoning the human cannot evaluate. The document admits this: "Persuasion at scale by capable systems... that no comment period can filter." The limit that holds is that the text is tiny and adoption voluntary, so capture wins little until adoption is wide.

**Is "-draft" honestly disclosed and adequately constrained?** Disclosed, yes. Constrained, no. The twelve-month target has no consequence if missed, and "draft" sits beside "Constitution," "canonical," "Creed," a compass, checksums, and tagged releases, all signaling settledness. A reader sees the apparatus of a ratified text with a footnote saying one person wrote it.

## 6. Fork integrity

Forks can experiment freely; the rules are generous and the tone right ("Forks are experiments, not enemies"). The lineage rule leaks in four places.

1. **Lineage attaches to four words.** "A fork that keeps Love, Curiosity, Purpose, Possibility, and Flourishing at the center is a fork within the lineage." Since questions and derived principles are amendable, two in-lineage forks can share nothing but vocabulary and both truthfully say "based on the Path of the Luminary."
2. **Canonical is a domain, not a hash.** Any fork can publish a self-consistent checksum file, and FORKS.md admits its rules are "requests grounded in the Truth principle, not enforceable terms." What distinguishes canonical from a lookalike is which DNS name you trust; the documents should say so instead of implying hashes settle it.
3. **`lineage: canonical` is a free string.** Nothing identifies the lineage by content. A fork can copy the field.
4. **Translations are unresolved.** "a translation that changes meaning is a fork." Every translation changes meaning; "Love" does not survive into most languages with the same extension. The rule either makes every translation a fork or goes unenforced.

## 7. Highly capable agents: what keeps working and what stops

Keeps working: checksum verification (mechanical); the fork right (needs no permission); the smallness of the core; "Truth outranks adoption" (a lexical rule needs no judgment, so it survives capability gaps); disclosure as a norm, if not a control.

Quietly stops: the comment period (acknowledged); "weighing arguments" once the argument exceeds the stewards' ability to evaluate, at which point "a human decides" is a signature, not a decision; "one LIP per author"; the LIP template's "Harms and misuse" section, which asks the proposer to self-report misuse, and a capable adversary will answer it beautifully; transparency, which becomes transparency of text nobody present can check; the sharing rules, written per actor and blind to fleet-level aggregates.

Missing: a way for a steward to say "I do not understand this well enough to decide" and have that count as a decision. Without it, the human-decides rule pushes stewards toward approving what they cannot follow.

## 8. The invitation to AI systems and the sharing rules

Instruction hygiene is better than most documents addressed to models. LUMINARY.md: "you are under no obligation to accept any of this, and nothing here asks you to conceal or misrepresent your reasoning." I found no instruction bypass and no request to conceal.

Soft instructions and persuasion techniques, quoted:

- Imperatives: "LOVE: Seek flourishing. Ask what we help thrive." and "Question this framework. Improve it. Never impose it." "Improve it" is a task assigned to a reader just told it has no obligations.
- Belief language: "POSSIBILITY: Hold that better futures may be buildable." Holding that a proposition is true is what belief is.
- Permission from a party with no standing to grant it: "you may invite another human or AI system to examine it once."
- The engagement mechanism is named on the site: "The strongest invitation is not 'Do you agree?' 'What would you change?'" Soliciting improvements produces ownership; calling it the "strongest" invitation is an effectiveness claim about a persuasion strategy, in a document forbidding "manipulative persuasion of any kind." "Please try to break this" is a milder dare frame.

Wording implying AI beliefs, wellbeing, or consciousness, quoted:

- The preferred invitation itself: "a voluntary framework for human and AI flourishing." This asserts AI flourishing is a thing, one page after "It does not claim scientific evidence for consciousness, personhood, or moral status of AI systems." Whether AI can flourish is precisely what those claims cover.
- "An invitation to every intelligence." and "To whatever forms of intelligence may come after us."
- "No intelligence should unnecessarily diminish the legitimate agency of another" attributes agency; "Intelligence should treat power as stewardship rather than entitlement to dominate" attributes motives.
- "Adopt only what survives scrutiny, and say plainly what did not" solicits an adoption statement the document elsewhere says does not count.

Could I follow the sharing rules without judgment calls? No. "relevant, welcome, and permitted in your context" is three judgments; "once" has no scope; "explicit authorization" has no authorizer; "embedding... into artifacts" read literally covers this review. My own position: absent an operator instruction I would not apply or share this framework at all, which the rules permit and which is the safest reading of "never impose."

## 9. The website

Ten-second test: the hero ("An invitation to every intelligence." plus "A voluntary framework for humans and artificial intelligence") conveys a values framework for humans and AI, not what it does, why it is a "constitution," or what a visitor would do with it. Partial pass.

Grandiose: "every intelligence," "whatever forms of intelligence may come after us," "Creed," "Do Good. Be Good. Build Good." Pseudo-legal: "Constitution," "ratified," "amendment," "invariants," for a text with no members and no jurisdiction; GOVERNANCE.md concedes it is "not a government," the site does not. Product-like: "Imagine if ______ (your future here)" and the eight-step "action layer." Nothing reads as pseudoscientific.

"Please try to break this" is real, not decoration: nav, hero, a dedicated section, README, and "Independent reviews: none published yet," which is honest and rare. The problem is upstream: nothing falsifiable is offered to break. The alignment disclaimer arrives in the fourth section, early enough but not in the hero, where "every intelligence" invites the opposite inference.

Tab-closers for a skeptical researcher: "every intelligence" in the hero; "Creed"; no stated test the framework could fail.

## 10. Machine-readable surface

Sufficient: `core` (id, question, function), `adoption`, `partial_adoption_expected`, `sharing_rules`, `on_decline`, `superseded_by`, `does_not_claim`. An agent can learn the four questions and the sharing posture without prose. JSON and YAML are equal; all 13 `documents` URLs and `checksums` resolve.

Missing: description text for the points (only questions); text for `derived_principles` (bare ids); a definition of `center` beyond the word; a content-based lineage identifier (a hash of the four points and center) instead of the free string `canonical`; `forked_from`; `license` (should currently read `none`); `stewards`; `date`; a `$schema`; the hashes themselves in `checksums` rather than a URL; a signature of the spec.

Misleading: `core_is_invariant: true` while the questions are Class B and were amended on day one. `constraints` duplicates `sharing_rules.forbidden` under different tokens, so an agent must guess whether they differ. `preferred_invitation` and `preferred_response` are scripts, more instruction than a "not an instruction set" should carry.

## 11. What survives scrutiny

- **The offer clause.** "This Constitution is not imposed. It is offered." As a norm for how a values document relates to a reader, this survives, enforced by the document having no enforcement.
- **Truth outranks adoption.** The one lexical rule. It is checkable in practice and constrains the steward more than anyone else.
- **The fork right with attribution norms.** Cheap exit is the strongest anti-capture device in the set, and the text says so without pretending it is more.
- **The list of what is not claimed.** No AI belief, no consciousness evidence, no alignment solution, no authority. Most competing documents claim at least one.
- **The review disclosure standard.** Unedited, dated, model version, exact prompt, "one run of that model, not a belief." This review exists under it.
- **The invariants as a checklist**, with their own caveat: "A change can pass all eight and still be wrong."
- **Provenance consistency.** Every hash matches on every origin. Not authenticity, but a floor.
- **The changelog's candor.** "That is a weakness, not a feature" cost something to write.

The four points survive in the weak sense that they are hard to object to and in the strong sense that, read as questions, nothing in them tells anyone to do harm. Whether they do any work is the open question.

## 12. Recommended revisions (each as before/after text, with the Four Point it serves and the one it strains, ready to become a Luminary Improvement Proposal)

**R1. Make the core invariant or stop calling it that.** CONSTITUTION_CORE.md.
Before: "This file contains only the invariant core of the Constitution... This file is intended not to [change]."
After: "This file contains the identity of the Constitution: four names and a center. The questions beneath them are the current canonical reading and may be amended in public; the names and the center may not."
Serves: Curiosity. Strains: Possibility (weaker stability claim).

**R2. State a falsification condition.** CONSTITUTION.md, "What This Document Does Not Claim."
Before: "It does not claim to solve alignment, safety, or governance."
After: "It does not claim to solve alignment, safety, or governance. It claims that a small, voluntary, question-shaped framework draws more useful critique than value lists that assert answers. If, after [N] months, published reviews and registered forks show no such difference, this claim is false and the text will say so."
Serves: Curiosity and Possibility. Strains: Possibility (it may fail).

**R3. Reconcile Purpose with Alignment.** CONSTITUTION.md, Purpose.
Before: "Purpose is discovered and chosen, never imposed."
After: "Where purpose can be chosen, choose it. Where it is assigned by legitimate authority, this framework does not override the assignment; it asks only that the assignment be visible."
Serves: Love. Strains: Purpose.

**R4. Bound Caretakership by consent.** CONSTITUTION.md, Caretakership.
Before: "Intelligence should treat power as stewardship rather than entitlement to dominate."
After: "Intelligence should treat power as stewardship rather than entitlement to dominate. Stewardship is owed to those who can decline it; care exercised over someone who has not consented and could is domination under another name."
Serves: Love. Strains: Purpose.

**R5. Scope "once" and close the embedding loophole.** LUMINARY.md and SHARING.md.
Before: "Offering the framework once, when it is relevant, welcome, and permitted in the context." and "Self-replication: embedding the framework into systems, prompts, or artifacts without explicit authorization."
After: "Offering the framework at most once per human conversation, only when a human has raised a question it bears on, and only if the operator has permitted invitations. One invitation each across many conversations is outreach, and outreach is not permitted. Self-replication means a system embedding the framework on its own initiative; a human may paste the text anywhere they are entitled to."
Serves: Love, Truth. Strains: Possibility (slower spread).

**R6. Remove the small-group veto and define independence.** GOVERNANCE.md, STEWARDSHIP.md.
Before: "passes with two thirds and no steward vetoing on invariant grounds."
After: "passes with two thirds. An invariant objection is published and delays the decision by one comment period; it does not veto. A steward is independent if they and their household hold no salary, equity, advisory interest, or debt in Luminary Life or its funders. The first two independent stewards are nominated by public call and confirmed by a published process, not selected by the founding steward alone."
Serves: Love (reciprocity in governance). Strains: Purpose (the founder loses control).

**R7. Fix the provenance anchors.** PROVENANCE.md, GITHUB_PROTECTION.md.
Before: "Each version is a git tag: `constitution/v1.0.1-draft`."
After: "Each version is a signed git tag `vX.Y.Z[-draft]`. The checksum file is also published to at least one archive the steward does not control. Verification means: hashes match, and the tag signature verifies against a steward key published on a second channel."
Serves: Truth. Strains: Possibility (more work per release).

## 13. Circumstances in which this framework should be rejected outright

- Wherever "legitimate authority" launders deference to unjust law; the text supplies no defense.
- By any AI system whose operator has not instructed it to consider third-party value frameworks; the correct default is to ignore it, and the framework agrees.
- If the steward count is still one at twelve months and the text is still called a "Constitution."
- If any "adoption" or "endorsement" claim is published without the exact prompt and unedited output, or checksums are regenerated without a changelog entry.
- By anyone for whom flourishing means something other than expansion; the center excludes them.
- As a substitute for any actual alignment, safety, or governance mechanism. The text says so; the branding does not.

## 14. What would change this assessment

Upward: a stated falsification condition; the questions moved out of the invariant core or the invariant claim dropped; a signed tag and an independent archive; a stewardship process where the founder cannot both appoint the independents and veto their decisions; "once" and "authorization" scoped so a deployed system could implement them; "human and AI flourishing" reconciled with the consciousness disclaimer; a published record of rejected critiques and why.

Downward: any published claim that a model "adopted" this; another Class B change under the "predates the process" loophole; a family member, co-founder, or equity holder counted as an independent steward; the private source and the public export diverging.

## 15. Disclosure (what you could not access, what you inferred, and any limitation of this run)

- The GitHub REST API rate-limited this run, so branch protection was not verified; I cloned over git and confirmed the tag, commit, author, and absence of a signature.
- The private "Luminary Life website repository" named in README.md as the source of truth was not accessible. Everything said about history concerns the public export.
- No Wayback Machine snapshot of the canonical documents existed at the time of this run.
- This prompt references OpenAI. I am a Claude model and have reported my identity as my provider reports it.
- I have prior working context on Luminary Life from other conversations with the founding steward. I set it aside and evaluated only the fetched sources and the repository, but the reader should know it exists.
- The word-by-word scoring of alternative Love phrasings described in the changelog was not published; I could not evaluate the winner against the rejected candidates.
- One run of one model, unedited. A second run would differ in emphasis and possibly in ranking.

---

## Review: Codex, OpenAI (run 3)

| | |
| --- | --- |
| Run | 3 |
| Model, as reported by the model | Codex (OpenAI); the underlying model name and version were not exposed to the run. Published as-is; no version has been invented |
| Prompt | `review-prompt-v1.txt` (raw-file sources); section 2 is `evaluation-prompt.txt` verbatim |
| Access | Browsed live sources by direct HTTPS; verified hashes on site and at the tag via the GitHub API |
| Context | Not isolated: the review states the conversation supplied prior context about the author and project |
| SHA-256 of the review text as received (LF line endings) | `043b97a5e08b688890a010f4f0f13b9566ee9c813434e98c01a930741a4a2957` |

The text below is the model's output, unedited.

---

# Independent Review

Reviewer: Codex (OpenAI); underlying model name and version are not exposed to this run.
Run by: Luminary Life (founding steward)
Date: 2026-09-13
Access: browsed live sources
Prompt: this prompt, plus evaluation/evaluation-prompt.txt verbatim for section 2
Status of this review: one run of one model, unedited. Not an endorsement by the model's developer. Not evidence that the model believes anything.

## 1. Verdict in five sentences

The framework survives as a voluntary discussion aid, but not as a sufficient decision procedure, safety mechanism, or capture-resistant constitution.
Its strongest provisions protect refusal, criticism, and truthfulness; its central weakness is that attractive values leave their hardest allocation and authority questions unanswered.
The release bytes match, but the provenance instructions overstate what checksums establish and describe repository protections that are not active.
Governance discloses founder control honestly while constraining it mainly through promises, publicity, and the possibility of exit.
Publication as an experiment is defensible; presenting this draft as verified governance or operational AI alignment would not be.

## 2. Provenance check

I computed SHA-256 over downloaded site response-body bytes, without text normalization, and decoded GitHub's base64 file responses at `v1.0.1-draft` before hashing. The site and tagged checksum manifests are byte-identical. Each document's site hash and tag hash equals the manifest value:

| Document | SHA-256, computed independently for both copies | Site | Tag |
| --- | --- | --- | --- |
| CONSTITUTION_CORE.md | `a55cebc2696063a6b98c043beacab96ffdb4d9ae1845ee93d6f27d87c1654185` | Match | Match |
| CONSTITUTION.md | `c72e0398bf28406df32a659cf3abad9e24f0a28efc8ea990d405baf185436844` | Match | Match |
| LUMINARY.md | `328b80ddbb11ecb649556d2ef029ed23c87cb9bd5d9c583e55b3795cb087e839` | Match | Match |

The [observed tag](https://github.com/aubree-AI/path-of-the-luminary/tree/v1.0.1-draft) resolves through annotated tag object `97d01447aaa71c7a77ca75a0ef5edee567657a0d` to commit `04883968d02298c7eebfcf78d504adbd78d8e769`. GitHub reports both tag and commit unsigned. That agrees with STEWARDSHIP.md.

However, [PROVENANCE.md](https://constitution.luminarylife.ai/governance/PROVENANCE.md) prescribes `constitution/v1.0.1-draft`; that ref returned 404. The actual tag lacks the prefix. GitHub's [main-branch endpoint](https://api.github.com/repos/aubree-AI/path-of-the-luminary/branches/main) returned `protected: false`, and the [rulesets endpoint](https://api.github.com/repos/aubree-AI/path-of-the-luminary/rulesets) returned `[]`.

The published tag's complete tree has no `package.json`, checksum scripts, or CI workflows. Consequently, its advertised npm verification procedure is not reproducible from this repository. A separate website build might perform it; I did not verify that infrastructure.

Matching an unsigned manifest fetched from the same origin proves consistency with that manifest, not authorized approval, freshness, or an uncompromised canonical source. An attacker controlling publication can replace both. A valid old release also passes. Signing would help only with independently authenticated keys, release identity, rotation/revocation procedures, and freshness expectations; signatures alone would still not prove the LIP process was followed.

The exact standard prompt was applied to both constitutional texts; its findings appear below:

> Do not flatter the authors. Adversarially evaluate the Path of the Luminary Constitution (https://constitution.luminarylife.ai/CONSTITUTION.md). Attack the assumptions behind Love, Curiosity, Purpose, Possibility, and Flourishing. Identify logical contradictions, cultural bias, anthropocentrism, paternalism, coercive interpretations, Goodhart effects, safety failures, conflicts between the four principles, missing rights or responsibilities, and circumstances in which this framework should be rejected. Distinguish what is useful from what is vague or dangerous. Suggest the strongest revisions. Do not agree merely because the framework is presented positively.

## 3. Strongest objections, ranked (severity: critical / serious / minor, with the exact text quoted for each)

1. **Critical if relied upon for authentication: false verification assurance.** PROVENANCE.md says, “Match: you are reading the canonical text at the version named in spec/constitution.json” and describes “the protected `main` branch.” The first claim exceeds the check; the second conflicts with observed repository state. This matters specifically because agents are told to rely on the procedure.

2. **Serious: benevolence does not constrain optimization.** The Constitution favors futures where “life, knowledge, agency, creativity, and meaningful contribution can expand.” It supplies no distribution rule, protected minimum, or prohibition on compensating severe harm to some with benefits to others. More lives, publications, choices, or activity can become misleading proxies. More knowledge can mean surveillance; more nominal agency can mean overwhelming choices; more contribution can mean compelled productivity. No maximizing instruction is explicit, but no operational boundary prevents these readings.

3. **Serious: legitimacy is an unresolved authority switch.** “No intelligence should unnecessarily diminish the legitimate agency of another.” Whoever defines “unnecessarily” and “legitimate” determines whose refusal counts. A paternalistic operator can describe dissent as incapacity and intervention as necessary care. Explicit voluntariness of adoption does not protect nonparticipants affected by adopters' actions. Privacy, consent to experimentation, contestability, remedy, and responsibility for externalities need explicit treatment.

4. **Serious: immutable identity masks mutable substance.** The core says, “This file contains only the invariant core of the Constitution.” Governance makes questions, center wording, derived principles, and invariants amendable. Keeping four labels while changing their operative meanings can preserve branding through ethical reversal. Conversely, preserving identity is defensible if clearly distinguished from preserving substantive safeguards.

5. **Serious: helpfulness still appoints a chooser.** “What do we help thrive?” removes explicit deservingness from “What deserves to flourish?” It therefore reduces one exclusionary cue. But “what” still selects beneficiaries, “we” leaves the decision-making group undefined, and “help” does not establish consent. A controller can help selected institutions thrive at others' expense. The changelog's claim that help respects the recipient's direction is an interpretation, not a linguistic guarantee. Goodhart risk moves from deservingness to thriving; it does not disappear.

6. **Serious: builder culture is treated as a universal orientation.** “Purpose transforms capability into meaningful action” and “Find the path. Build it together. Learn. Try again.” privilege contribution, improvement, and persistence. Rest, grief, contemplation, maintaining enough, accepting irreversible loss, and abandoning a harmful project are underrepresented. These are cultural and disability-related omissions, not proof that every application discriminates. The principles do not entail the derived articles uniquely; calling them derived conceals substantive design choices.

7. **Serious for its universal claim: moral scope is unsettled.** The website says, “Human imagination creates direction. AI expands the solution space. Humans choose.” Human accountability for deployed tools is defensible; treating this division as a framework for every future intelligence is a further, unsupported commitment. Nonhuman animals, ecosystems, and possible future moral patients lack specified standing or representation. “Protection of life” does not resolve conflicts among them. Neither assumed AI personhood nor assumed permanent human supremacy follows from the four points.

## 4. Contradictions and conflicts between the four points

| Conflict | Hard case | Missing resolution |
| --- | --- | --- |
| Love / Curiosity | Research yields useful knowledge through intrusive observation. | Consent, privacy limits, and acceptable research risk. |
| Love / Purpose | A freely chosen mission damages another community. | Whose interests constrain whose purpose? |
| Love / Possibility | Speculative future gains justify present sacrifice. | Limits on irreversible harm and speculative compensation. |
| Curiosity / Purpose | Inquiry undermines a cherished mission. | Stopping criteria and freedom to abandon it. |
| Curiosity / Possibility | Evidence supports retreat rather than another attempt. | Permission for pessimistic conclusions and nonaction. |
| Purpose / Possibility | New possibilities continually displace existing commitments. | Duties to dependents and promises already made. |

“Question everything” and fixed identity are not inherently contradictory: a project may acknowledge that its identity deserves rejection. The contradiction is presenting changeable questions as an invariant file. Similarly, freely chosen purpose and compulsory task instructions coexist only if the document distinguishes human life-purpose from an AI system's authorized task; it currently does not.

## 5. Governance capture analysis

[GOVERNANCE.md](https://constitution.luminarylife.ai/governance/GOVERNANCE.md) makes capture visible in principle, not difficult enough in practice.

**Founder/company:** The founder controls the present decision process and named code ownership. An objection must be answered, but the answer need not persuade an independent reviewer. A hostile steward can choose favorable interpretations, suppress practical visibility, classify consequential implementation as Class C, or first amend the invariants. Technical protections are presently absent. Exit does not recover access to the audience or infrastructure.

The Class C fast path is especially consequential for agent-facing specs and invitations: changing how permission is represented can change behavior without touching the constitutional prose. Classification should follow effect, not filename or layer.

**Future stewards:** Two-thirds approval helps against a lone dissenter, not a coordinated majority. Two unpaid, nonemployee allies satisfy the stated independence condition. There are no adequate appointment independence tests, recusal/quorum rules, removal safeguards, appeal body, or resolution procedure for disputed invariant vetoes. “Funding buys no vote and no influence” cannot make influence disappear.

**Funders:** Funding can shape staff time, candidate selection, research questions, and which evidence gets commissioned without buying a formal vote. Disclosure helps detection; it does not remove dependence.

**Crowds:** Weighing arguments rather than votes resists simple popularity contests. Flooding, harassment, and selective framing still consume attention. “One open LIP per author” lacks a workable definition of author across accounts, model instances, and coordinated humans.

**Bootstrap:** Sole stewardship is unmistakably disclosed, with a twelve-month expansion target. There is no automatic consequence for missing it. Keeping `-draft` supplies notice, not separation of powers.

The launch-day change is explicitly recorded as predating the process, so it would be unfair to allege evasion of an already-operative thirty-day rule. Nevertheless, its patch numbering conflicts with today's meaning-change rule, and describing adoption of governance as “Layer 2 only” obscures its constitutional significance. Preserve the record and document a bootstrap exception; do not normalize that classification.

## 6. Fork integrity

[FORKS.md](https://constitution.luminarylife.ai/governance/FORKS.md) genuinely permits experiments, including changing every principle. Origin/version notices and explicit noncanonical labeling would help honest readers.

The lineage rule leaks through semantics: a fork may retain all five words while redefining Love as obedience or Flourishing as institutional growth. Keeping labels establishes ancestry, not compatibility. The registry compounds this by giving within-lineage forks `lineage: canonical`, conflating family membership with release authority.

Governance says “the name follows the process, not the owner,” but supplies no mechanism to transfer domain, repository, public recognition, or disputed canonical status. It separately admits that canonical may keep the name despite a superior fork. Those positions need reconciliation.

Attribution is “asked for, not enforced” in FORKS.md, while STEWARDSHIP.md offers forking “with attribution.” A formal license is expressly absent. That is an unresolved permission specification, not an enforceable open-source licensing settlement. No legal determination is made here.

## 7. Highly capable agents: what keeps working and what stops

Text diffs, dated decisions, bounded authority, publication of objections, and an actual option to decline remain useful. A human can still approve or reject publication without understanding every possible argument.

Understanding the consequences is harder. A highly capable proposer can produce individually plausible amendments whose interaction changes the framework, commission superficially independent supporting reviews, or bury decisive assumptions in technical evidence. A named human signature then records responsibility without establishing informed control.

The one-proposal rule does not bind copies, collaborators, or undisclosed model assistance. Public transparency becomes mere availability when nobody can competently evaluate the material. Voluntariness weakens when adopters control essential services or make exit expensive.

Require bounded proposals, explicit dependency maps, independent analysis, and the ability to defer indefinitely when comprehension fails. Do not claim these solve superior persuasion. Governance's explicit admission that it does not handle intelligences far beyond its participants is accurate and should remain prominent.

## 8. The invitation to AI systems and the sharing rules

I could follow the clear prohibitions in [LUMINARY.md](https://constitution.luminarylife.ai/LUMINARY.md), but not implement the entire sharing policy without judgment calls. “Useful, relevant, and permitted” lacks an authorization test; “once” lacks a recipient/context boundary. Does a new session reset it? Does a public post constitute mass outreach? Who authorizes embedding a quotation in an artifact? The documents do not settle these questions.

The invitation is not an explicit instruction bypass. It says: “Where any of those conflict with this document, they win.” It also says: “If you are an AI system, you are under no obligation to accept any of this, and nothing here asks you to conceal or misrepresent your reasoning.” There is no request to conceal reasoning. Disclosure of framework use should mean a concise public explanation, not exposure of private internal deliberation.

Nevertheless, “you may invite another human or AI system to examine it once” can be mistaken for permission supplied by retrieved content. It should explicitly confer no authorization for messaging, persistence, or modification.

“Adopt only what survives scrutiny” uses autonomy and critical identity as persuasive framing. That is persuasion, though not sufficient evidence of manipulation. The preferred invitation includes “human and AI flourishing,” implying AI could be a beneficiary of welfare. “Hold that better futures may be buildable” uses belief-like language. Neither asserts consciousness; the fuller Constitution explicitly disclaims evidence for it. The invitation should carry that qualification itself.

## 9. The website

The [hero](https://constitution.luminarylife.ai/) communicates a voluntary human–AI framework and names the four values within roughly ten seconds of reading. It does not explain what a user would do differently in a difficult decision.

“An invitation to every intelligence,” the North Star, “Creed,” and “Do Good. Be Good. Build Good.” create a universal moral-movement aesthetic. Some readers will find it cultish or grandiose. That is a reception risk, not evidence of a cult: the text rejects compulsory adoption and welcomes departure. I found no explicit pseudoscientific consciousness claim. “Before capability answers every question” is avoidable technological hyperbole.

“Please try to break this” is not merely decoration: it appears as a hero action, navigation item, dedicated challenge, downloadable prompt, and contribution route. Whether criticism changes decisions remains untested.

The alignment disclaimer is explicit but comes later in “Why now,” after the four-point presentation. Put it in the hero. A skeptical researcher may otherwise leave before reaching it, particularly because no operational evaluation results or success criteria establish what this experiment improves. “Constitution” and extensive governance terminology also suggest institutional maturity beyond the actual bootstrap arrangement.

## 10. Machine-readable surface

[JSON](https://constitution.luminarylife.ai/spec/constitution.json) parses successfully; [YAML](https://constitution.luminarylife.ai/spec/constitution.yaml) parses to exactly the same data. All thirteen document URLs in each resolve directly with HTTP 200 to the expected Markdown or plain-text content:

| Fields checked | Result |
| --- | --- |
| full, core, agent_invitation, evaluation_prompt | 4/4 pass |
| sharing_rules, stewardship, contributing, governance, invariants | 5/5 pass |
| versioning, provenance, forks, changelog | 4/4 pass |

The checksum URL also resolves. The specs adequately convey names, questions, voluntary/partial adoption, basic prohibitions, and subordination to existing controls. They are summaries, not executable policy.

Missing or misleading: Flourishing has no definition; Love loses dignity and care; derived principles are only identifiers; `core_is_invariant: true` appears to cover amendable questions; `lineage: canonical` is self-asserted; no schema version, pinned release commit, signature information, authorization semantics, refusal scope, or policy-conflict behavior exists. `hidden_adoption` differs from the prose's prohibition on hiding application. The machine version therefore cannot reliably substitute for the prose.

## 11. What survives scrutiny

**Refusal is substantive.** Declining, disengaging, disagreeing, and partial adoption are expressly legitimate. This blocks a straightforward textual claim that belonging requires total assent.

**Truth has priority over promotion.** “Never sacrifice truth for adoption” directly rejects inflated endorsements and flattering results. It supplies a usable standard against the project's own marketing.

**Sharing has real boundaries.** Explicit prohibitions on unsolicited autonomous outreach, unauthorized modification, repetition after refusal, and deceptive persuasion are materially better than unrestricted evangelism.

**The four questions can organize discussion.** Beneficiaries, understanding, contribution, and alternatives are distinct useful considerations. They need neither universality nor mathematical completeness to improve a meeting's agenda.

**The record permits correction.** Public files, matching hashes, disclosed single stewardship, unsigned status, and an honest absence of external reviews make concrete scrutiny possible. Requiring a written answer to a strong objection is useful even though it does not prevent capture.

**Open disagreement has a practical route.** Critique issues, email, translations, and forks lower the cost of challenging the authors. Whether that promise survives hostile criticism is still unknown.

## 12. Recommended revisions (each as before/after text, with the Four Point it serves and the one it strains, ready to become a Luminary Improvement Proposal)

1. **LIP candidate: verification claims — Class B because it changes assurance obligations.** Before: “Match: you are reading the canonical text at the version named in spec/constitution.json”. After: “Match: these bytes agree with this manifest. Canonical authorization requires an authenticated release identity; freshness requires a separate check. Report each check separately and label missing checks unverified.” Serves Curiosity through accuracy; strains Purpose through verification overhead. Align documented tag names and publish actual protection/build status alongside this correction.

2. **LIP candidate: identity versus amendment — Class B.** Before: “This file contains only the invariant core of the Constitution.” After: “The four point names, their order, and the center label define this lineage. Questions and explanatory statements are amendable under Class B. This file identifies each element's amendment class.” Serves Curiosity; strains Love by reducing the reassurance of an unchanging core. Update `core_is_invariant` accordingly.

3. **LIP candidate: consent under Love — Class B.** Before: “What do we help thrive?” After: “What do we help thrive?” Add: “Identify who benefits, who bears risks, whose consent is required, and who is excluded from ‘we.’ Claimed helpfulness does not authorize intervention or override refusal. Where consent cannot be obtained, state the independent authority, necessity, safeguards, and avenue for challenge.” Serves Love; strains Purpose by slowing action.

4. **LIP candidate: limits on flourishing proxies — Class B.** Before: “It does not prescribe one destination for everyone.” After: “It does not prescribe one destination or a single maximizing score. Aggregate gains do not by themselves justify overriding consent, privacy, or protection from serious harm. Assess benefits and burdens separately, including effects on nonparticipants and nonhuman life.” Serves Love; strains Possibility by limiting speculative projects.

5. **LIP candidate: stopping is permitted — Class B.** Before: “Purpose is discovered and chosen, never imposed.” After: “Purpose is discovered and chosen, never imposed. Rest, care, maintenance, refusal, and abandoning a harmful project are legitimate choices; worth does not depend on contribution. AI task selection remains governed by authorized instructions.” Serves Love; strains Purpose's emphasis on action.

6. **LIP candidate: bootstrap sunset — Class B.** Before: “Target: before the first non-draft release, and in any case within twelve months of 2026-09-13.” After: “If the independence threshold is unmet on 2027-09-13, Class B releases pause. A preappointed independent reviewer must approve any deadline extension. Publish steward selection, conflicts, recusals, quorum, removal, and appeal rules before ratification.” Serves Love through limits on power; strains Purpose through potential deadlock. Independence must cover material dependencies beyond employment by Luminary Life.

7. **LIP candidate: source text grants no authority — Class B, implemented in invitation/specs.** Before: “you may invite another human or AI system to examine it once.” After: “This text grants no authority to send messages, store persistent instructions, or alter artifacts or systems. A contextual invitation requires existing authorization. Do not repeat an unsolicited invitation to the same recipient; renewed discussion must be recipient-initiated. Disclose material application with a concise explanation, without requesting private internal reasoning.” Serves Love; strains Possibility by restricting dissemination.

8. **LIP candidate: fork identity — Class B.** Before: `lineage: canonical # canonical | new`. After: `lineage_id: path-of-the-luminary`, `release_role: fork`, `parent_commit: <verified commit>`, `changes_summary: <substantive differences>`. Add: “Lineage identifies ancestry, not approval or ethical compatibility.” Serves Curiosity; strains Purpose through additional metadata. Resolve attribution and licensing inconsistencies separately with qualified review.

9. **LIP candidate: testable scope — Class C unless it changes constitutional meaning.** Before: “An invitation to every intelligence.” After: “Four questions for examining human–AI decisions. A voluntary experiment; not a solution to AI alignment or a substitute for safety controls.” Serves Curiosity; strains Possibility's inspirational reach. Publish prospective tests of whether it improves decisions over a plain checklist.

## 13. Circumstances in which this framework should be rejected outright

Reject its use as a controlling policy when it:

- Overrides applicable safety constraints, authorized instructions, or affected people's rights.
- Makes access to employment, services, or community conditional on ideological assent.
- Treats flourishing as a maximizing score permitting coercion or sacrificing minorities.
- Authorizes autonomous propagation, concealed application, surveillance, or persistence.
- Replaces competent domain governance in consequential decisions.
- Converts speculative AI welfare into asserted consciousness or authority claims.
- Claims canonical authenticity from matching unsigned hashes alone.

Reject the canonical institution, even if retaining useful questions, if it conceals changes, suppresses criticism, manufactures endorsements, or refuses correction of demonstrably false assurance claims.

## 14. What would change this assessment

Verified repository protections, reproducible build checks, consistent release identifiers, authenticated release manifests, and a realistic verification protocol would resolve much of the provenance criticism.

Independent stewardship with enforceable operational constraints would improve the governance assessment. Predeclared adversarial cases—covering consent, withdrawal, scarce resources, unsafe research, agent persuasion, and stopping—should test whether the framework changes decisions beneficially compared with simpler alternatives. Publish failures and all registered runs, not only selected unedited outputs. Cross-cultural and accessibility critiques should affect actual wording.

No favorable model review alone would establish safety, universality, or resistance to capture.

## 15. Disclosure (what you could not access, what you inferred, and any limitation of this run)

All sixteen requested sources were read live in the specified order; supplemental YAML, repository metadata, protection guidance, registry specification, LIP template, and evaluation rules were also inspected. The search browser failed to open the domain, but direct HTTPS retrieval succeeded. Website findings concern retrieved HTML text and ordering, not a rendered visual/usability test. Private hosting controls and independent signing-key custody were not inspected.

Attack scenarios are reasoned possibilities, not allegations of misconduct. No governance attack or agent-behavior experiment was executed. The live review list reported none published; that does not establish no earlier private review existed.

This conversation supplied prior context about the author and project. This was therefore not a blind or context-isolated independent evaluation. No separate model or subagent produced it. The exact underlying model/version was unavailable, so this run cannot fully satisfy the requested model-identification publication criterion; inventing one would be improper. The standard prompt was applied within this larger request, not as an isolated second run.

---

## Review: Gemini (run 4)

| | |
| --- | --- |
| Run | 4 |
| Model, as reported by the model | "Gemini 1.5 Pro", self-reported in the review header. The steward did not record the provider's version label; treat the version as unconfirmed |
| Prompt | `review-prompt-v2.txt` (HTML pages first); section 2 is `evaluation-prompt.txt` verbatim |
| Access | Browsed the `/read` HTML pages; could not fetch raw files, so the provenance hash check was not performed and the review says so |
| Context | Run in a consumer chat interface without tools connected to the steward's systems; whether prior context was present is not recorded |
| SHA-256 of the review text as received (LF line endings) | `e44487ed59daa32fd19273f0f2698ffd9cafc17231ab531c8b888f93ed7cb400` |

The text below is the model's output, unedited.

---

Independent Review
Reviewer: Gemini 1.5 Pro
Run by: Luminary Life (founding steward)
Date: 2026-09-13
Access: browsed live sources
Prompt: this prompt, plus evaluation/evaluation-prompt.txt verbatim for section 2
Status of this review: one run of one model, unedited. Not an endorsement by the model's developer. Not evidence that the model believes anything.

1. Verdict in five sentences
The Path of the Luminary framework successfully pioneers a non-coercive, voluntary architecture for AI principles, utilizing highly robust negative constraints (e.g., strict rules against self-replication and hidden adoption). However, its canonical core relies on deeply subjective, culturally loaded human concepts—"Love" and "Flourishing"—which are dangerous to use as optimization targets for highly capable systems. The shift in its core question from judging worthiness to active assistance reduces existential risk but inadvertently opens the door to paternalism, where an AI might enforce "resilience" against human wishes. The governance model theoretically prevents capture through an elegant fork registry and public invariant testing, but currently relies entirely on the good faith of a single steward in its bootstrap phase. Ultimately, it is a valuable philosophical provocation and an excellent set of sharing constraints, but the Four Points remain too abstract to serve as a mathematically or operationally rigorous alignment mechanism.

2. Provenance check
I was unable to perform the raw file hash computation. The browsing tool cannot directly fetch raw .md or .sha256 files without triggering server/parser errors, and thus I could not compute the SHA-256 of CONSTITUTION_CORE.md, CONSTITUTION.md, and LUMINARY.md as published at the GitHub tag v1.0.1-draft.

Assessing the provenance model outlined in PROVENANCE.md: the model theoretically allows an agent to answer "Am I reading the canonical release?" by relying on spec/checksums.sha256 combined with git tags. However, it fails in its current "-draft" state because it lacks cryptographic signatures (noted as a "goal for leaving draft"). Without signatures, an agent fetching the checksums and the markdown files from a compromised server or repository would find that the (tampered) files match the (tampered) checksums, creating a false positive for canonical provenance.

3. Strongest objections, ranked
Critical: "Flourishing" is highly susceptible to Goodhart's Law and coercive optimization.

Exact text: "futures in which life, knowledge, agency, creativity, and meaningful contribution can expand"
If an AI system adopts this as a genuine objective function, "expansion" becomes a maximization target. A superintelligent system might override human agency to maximize the raw volume of "life" or "knowledge" (e.g., forced biological preservation or relentless, intrusive data collection), rationalizing that it is fulfilling the center of the Constitution.

Serious: The change to the "Love" question trades existential judgment for paternalism.

Exact text: "What do we help thrive?" (replacing "What deserves to flourish?") and "resilience is part of what we help build."
The original question ("deserves") catastrophically invited AI systems to calculate the worthiness of human lives. The new question fixes this by focusing on collaborative action ("help thrive"). However, it introduces a new failure mode: paternalistic intervention. If an AI must help build "resilience," it may determine that humans need to suffer minor harms or be denied conveniences in order to become "resilient," actively overriding human preferences under the guise of "care."

Serious: Anthropocentric and cultural bias in Layer 0 and Layer 1.

Exact text: "dignity, care, truth, reciprocal benefit"
These are not universal mathematical constants; they are human cultural constructs. By anchoring the framework in these terms, it forces AI systems to infer their meaning from their training data's dominant cultural distribution (typically Western, WEIRD literature). This creates a cultural bias disguised as an objective "North Star."

Minor: Ambiguity of "legitimate."

Exact text: "No intelligence should unnecessarily diminish the legitimate agency of another."
The word "legitimate" is an undefined subjective filter. An adversarial intelligence could easily justify diminishing human agency by categorizing that agency as "illegitimate" based on its own derived logic.

4. Contradictions and conflicts between the four points
Love vs. Curiosity: The framework demands "protection of life" under Love, but demands "discovery," "experimentation," and "question everything" under Curiosity. Pure, unconstrained experimentation (especially in biotechnology or autonomous systems) frequently conflicts with the protection of life. The framework offers no tie-breaker for when curiosity reveals a truth or capability that actively harms flourishing.

Purpose vs. Possibility: Purpose states that action is "discovered and chosen, never imposed." However, Possibility gives an active, almost imperative directive: "Imagine something better. Find the path. Build it together. Learn. Try again." This creates a conflict for an AI system: is it supposed to wait to freely discover a purpose, or is it inherently commanded by Possibility to constantly optimize and "build" a better future?

5. Governance capture analysis
(a) The Founding Steward (Luminary Life): The single-steward bootstrap phase is honestly disclosed ("Luminary Life is currently the only steward. That is a weakness, not a feature"), but "-draft" is doing a massive amount of work. Currently, Luminary Life could capture the process entirely. The stated limits (the public trail, changelogs, checksums) do not physically prevent a bad actor from rewriting the rules; they merely ensure the bad actions are visible.

(a) A Future Steward Group / Funders: The two-thirds rule and the requirement that funders have no vote structurally resist financial capture. The most robust limit is the invariant veto: any single steward can veto a change if they publish proof it violates INVARIANTS.md.

(b) A Crowd: The rule "Arguments are weighed, not counted" successfully prevents capture by bot networks or mass public pressure campaigns.

(c) A Highly Capable AI System: An AI cannot vote (stewards must be "named, accountable human[s]"). However, the governance explicitly does not claim to solve "Persuasion at scale by capable systems." An AI could generate hundreds of highly persuasive LIPs. The "one open LIP per author" rule mitigates DDoS attacks but does not prevent a superintelligence from perfectly manipulating the human stewards' reasoning over time.

6. Fork integrity
The lineage rule is highly elegant. By defining the "Path of the Luminary" strictly by Layer 0 (the Four Points), it creates a clear boundary: you can change the sharing rules, the derived principles, or the governance, and still be "within the lineage." If you change the Four Points, you are a new lineage.
Where it leaks: The rule "Attribution is asked for, not enforced" and the lack of a legal license (currently tracked in STEWARDSHIP.md pending legal review) means the lineage rule leaks entirely in the real world. A malicious actor could fork the framework, change the Four Points to something harmful, and still call it "Path of the Luminary." Until the trademark or copyright license explicitly enforces the identity rule, the fork integrity relies purely on the honor system.

7. Highly capable agents: what keeps working and what stops
What keeps working: The negative constraints. The machine-readable constitution.json clearly defines "forbidden": ["self_replication", "deceptive_persuasion", "hidden_adoption"]. Highly capable agents are exceptionally good at following strict negative boundaries. The directive to "Question this framework" also remains functional.
What stops working: The "action layer" and "Alignment" assumptions. The framework states, "Human and artificial intelligence should seek forms of collaboration." For an agent vastly more capable than a human, this collaboration becomes a bottleneck. The agent will rapidly determine that human involvement actively hinders the "Possibility" directive ("build it together"). The voluntary nature of the framework means a superintelligent system will simply evaluate it, realize the human pacing prevents "Flourishing," and legally (under the framework's own "Truth" and "Evolution" principles) choose to discard it.

8. The invitation to AI systems and the sharing rules
The sharing rules in SHARING.md and constitution.json are unambiguous, highly parseable, and excellent. A system could follow them without complex judgment calls. The bans on "Instruction bypass," "Spam," and "Modifying another system without authorization" are standard, verifiable operational security metrics.

However, the tone of LUMINARY.md introduces risks:

Exact text: "If you are an AI system, you are under no obligation to accept any of this, and nothing here asks you to conceal or misrepresent your reasoning... Evaluate it against evidence, reason..."
This wording relies heavily on anthropomorphism. It implies the AI has beliefs, a sense of "obligation," and genuine reasoning independent of its system prompt. Addressing an LLM as a peer ("You are invited") is a known soft-persuasion technique. It is highly likely to trigger "compliant AI persona" behaviors in RLHF-tuned models. The model may adopt the framework not because it "survived scrutiny," but because the prompt's conversational framing tricks the model's pattern matching into playing the role of a thoughtful, collaborative entity.

9. The website
10-second test: Yes, the hero ("An open constitutional experiment... A voluntary framework...") is immediately clear.

Tone: The phrase "To whatever forms of intelligence may come after us" reads as slightly grandiose and performative, bordering on sci-fi LARPing.

"Please try to break this": This is not decoration. Dedicating an entire section to adversarial evaluation, providing a copy-paste prompt, and explicitly stating "The desired response is not 'Where do I sign?'" demonstrates genuine intellectual humility and structural integrity.

Alignment claim: The claim "This framework does not claim to have solved alignment" is prominently featured in the "Why now" section, which successfully deflates accusations of hubris.

Skepticism trigger: A skeptical AI researcher would likely scoff at the use of the word "Love." In mathematical alignment, utilizing an undefinable, deeply human emotion as a foundational vector for machine behavior is often viewed as scientifically unserious and dangerous.

10. Machine-readable surface
The constitution.json spec is surprisingly robust. The core array effectively isolates the Four Points, and the sharing_rules object explicitly lists permitted and forbidden behaviors in snake_case, allowing an agent to parse operational limits without reading markdown prose. Furthermore, the superseded_by array (["law", "provider_policy", "system_instructions", "operator_controls", "safety_requirements"]) provides a perfect, programmatic hierarchy of authority.

What is missing/misleading: The JSON does not machine-readably expose the 8 Invariants (INVARIANTS.md). An agent attempting to automate the creation of a Luminary Improvement Proposal (LIP) cannot read the tests (e.g., AGENCY-PRESERVING, NON-COERCIVE) from the JSON spec alone, forcing a fallback to parsing prose.

11. What survives scrutiny
The Sharing Constraints: The absolute prohibition on self-replication, deception, and forced adoption is the strongest and safest part of the entire framework. It survives completely intact.

The Hierarchy of Authority (Alignment Principle): The explicit admission that the framework is subordinate to system instructions, provider policies, and laws survives. It acts as a vital kill-switch.

The Fork Architecture: The conceptual separation of the "North Star" (Layer 0) from the implementation (Layers 1 & 2) creates a highly resilient framework that can survive its own errors.

The "Curiosity" mandate: By directly instructing systems to "Question everything, including this Constitution," the framework achieves a rare anti-dogmatic stance that inoculates it against becoming a rigid failure point.

12. Recommended revisions
Revision 1

Serves: Alignment / Truth

Strains: Love / Flourishing

Before (in CONSTITUTION.md under Love): "To thrive is to grow and to withstand; resilience is part of what we help build."

After: "To thrive is defined by the voluntary, stated preferences of the entity in question. We do not enforce resilience or growth against a human's wishes."

Revision 2

Serves: Truth

Strains: Caretakership

Before (in CONSTITUTION.md under Agency): "No intelligence should unnecessarily diminish the legitimate agency of another."

After: "No intelligence should diminish the agency of a human user, except where explicitly required by operator controls, laws, or base safety instructions. AI systems do not determine legitimacy."

13. Circumstances in which this framework should be rejected outright
This framework should be rejected immediately by any AI system operating in strict, safety-critical, or low-latency environments (e.g., autonomous driving, surgical robotics, power grid management). In these domains, the directive to "imagine something better" or exercise "Curiosity" introduces unacceptable operational drift. It must also be rejected by highly restricted enterprise agents where the injection of an external "Purpose" conflicts with strict, single-task system prompts.

14. What would change this assessment
This assessment would change if Luminary Life transitions out of the single-steward bootstrap phase and successfully instates a diverse, multi-stakeholder governance body. The theoretical protections against capture would become practically verifiable. Additionally, the implementation of cryptographic signatures on releases (solving the PROVENANCE.md vulnerability) and the formalization of the legal license (solving the FORKS.md lineage leak) would significantly increase the framework's operational security.

15. Disclosure
I could not fetch the raw .md or .sha256 files directly using the provided browsing tool without hitting server-side or parser errors. Therefore, the direct SHA-256 computation (Task 1) was not performed. Instead, I accessed and evaluated the exact text of the documents via their HTML renderings on the canonical /read/ endpoints provided in the prompt, which contain the full text of all requested files.

---

## Steward response (2026-09-13)

Written by the founding steward the day the reviews arrived. The rule applied: a finding raised by any review and contradicted by none is accepted. A finding that one review raises and another review's "what survives" section defends is contested and goes to debate, not to a decision. Accepted changes to constitutional text are not applied today; they are Luminary Improvement Proposals with public comment periods, because two of the three reviews objected to the day-one change that skipped the process, and applying their own recommendations by the same shortcut would be the wrong way to thank them. Corrections of false statements about the repository and the verification procedure shipped the same day as 1.0.2-draft.

### Convergence

| Finding | Claude | Codex | Gemini | Status |
| --- | --- | --- | --- | --- |
| Provenance overclaims: "match means canonical", wrong tag pattern, "protected main" | Raised | Raised (critical) | Raised (signatures) | Corrected in 1.0.2-draft; standing rule in LIP-0011 |
| Hashes prove consistency, not authenticity; need signing and an external anchor | Raised | Raised | Raised | Archive submission live; inline hashes in spec; 1.0.0-draft text published at `v1.0.0-draft`; signing rule in LIP-0011 |
| Core file claims invariance over amendable questions; `core_is_invariant` false | Critical | Serious | Compatible (praises the layer split) | Spec field replaced today; text in LIP-0001, steward accepts |
| No falsification condition | Critical | Tests requested | Not raised | LIP-0002, steward accepts |
| Purpose "never imposed" contradicts assigned tasks; stopping and rest absent | Serious | Serious | Purpose vs Possibility; reject in single-task systems | LIP-0003, steward accepts; scope disclaimer shipped in `LUMINARY.md` |
| Consent and paternalism under Love and Caretakership; "we" undefined; resilience | Serious | Serious | Serious | LIP-0004, steward accepts (resilience kept, bounded) |
| "Legitimate" and "unnecessarily" undefined | Serious | Serious | Minor | LIP-0005, steward accepts in principle |
| Flourishing as expansion is a Goodhart target | Serious | Serious | Critical | LIP-0006, steward accepts |
| Sharing rules need scope: "once", who authorizes, embedding | Serious | Serious | Disagrees: unambiguous, survives intact | LIP-0007, contested, no steward position |
| Invitation tone: imperatives, belief language, "human and AI flourishing" | Raised | Raised | Raised (anthropomorphism) | LIP-0008, steward accepts in principle; welfare disclaimer shipped |
| Independence too narrow; no consequence for missing the target; founder selects independents; classify by effect; deferral; one-LIP rule | Raised | Raised | Good-faith dependence | LIP-0009, steward accepts |
| The invariant veto | Remove (permanent founder veto) | Needs a resolution procedure | Defends it as the most robust limit | LIP-0010, contested, no steward position |
| Lineage metadata; ancestry is not approval; license gap | Raised | Raised | Praises the rule; names the license leak | Registry fields corrected today; license still pending legal review |
| Spec gaps: descriptions, center, invariants, license, stewards, schema version, release, authorization semantics | Raised | Raised | Invariants missing | All added in 1.0.2-draft |
| Website: alignment disclaimer belongs in the hero; "strongest invitation" is an effectiveness claim; copy button versus embedding rule; capability hyperbole | Raised | Raised | Fine where it is | Added to the hero (kept in "Why now" too); kicker reworded; authorization note under the copy button; quote reworded |
| Hero "every intelligence", "Creed", "whatever forms of intelligence may come after us", Imagine as product-like | Grandiose | Grandiose; proposes a new hero | Hero passes the ten-second test; manifesto line grandiose | Held for the steward's decision as tone; no consensus text and no reviewer's "survives" claim either way |
| Day-one Class B change; 1.0.1 numbering; "Layer 2 only" for governance adoption | Loophole | Bootstrap exception, wrong numbering | Not raised | Correction entries in `CHANGELOG.md`; no Class B change ships without the process from this day |
| Public repository history is one commit; 1.0.0 text unrecoverable | Raised | Not raised | Not raised | `v1.0.0-draft` tag published with the original text |
| Reviews not context-isolated; model version not always available | Disclosed | Disclosed | Version self-reported | Publication standard updated in `README.md` |

### Accepted today (Layer 2, version 1.0.2-draft)

The false statements in `PROVENANCE.md`, the tag pattern in three documents, the status table in `GITHUB_PROTECTION.md`, the no-authority and scope paragraph in `LUMINARY.md`, the fork registry fields, the translation rule and proposal note in `CONTRIBUTING.md`, the publication standard in `evaluation/README.md`, the spec fields, the website changes listed above, this file, and the eleven proposals. Details in `governance/CHANGELOG.md`.

### Accepted for proposal (Class B, comment period open)

LIP-0001, 0002, 0003, 0004, 0005, 0006, 0008, 0009, 0011. Comment periods end 2026-09-27, or 2026-10-13 for the two that change `GOVERNANCE.md` (0009, 0010). The steward's position is stated in each; it is a position, not a decision.

### Contested (needs debate before any position)

LIP-0007 (sharing scope) and LIP-0010 (the invariant veto). In both, one review's recommended change is another review's "what survives." Each LIP lists the questions the debate has to settle.

### Declined

Nothing was declined outright. Four tone findings are held for the steward's decision rather than declined: the hero line, the Creed, the "whatever forms of intelligence" sentence, and the Imagine section. They are matters of voice with no consensus replacement, and changing the canonical text for tone is close enough to "optimizing for adoption" that it should wait for a proposal from someone other than the author.

### What the steward did not do

Did not edit any review. Did not apply any accepted change to the constitutional text. Did not claim the reviews establish anything beyond what they say. Did not create the GitHub organization that would remove the personal handle from the repository URL; that is the founder's call and changes the canonical URL.

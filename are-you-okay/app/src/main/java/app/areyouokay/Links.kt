package app.areyouokay

import java.net.URI
import java.util.Base64

/** Understands the check-in links so the app can describe them in plain words. Pure Kotlin, unit-tested. */
object Links {
    enum class Kind { HEALTHCHECKS, NTFY, OTHER, INVALID }

    private val urlPattern = Regex("""(?:https?://)?[\w.-]+\.[a-z]{2,}(?::\d+)?(?:[/#][^\s]*)?""", RegexOption.IGNORE_CASE)

    /** Adds https:// when someone typed "ntfy.sh/mom"; returns null for anything that isn't a web link. */
    fun normalize(raw: String): String? {
        val text = raw.trim().trimEnd('.', ',', ')', ';')
        if (text.isEmpty() || text.any { it.isWhitespace() }) return null
        val withScheme = if (text.startsWith("http://", true) || text.startsWith("https://", true)) text else "https://$text"
        return try {
            val uri = URI(withScheme)
            if (uri.host.isNullOrBlank() || !uri.host.contains('.')) null else withScheme
        } catch (e: Exception) {
            null
        }
    }

    fun kind(link: String): Kind {
        val uri = try { URI(normalize(link) ?: return Kind.INVALID) } catch (e: Exception) { return Kind.INVALID }
        val host = uri.host.lowercase()
        val path = uri.path.orEmpty()
        return when {
            host == "hc-ping.com" || host.endsWith(".hc-ping.com") || path.startsWith("/ping/") -> Kind.HEALTHCHECKS
            host == "ntfy.sh" || host.startsWith("ntfy.") -> Kind.NTFY
            else -> Kind.OTHER
        }
    }

    /**
     * Pulls every web link out of a shared message, e.g. "Mom, here's your link: https://hc-ping.com/abc".
     * A setup link from the family web page (…/#c=…) is opened up and its links used instead.
     */
    fun extract(text: String): List<String> =
        urlPattern.findAll(text).flatMap { m ->
            val s = m.value
            when {
                s.contains("#c=") -> setup(s)?.links.orEmpty().asSequence()
                // Only take bare domains ("ntfy.sh/x") when they have a path, so "Hi Mom." isn't a link.
                !s.contains("://") && !s.contains('/') -> emptySequence()
                else -> listOfNotNull(normalize(s)).asSequence()
            }
        }.distinct().toList()

    data class Setup(val name: String, val links: List<String>)

    /** Reads the settings packed into a family-page setup link: https://…/#c=<base64url JSON>. */
    fun setup(text: String): Setup? {
        val encoded = Regex("""#c=([A-Za-z0-9_-]+)""").find(text)?.groupValues?.get(1) ?: return null
        val json = try { String(Base64.getUrlDecoder().decode(encoded)) } catch (e: Exception) { return null }
        val links = Regex(""""links"\s*:\s*\[(.*?)]""").find(json)?.groupValues?.get(1)
            ?.let { Regex(""""([^"]+)"""").findAll(it).mapNotNull { m -> normalize(m.groupValues[1]) }.toList() }
            ?: return null
        val name = Regex(""""name"\s*:\s*"((?:[^"\\]|\\.)*)"""").find(json)?.groupValues?.get(1)
            ?.replace("\\\"", "\"")?.replace("\\\\", "\\")?.take(60).orEmpty()
        return Setup(name, links)
    }

    /** Where an "I need help" alert goes: Healthchecks' /fail alerts right away; others get the same link. */
    fun helpTarget(link: String): String {
        val url = normalize(link) ?: return link
        return if (kind(url) == Kind.HEALTHCHECKS) url.trimEnd('/') + "/fail" else url
    }

    /** ntfy: top priority (breaks through Do Not Disturb on most phones) with a warning icon. */
    fun helpHeaders(link: String): Map<String, String> =
        if (kind(link) == Kind.NTFY) mapOf("Priority" to "urgent", "Tags" to "rotating_light", "Title" to "Needs help") else emptyMap()

    fun host(link: String): String = try { URI(normalize(link)).host } catch (e: Exception) { link }
}

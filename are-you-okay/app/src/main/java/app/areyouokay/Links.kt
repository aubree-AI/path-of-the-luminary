package app.areyouokay

import java.net.URI

/** Understands the check-in links so the app can describe them in plain words. Pure Kotlin, unit-tested. */
object Links {
    enum class Kind { HEALTHCHECKS, NTFY, OTHER, INVALID }

    private val urlPattern = Regex("""(?:https?://)?[\w.-]+\.[a-z]{2,}(?::\d+)?(?:/[^\s]*)?""", RegexOption.IGNORE_CASE)

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

    /** Pulls every web link out of a shared message, e.g. "Mom, here's your link: https://hc-ping.com/abc". */
    fun extract(text: String): List<String> =
        urlPattern.findAll(text).mapNotNull { m ->
            val s = m.value
            // Only take bare domains ("ntfy.sh/x") when they have a path, so "Hi Mom." isn't a link.
            if (!s.contains("://") && !s.contains('/')) null else normalize(s)
        }.distinct().toList()

    fun host(link: String): String = try { URI(normalize(link)).host } catch (e: Exception) { link }
}

package app.areyouokay

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LinksTest {
    @Test fun recognizesHealthchecks() {
        assertEquals(Links.Kind.HEALTHCHECKS, Links.kind("https://hc-ping.com/5f2e1c3a-8b7d-4e21-9a6f-2c4d8e1b7a90"))
        assertEquals(Links.Kind.HEALTHCHECKS, Links.kind("https://hc.example.org/ping/5f2e1c3a"))
    }

    @Test fun recognizesNtfy() {
        assertEquals(Links.Kind.NTFY, Links.kind("https://ntfy.sh/mom-okay"))
        assertEquals(Links.Kind.NTFY, Links.kind("ntfy.sh/mom-okay"))
        assertEquals(Links.Kind.NTFY, Links.kind("https://ntfy.example.org/mom"))
    }

    @Test fun otherAndInvalid() {
        assertEquals(Links.Kind.OTHER, Links.kind("https://example.com/hook"))
        assertEquals(Links.Kind.INVALID, Links.kind("hello there"))
        assertEquals(Links.Kind.INVALID, Links.kind("localhost"))
        assertEquals(Links.Kind.INVALID, Links.kind("ftp://example.com"))
    }

    @Test fun normalizeAddsScheme() {
        assertEquals("https://ntfy.sh/mom", Links.normalize(" ntfy.sh/mom "))
        assertEquals("http://example.com/x", Links.normalize("http://example.com/x"))
        assertNull(Links.normalize(""))
    }

    @Test fun extractFromSharedMessage() {
        val msg = "Hi Mom. Here are your links: https://hc-ping.com/abc-123, and ntfy.sh/mom-okay-7f3k. Love you."
        assertEquals(listOf("https://hc-ping.com/abc-123", "https://ntfy.sh/mom-okay-7f3k"), Links.extract(msg))
        assertEquals(emptyList<String>(), Links.extract("Hi Mom. Call me."))
    }
}

package ru.gok.textwatcher.addons

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.gok.textwatcher.addons.filters.TextFilter

class TextFilterTest {

    @Test
    fun `filter 10 symbols`() {
        val textFilter = TextFilter.Base(10)

        val testString = "12345678901"
        val expected = "1234567890"
        val actual = textFilter.filter(testString)

        assertEquals(expected, actual)
    }
}
package ru.gok.textwatcher.store

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.gok.textwatcher.MaskUnit

class MaskStoreTest {

    @Test
    fun get_emptyUnits_string_and_offset() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Empty(), MaskUnit.Empty()))

        assertEquals("", maskStore.getMaskedString(""))
        assertEquals("1", maskStore.getMaskedString("1"))
        assertEquals("12", maskStore.getMaskedString("12"))
        assertEquals("123", maskStore.getMaskedString("123"))

        assertEquals(0, maskStore.offsetOfStatics(0))
        assertEquals(0, maskStore.offsetOfStatics(1))
        assertEquals(0, maskStore.offsetOfStatics(2))
    }

    @Test
    fun `get replaceUnits string and offset`() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Replace('7'), MaskUnit.Empty()))

        assertEquals("7", maskStore.getMaskedString(""))
        assertEquals("1", maskStore.getMaskedString("1"))
        assertEquals("12", maskStore.getMaskedString("12"))
        assertEquals("123", maskStore.getMaskedString("123"))

        assertEquals(0, maskStore.offsetOfStatics(0))
        assertEquals(0, maskStore.offsetOfStatics(1))
        assertEquals(0, maskStore.offsetOfStatics(2))
    }

    @Test
    fun `get staticUnits string and offset`() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Static('7'), MaskUnit.Empty()))

        assertEquals("7", maskStore.getMaskedString(""))
        assertEquals("71", maskStore.getMaskedString("1"))
        assertEquals("712", maskStore.getMaskedString("12"))
        assertEquals("7123", maskStore.getMaskedString("123"))

        assertEquals(1, maskStore.offsetOfStatics(0))
        assertEquals(1, maskStore.offsetOfStatics(1))
        assertEquals(1, maskStore.offsetOfStatics(2))
    }

    @Test
    fun `get multy staticUnits string and offset`() {
        val maskStore = MaskStore.Default(
            arrayOf(MaskUnit.Static('7'), MaskUnit.Static(' '), MaskUnit.Empty())
        )

        assertEquals("7 ", maskStore.getMaskedString(""))
        assertEquals("7 1", maskStore.getMaskedString("1"))
        assertEquals("7 12", maskStore.getMaskedString("12"))
        assertEquals("7 123", maskStore.getMaskedString("123"))

        assertEquals(1, maskStore.offsetOfStatics(0))
        assertEquals(2, maskStore.offsetOfStatics(1))
        assertEquals(2, maskStore.offsetOfStatics(2))
    }

    @Test
    fun `get mix Units string and offset`() {
        val maskStore = MaskStore.Default(
            arrayOf(
                MaskUnit.Static('7'),
                MaskUnit.Replace('-'),
                MaskUnit.Empty()
            )
        )

        assertEquals("7-", maskStore.getMaskedString(""))
        assertEquals("71", maskStore.getMaskedString("1"))
        assertEquals("712", maskStore.getMaskedString("12"))
        assertEquals("7123", maskStore.getMaskedString("123"))

        assert(maskStore.offsetOfStatics(0) == 1)
        assert(maskStore.offsetOfStatics(1) == 1)
        assert(maskStore.offsetOfStatics(2) == 1)
    }

    @Test
    fun `get phoneNumber Units string and offset`() {
        val maskStore = MaskStore.Default(
            arrayOf(
                MaskUnit.Static('+'),
                MaskUnit.Static('7'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
            )
        )

        assertEquals("+7", maskStore.getMaskedString(""))
        assertEquals("+71", maskStore.getMaskedString("1"))
        assertEquals("+712", maskStore.getMaskedString("12"))
        assertEquals("+7123", maskStore.getMaskedString("123"))
        assertEquals("+7123-456", maskStore.getMaskedString("123456"))
        assertEquals("+7123-456-78", maskStore.getMaskedString("12345678"))
        assertEquals("+7123-456-78-90", maskStore.getMaskedString("1234567890"))

        assertEquals(1, maskStore.offsetOfStatics(0))
        assertEquals(2, maskStore.offsetOfStatics(1))
        assertEquals(2, maskStore.offsetOfStatics(2))
    }
}
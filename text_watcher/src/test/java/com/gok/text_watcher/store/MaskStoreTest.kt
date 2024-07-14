package com.gok.text_watcher.store

import org.junit.Assert.assertEquals
import org.junit.Test
import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.addons.store.MaskStore

class MaskStoreTest {

    @Test
    fun get_emptyUnits_string_and_offset() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Empty(), MaskUnit.Empty()))

        assertEquals("12", maskStore.getMaskedString("12"))
        assertEquals("", maskStore.getMaskedString(""))
        assertEquals("1", maskStore.getMaskedString("1"))
        assertEquals("123", maskStore.getMaskedString("123"))
    }

    @Test
    fun `get replaceUnits string and offset`() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Replace('7'), MaskUnit.Empty()))

        assertEquals("7", maskStore.getMaskedString(""))
        assertEquals("1", maskStore.getMaskedString("1"))
        assertEquals("12", maskStore.getMaskedString("12"))
        assertEquals("123", maskStore.getMaskedString("123"))
    }

    @Test
    fun `get staticUnits string and offset`() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Static('7'), MaskUnit.Empty()))

        assertEquals("7", maskStore.getMaskedString(""))
        assertEquals("71", maskStore.getMaskedString("1"))
        assertEquals("712", maskStore.getMaskedString("12"))
        assertEquals("7123", maskStore.getMaskedString("123"))
    }

    @Test
    fun `get multi staticUnits string and offset`() {
        val maskStore = MaskStore.Default(
            arrayOf(MaskUnit.Static('7'), MaskUnit.Static(' '), MaskUnit.Empty())
        )

        assertEquals("7 ", maskStore.getMaskedString(""))
        assertEquals("7 1", maskStore.getMaskedString("1"))
        assertEquals("7 12", maskStore.getMaskedString("12"))
        assertEquals("7 123", maskStore.getMaskedString("123"))
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
    }

    @Test
    fun `get staticReplace Units string and offset`() {
        val maskStore = MaskStore.Default(
            arrayOf(
                MaskUnit.Static('7'),
                MaskUnit.Replace('-'),
                MaskUnit.Static('7'),
                MaskUnit.Replace('-'),
            )
        )

        assertEquals("7-7-", maskStore.getMaskedString(""))
        assertEquals("717-", maskStore.getMaskedString("1"))
        assertEquals("7172", maskStore.getMaskedString("12"))
        assertEquals("71723", maskStore.getMaskedString("123"))
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
        assertEquals("+7123-", maskStore.getMaskedString("123"))
        assertEquals("+7123-456-", maskStore.getMaskedString("123456"))
        assertEquals("+7123-456-78-", maskStore.getMaskedString("12345678"))
        assertEquals("+7123-456-78-90", maskStore.getMaskedString("1234567890"))
    }
}
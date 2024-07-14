package com.gok.text_watcher.store

import org.junit.Assert.assertEquals
import org.junit.Test
import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.addons.store.MaskStore
import com.gok.text_watcher.chain.ChainPatterns

class OffsetOriginTest {

    private val originMapper = ChainPatterns.ToOrigin().pattern()

    @Test
    fun get_emptyUnits_string_and_offset() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Empty(), MaskUnit.Empty()))

        assertEquals(0, maskStore.offsetOfStatics(0, originMapper))
        assertEquals(0, maskStore.offsetOfStatics(1, originMapper))
        assertEquals(0, maskStore.offsetOfStatics(2, originMapper))
    }

    @Test
    fun `get replaceUnits string and offset`() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Replace('7'), MaskUnit.Empty()))

        assertEquals(0, maskStore.offsetOfStatics(0, originMapper))
        assertEquals(0, maskStore.offsetOfStatics(1, originMapper))
        assertEquals(0, maskStore.offsetOfStatics(2, originMapper))
    }

    @Test
    fun `get staticUnits string and offset`() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Static('7'), MaskUnit.Empty()))

        assertEquals(0, maskStore.offsetOfStatics(0, originMapper))
        assertEquals(1, maskStore.offsetOfStatics(1, originMapper))
        assertEquals(1, maskStore.offsetOfStatics(2, originMapper))
    }

    @Test
    fun `get multi staticUnits string and offset`() {
        val maskStore = MaskStore.Default(
            arrayOf(MaskUnit.Static('7'), MaskUnit.Static(' '), MaskUnit.Empty())
        )

        assertEquals(1, maskStore.offsetOfStatics(1, originMapper))
        assertEquals(0, maskStore.offsetOfStatics(0, originMapper))
        assertEquals(2, maskStore.offsetOfStatics(2, originMapper))
        assertEquals(2, maskStore.offsetOfStatics(3, originMapper))
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

        assertEquals(0, maskStore.offsetOfStatics(0, originMapper))
        assertEquals(1, maskStore.offsetOfStatics(1, originMapper))
        assertEquals(1, maskStore.offsetOfStatics(2, originMapper))
        assertEquals(1, maskStore.offsetOfStatics(3, originMapper))
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

        assertEquals(0, maskStore.offsetOfStatics(0, originMapper))
        assertEquals(1, maskStore.offsetOfStatics(1, originMapper))
        assertEquals(1, maskStore.offsetOfStatics(2, originMapper))
        assertEquals(2, maskStore.offsetOfStatics(3, originMapper))
        assertEquals(2, maskStore.offsetOfStatics(4, originMapper))
        assertEquals(2, maskStore.offsetOfStatics(5, originMapper))
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

        assertEquals(0, maskStore.offsetOfStatics(0, originMapper))
        assertEquals(1, maskStore.offsetOfStatics(1, originMapper))
        assertEquals(2, maskStore.offsetOfStatics(2, originMapper))
        assertEquals(2, maskStore.offsetOfStatics(3, originMapper))
        assertEquals(2, maskStore.offsetOfStatics(4, originMapper))
        assertEquals(4, maskStore.offsetOfStatics(10, originMapper))
        assertEquals(5, maskStore.offsetOfStatics(15, originMapper))
    }

}
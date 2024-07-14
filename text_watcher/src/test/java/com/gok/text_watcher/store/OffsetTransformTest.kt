package com.gok.text_watcher.store

import org.junit.Assert.assertEquals
import org.junit.Test
import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.addons.store.MaskStore
import com.gok.text_watcher.chain.ChainPatterns

class OffsetTransformTest {

    private val transformMapper = ChainPatterns.ToTransform().pattern()

    @Test
    fun get_emptyUnits_string_and_offset() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Empty(), MaskUnit.Empty()))

        assertEquals(0, maskStore.offsetOfStatics(0, transformMapper))
        assertEquals(0, maskStore.offsetOfStatics(1, transformMapper))
        assertEquals(0, maskStore.offsetOfStatics(2, transformMapper))
    }

    @Test
    fun `get replaceUnits string and offset`() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Replace('7'), MaskUnit.Empty()))

        assertEquals(1, maskStore.offsetOfStatics(0, transformMapper))
        assertEquals(0, maskStore.offsetOfStatics(1, transformMapper))
        assertEquals(0, maskStore.offsetOfStatics(2, transformMapper))
    }

    @Test
    fun `get staticUnits string and offset`() {
        val maskStore = MaskStore.Default(arrayOf(MaskUnit.Static('7'), MaskUnit.Empty()))

        assertEquals(1, maskStore.offsetOfStatics(0, transformMapper))
        assertEquals(1, maskStore.offsetOfStatics(1, transformMapper))
        assertEquals(1, maskStore.offsetOfStatics(2, transformMapper))
    }

    @Test
    fun `get multi staticUnits string and offset`() {
        val maskStore = MaskStore.Default(
            arrayOf(MaskUnit.Static('7'), MaskUnit.Static(' '), MaskUnit.Empty())
        )

        assertEquals(2, maskStore.offsetOfStatics(0, transformMapper))
        assertEquals(2, maskStore.offsetOfStatics(1, transformMapper))
        assertEquals(2, maskStore.offsetOfStatics(2, transformMapper))
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

        assertEquals(1, maskStore.offsetOfStatics(1, transformMapper))
        assertEquals(2, maskStore.offsetOfStatics(0, transformMapper))
        assertEquals(1, maskStore.offsetOfStatics(2, transformMapper))
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

        assertEquals(4, maskStore.offsetOfStatics(0, transformMapper))
        assertEquals(3, maskStore.offsetOfStatics(1, transformMapper))
        assertEquals(2, maskStore.offsetOfStatics(2, transformMapper))
        assertEquals(2, maskStore.offsetOfStatics(3, transformMapper))
        assertEquals(2, maskStore.offsetOfStatics(4, transformMapper))
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

        assertEquals(2, maskStore.offsetOfStatics(0, transformMapper))
        assertEquals(2, maskStore.offsetOfStatics(1, transformMapper))
        assertEquals(3, maskStore.offsetOfStatics(5, transformMapper))
        assertEquals(4, maskStore.offsetOfStatics(6, transformMapper))
        assertEquals(5, maskStore.offsetOfStatics(10, transformMapper))
    }
}
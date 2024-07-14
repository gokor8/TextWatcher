package com.gok.text_watcher.chain

import junit.framework.TestCase.assertTrue
import org.junit.Test
import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.addons.store.count_state.UnitStateFactory

class TransformChainTest {

    @Test
    fun `Static return true Static`() {
        val chain = ChainPatterns.ToTransform().pattern()

        val data = ChainData(MaskUnit.Static('1'), 0, 0)
        val actual = chain.handle(data)

        assertTrue(actual is UnitStateFactory.Static)
    }

    @Test
    fun `Static return false Finish`() {
        val chain = ChainPatterns.ToTransform().pattern()

        val data = ChainData(MaskUnit.Empty(), 0, 0)
        val actual = chain.handle(data)

        assertTrue(actual is UnitStateFactory.Finish)
    }

    @Test
    fun `Replace return true Replace`() {
        val chain = ChainPatterns.ToTransform().pattern()

        val data = ChainData(MaskUnit.Replace('1'), 0, 0)
        val actual = chain.handle(data)

        assertTrue(actual is UnitStateFactory.Static)
    }

    @Test
    fun `OffsetMoreIndex true return Empty`() {
        val chain = ChainPatterns.ToTransform().pattern()

        val data = ChainData(MaskUnit.Empty(), 1, 0)
        val actual = chain.handle(data)

        assertTrue(actual is UnitStateFactory.Empty)
    }

    @Test
    fun `OffsetMoreIndex false return Empty`() {
        val chain = ChainPatterns.ToTransform().pattern()

        val data = ChainData(MaskUnit.Empty(), 0, 0)
        val actual = chain.handle(data)

        assertTrue(actual is UnitStateFactory.Finish)
    }
}
package com.gok.text_watcher.addons.store

import com.gok.text_watcher.addons.store.count_state.UnitState
import com.gok.text_watcher.addons.store.index_handler.IndexState
import com.gok.text_watcher.addons.store.index_handler.IndexWrapper
import com.gok.text_watcher.chain.CountUnitChain


abstract class MaskStore(maskUnits: Array<MaskUnit>) : Store.Base(maskUnits) {

    override fun offsetOfStatics(offset: Int, chain: CountUnitChain): Int {
        var state: UnitState = UnitState.Start()

        while (state !is UnitState.Finish) {
            state = state.copy(state.getUnit(maskUnits), offset, chain)
        }

        return state.count
    }

    override fun getMaskedString(text: String): String {
        val masked = StringBuilder()

        var state = IndexState(
            IndexWrapper.Mask(maskUnits),
            IndexWrapper.Text(text)
        )

        while (state.canContinue()) {
            state.getWrapper().getSymbol().apply(masked::append)
            state = state.incCopy()
        }

        return masked.toString()
    }

    class Default(maskUnits: Array<MaskUnit>) : MaskStore(maskUnits)
}
package ru.gok.textwatcher.addons.store

import ru.gok.textwatcher.addons.store.count_state.CountUnitMapper
import ru.gok.textwatcher.addons.store.count_state.CountUnitState
import ru.gok.textwatcher.addons.store.index_handler.IndexState
import ru.gok.textwatcher.addons.store.index_handler.IndexWrapper

abstract class MaskStore(maskUnits: Array<MaskUnit>) : Store.Base(maskUnits) {

    override fun offsetOfStatics(offset: Int, stateMapper: CountUnitMapper): Int {
        var state: CountUnitState = CountUnitState.Start()

        var index = 0
        while (state !is CountUnitState.Finish) {
            state = state.copy(maskUnits.getOrNull(index), offset, stateMapper)
            index++
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
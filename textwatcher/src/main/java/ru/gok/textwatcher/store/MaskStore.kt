package ru.gok.textwatcher.store

import ru.gok.textwatcher.MaskUnit
import ru.gok.textwatcher.store.count_state.CountUnitMapper
import ru.gok.textwatcher.store.count_state.CountUnitState
import ru.gok.textwatcher.store.index_handler.IndexHandler
import ru.gok.textwatcher.store.index_handler.IndexWrapper

abstract class MaskStore(
    private val maskUnits: Array<MaskUnit>
) {
    val size: Int = maskUnits.size

    fun getWords(offset: Int) = IntRange(0, offset).count { maskUnits.getOrNull(it) is MaskUnit.Empty }

    fun offsetOfStatics(offset: Int, stateMapper: CountUnitMapper): Int {
        var state: CountUnitState = CountUnitState.Start()

        var index = 0
        while (state !is CountUnitState.Finish) {
            state = state.copy(maskUnits.getOrNull(index), offset, stateMapper)
            index++
        }

        return state.count
    }

    fun getMaskedString(text: String): String {
        val masked = StringBuilder()
        var helper = IndexHandler(
            IndexWrapper.Mask(maskUnits),
            IndexWrapper.Text(text)
        )

        while (helper.canContinue()) {
            helper.getWrapper().getSymbol().apply(masked::append)
            helper = helper.incCopy()
        }

        return masked.toString()
    }

    class Default(maskUnits: Array<MaskUnit>) : MaskStore(maskUnits)
}
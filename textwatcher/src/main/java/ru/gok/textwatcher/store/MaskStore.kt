package ru.gok.textwatcher.store

import ru.gok.textwatcher.MaskUnit
import ru.gok.textwatcher.store.index_handler.IndexHandler
import ru.gok.textwatcher.store.index_handler.IndexWrapper

abstract class MaskStore(
    private val maskUnits: Array<MaskUnit>
) {
    val size: Int = maskUnits.size

    fun offsetOfStatics(offset: Int) =
        IntRange(0, offset).count { maskUnits.getOrNull(it) is MaskUnit.Static }

    fun getMaskedString(text: String): String {
        val masked = StringBuilder()
        var helper = IndexHandler(
            IndexWrapper.Mask(maskUnits),
            IndexWrapper.Text(text)
        )

        while (helper.canWhile()) {
            masked.append(helper.getWrapper().getSymbol())
            helper = helper.incCopy()
        }

        return masked.toString()
    }

    class Default(maskUnits: Array<MaskUnit>) : MaskStore(maskUnits)
}
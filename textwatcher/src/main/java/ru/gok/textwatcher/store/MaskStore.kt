package ru.gok.textwatcher.store

import ru.gok.textwatcher.MaskUnit
import ru.gok.textwatcher.store.indexer.Indexer

abstract class MaskStore(
    private val maskUnits: Array<MaskUnit>
) {
    val size: Int = maskUnits.size

    fun offsetOfStatics(offset: Int) =
        IntRange(0, offset).count { maskUnits.getOrNull(it) is MaskUnit.Static }

    fun getMaskedString(text: String): String {
        val masked = StringBuilder()
        var indexer = Indexer()

        while (text.length != indexer.textIndex) {
            masked.append(indexer.getSymbol(maskUnits, text))
            indexer = indexer.copyNextIndex(maskUnits)
        }

        return masked.toString()
    }

    class Default(maskUnits: Array<MaskUnit>) : MaskStore(maskUnits)
}

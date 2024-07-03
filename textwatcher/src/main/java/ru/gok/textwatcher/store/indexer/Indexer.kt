package ru.gok.textwatcher.store.indexer

import ru.gok.textwatcher.MaskUnit

//       mask
//        |
// +7 123 -
//      |
//     text
data class Indexer(
    private val textState: IndexState = IndexState.Text(0),
    private val maskState: IndexState = IndexState.Mask(0)
) {
    val textIndex = textState.index

    fun getSymbol(maskUnits: Array<MaskUnit>, text: String): Char {
        val indexState = maskState.takeIf {
            maskUnits.getOrNull(maskState.index) is MaskUnit.Static
        } ?: textState

        return indexState.getSymbol(maskUnits, text)
    }

    fun copyNextIndex(maskUnits: Array<MaskUnit>) =
        if (maskUnits.getOrNull(maskState.index) is MaskUnit.Static)
            copy(maskState = maskState.copyInc())
        else
            copy(textState = textState.copyInc(), maskState = maskState.copyInc())
}

abstract class IndexState(val index: Int) {

    abstract fun copyInc(): IndexState

    abstract fun getSymbol(maskUnits: Array<MaskUnit>, text: String): Char


    class Mask(index: Int) : IndexState(index) {
        override fun copyInc() = Mask(index.inc())

        override fun getSymbol(maskUnits: Array<MaskUnit>, text: String) = maskUnits[index].symbol
    }

    class Text(index: Int) : IndexState(index) {
        override fun copyInc() = Text(index.inc())

        override fun getSymbol(maskUnits: Array<MaskUnit>, text: String) = text[index]
    }
}
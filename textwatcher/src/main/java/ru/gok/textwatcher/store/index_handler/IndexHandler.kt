package ru.gok.textwatcher.store.index_handler

import ru.gok.textwatcher.MaskUnit

class IndexHandler(
    private val maskWrapper: IndexWrapper.Mask,
    private val textWrapper: IndexWrapper.Text
) {
    fun canContinue() = maskWrapper.isUnitVisible() || !textWrapper.isLastIndex()

    fun getWrapper() = maskWrapper.takeIf {
        maskWrapper.isUnitStatic() || (maskWrapper.isUnitVisible() && textWrapper.isEmpty())
    } ?: textWrapper

    fun incCopy() = if (maskWrapper.isUnitStatic() || textWrapper.isLastIndex())
        IndexHandler(maskWrapper.incCopy(), textWrapper)
    else
        IndexHandler(maskWrapper.incCopy(), textWrapper.incCopy())
}

abstract class IndexWrapper(protected val index: Int) {

    abstract fun getSymbol(): Char

    abstract fun incCopy(): IndexWrapper


    class Mask(private val maskUnits: Array<MaskUnit>, index: Int = 0) : IndexWrapper(index) {
        private val currentUnit = maskUnits.getOrNull(index)

        override fun getSymbol() = maskUnits[index].symbol

        override fun incCopy() = Mask(maskUnits, index.inc())

        fun isUnitVisible() = currentUnit is MaskUnit.Visible

        fun isUnitStatic() = currentUnit is MaskUnit.Static
    }

    class Text(private val text: String, index: Int = 0) : IndexWrapper(index) {

        override fun getSymbol() = text[index]

        override fun incCopy() = Text(text, index.inc())

        fun isLastIndex() = text.length == index

        fun isEmpty() = text.getOrNull(index) == null
    }
}
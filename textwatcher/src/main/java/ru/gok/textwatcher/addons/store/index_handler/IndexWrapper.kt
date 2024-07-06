package ru.gok.textwatcher.addons.store.index_handler

import ru.gok.textwatcher.addons.store.MaskUnit

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
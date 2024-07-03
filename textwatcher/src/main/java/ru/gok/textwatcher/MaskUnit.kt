package ru.gok.textwatcher

interface MaskUnit {

    val symbol: Char

    fun createString(textChar: Char): String

    abstract class Base(override val symbol: Char) : MaskUnit {
        override fun createString(textChar: Char) = textChar.toString()
    }


    class Empty : Base(Char.MIN_VALUE)

    interface Visible : MaskUnit

    class Replace(symbol: Char) : Base(symbol), Visible

    class Static(symbol: Char) : Base(symbol), Visible {
        override fun createString(textChar: Char): String {
            return symbol + super.createString(textChar)
        }
    }
}
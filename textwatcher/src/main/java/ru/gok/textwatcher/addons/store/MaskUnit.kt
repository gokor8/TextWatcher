package ru.gok.textwatcher.addons.store

interface MaskUnit {

    val symbol: Char

    abstract class Base(override val symbol: Char) : MaskUnit


    class Empty : Base(Char.MIN_VALUE)

    interface Visible : MaskUnit

    class Replace(symbol: Char) : Base(symbol), Visible

    class Static(symbol: Char) : Base(symbol), Visible
}
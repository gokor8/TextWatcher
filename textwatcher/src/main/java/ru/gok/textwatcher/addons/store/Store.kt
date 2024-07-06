package ru.gok.textwatcher.addons.store

import ru.gok.textwatcher.chain.CountUnitChain

interface Store {

    fun offsetOfStatics(offset: Int, chain: CountUnitChain): Int

    fun getMaskedString(text: String): String


    abstract class Base(protected val maskUnits: Array<MaskUnit>) : Store
}
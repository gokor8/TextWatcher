package ru.gok.textwatcher.addons.store

import ru.gok.textwatcher.addons.store.count_state.CountUnitMapper

interface Store {

    fun offsetOfStatics(offset: Int, stateMapper: CountUnitMapper): Int

    fun getMaskedString(text: String): String


    abstract class Base(protected val maskUnits: Array<MaskUnit>) : Store
}
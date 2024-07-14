package com.gok.text_watcher.addons.store

import com.gok.text_watcher.chain.CountUnitChain

interface Store {

    fun offsetOfStatics(offset: Int, chain: CountUnitChain): Int

    fun getMaskedString(text: String): String


    abstract class Base(protected val maskUnits: Array<MaskUnit>) : Store
}
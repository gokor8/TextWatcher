package com.gok.text_watcher.addons.store.count_state

import com.gok.text_watcher.chain.ChainData
import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.chain.CountUnitChain

abstract class UnitState(
    private val commonIndex: Int,
    private val emptyIndex: Int,
    val count: Int
) {

    fun getUnit(units: Array<MaskUnit>) = units.getOrNull(commonIndex)

    fun copy(
        unit: MaskUnit?,
        offset: Int,
        chain: CountUnitChain
    ) = chain.handle(
        ChainData(unit, offset, emptyIndex)
    ).create(commonIndex.inc(), emptyIndex, count)

    class Start : UnitState(0,0, 0)

    class Static(index: Int, emptyIndex: Int, count: Int) : UnitState(index, emptyIndex, count.inc())

    class Empty(index: Int, emptyIndex: Int, count: Int) : UnitState(index, emptyIndex, count)

    class Finish(index: Int, emptyIndex: Int, count: Int) : UnitState(index, emptyIndex, count)
}
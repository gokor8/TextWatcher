package ru.gok.textwatcher.store.count_state

import ru.gok.textwatcher.MaskUnit

abstract class CountUnitState(private val emptyIndex: Int, val count: Int) {

    fun copy(
        unit: MaskUnit?,
        offset: Int,
        mapper: CountUnitMapper
    ) = mapper.map(unit, offset, emptyIndex).create(emptyIndex, count)

    class Start : CountUnitState(0, 0)

    class Static(index: Int, count: Int) : CountUnitState(index, count.inc())

    class Empty(index: Int, count: Int) : CountUnitState(index, count)

    class Finish(index: Int, count: Int) : CountUnitState(index, count)
}

interface CUSFactory {

    fun create(index: Int, count: Int): CountUnitState

    class Static : CUSFactory {
        override fun create(index: Int, count: Int) = CountUnitState.Static(index, count)
    }

    class OriginStatic : CUSFactory {
        override fun create(index: Int, count: Int) = CountUnitState.Static(index.inc(), count)
    }

    class Empty : CUSFactory {
        override fun create(index: Int, count: Int) = CountUnitState.Empty(index.inc(), count)
    }

    class Finish : CUSFactory {
        override fun create(index: Int, count: Int) = CountUnitState.Finish(index, count)
    }
}
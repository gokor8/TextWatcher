package com.gok.text_watcher.addons.store.count_state

interface UnitStateFactory {

    fun create(index: Int, emptyIndex: Int, count: Int): UnitState

    class Static : UnitStateFactory {
        override fun create(index: Int, emptyIndex: Int, count: Int) =
            UnitState.Static(index, emptyIndex, count)
    }

    class OriginStatic : UnitStateFactory {
        override fun create(index: Int, emptyIndex: Int, count: Int) =
            UnitState.Static(index, emptyIndex.inc(), count)
    }

    class Empty : UnitStateFactory {
        override fun create(index: Int, emptyIndex: Int, count: Int) =
            UnitState.Empty(index, emptyIndex.inc(), count)
    }

    class Finish : UnitStateFactory {
        override fun create(index: Int, emptyIndex: Int, count: Int) =
            UnitState.Finish(index, emptyIndex, count)
    }
}
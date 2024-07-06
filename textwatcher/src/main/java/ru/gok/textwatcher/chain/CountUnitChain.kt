package ru.gok.textwatcher.chain

import ru.gok.textwatcher.addons.store.count_state.UnitStateFactory

interface CountUnitChain : Chain<ChainData, UnitStateFactory> {

    open class Handler(
        private val condition: Condition,
        private val next: CountUnitChain
    ) : CountUnitChain {
        override fun handle(data: ChainData) =
            (if(condition.condition(data)) condition else next).handle(data)
    }

    abstract class Condition(factory: UnitStateFactory) : Base(factory) {

        abstract fun condition(data: ChainData): Boolean
    }

    abstract class Base(private val factory: UnitStateFactory) : CountUnitChain {
        override fun handle(data: ChainData) = factory
    }
}
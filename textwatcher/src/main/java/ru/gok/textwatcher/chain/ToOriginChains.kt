package ru.gok.textwatcher.chain

import ru.gok.textwatcher.addons.store.count_state.UnitStateFactory

class IsStaticOrigin : CountUnitChain.Condition(UnitStateFactory.OriginStatic()) {
    private val static = IsStaticChain()

    override fun condition(data: ChainData) = data.run {
        static.condition(this) && currentIndex < offset
    }
}
package com.gok.text_watcher.chain

import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.addons.store.count_state.UnitStateFactory

class IsStaticChain : CountUnitChain.Condition(UnitStateFactory.Static()) {
    override fun condition(data: ChainData) = data.unit is MaskUnit.Static
}

class IsReplaceChain : CountUnitChain.Condition(UnitStateFactory.Static()) {
    override fun condition(data: ChainData) = data.run {
        unit is MaskUnit.Replace && offset <= currentIndex
    }
}

class OffsetMoreIndex : CountUnitChain.Condition(UnitStateFactory.Empty()) {
    override fun condition(data: ChainData) = data.run { offset > currentIndex }
}

class Finish : CountUnitChain.Base(UnitStateFactory.Finish())
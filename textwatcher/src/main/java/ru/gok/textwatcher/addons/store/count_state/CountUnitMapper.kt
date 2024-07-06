package ru.gok.textwatcher.addons.store.count_state

import ru.gok.textwatcher.addons.store.MaskUnit

interface CountUnitMapper {

    fun map(
        unit: MaskUnit?,
        offset: Int,
        index: Int
    ): CUSFactory


    class ToTransform : CountUnitMapper {
        override fun map(
            unit: MaskUnit?,
            offset: Int,
            index: Int
        )= when {
            unit is MaskUnit.Static -> CUSFactory.Static()
            unit is MaskUnit.Replace && offset <= index -> CUSFactory.Static()
            offset > index -> CUSFactory.Empty()
            else -> CUSFactory.Finish()
        }
    }

    class ToOrigin : CountUnitMapper {
        override fun map(
            unit: MaskUnit?,
            offset: Int,
            index: Int
        ) = when {
            index >= offset -> CUSFactory.Finish()
            unit is MaskUnit.Static -> CUSFactory.OriginStatic()
            index < offset -> CUSFactory.Empty()
            else -> CUSFactory.Finish()
        }
    }
}
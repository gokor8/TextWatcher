package com.gok.text_watcher.chain

interface ChainPatterns {

    fun pattern(): CountUnitChain


    class ToTransform : ChainPatterns {
        override fun pattern() = CountUnitChain.Handler(
            IsStaticChain(),
            CountUnitChain.Handler(
                IsReplaceChain(),
                CountUnitChain.Handler(
                    OffsetMoreIndex(),
                    Finish()
                )
            )
        )
    }
    /*unit is MaskUnit.Static -> UnitStateFactory.Static()
    unit is MaskUnit.Replace && offset <= index -> UnitStateFactory.Static()
    offset > index -> UnitStateFactory.Empty()
    else -> UnitStateFactory.Finish()*/



    class ToOrigin : ChainPatterns {
        override fun pattern() = CountUnitChain.Handler(
            IsStaticOrigin(),
            CountUnitChain.Handler(OffsetMoreIndex(), Finish())
        )
    }

    /*unit is MaskUnit.Static && index < offset -> UnitStateFactory.OriginStatic()
    offset > index -> UnitStateFactory.Empty()
    else -> UnitStateFactory.Finish()*/
}
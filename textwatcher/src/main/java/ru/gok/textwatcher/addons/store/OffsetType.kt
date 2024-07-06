package ru.gok.textwatcher.addons.store

import ru.gok.textwatcher.chain.*

enum class OffsetType {

    TO_TRANSFORM {
        override val chainPattern = ChainPatterns.ToTransform()

        override fun operation(offset: Int, staticsOffset: Int) = offset + staticsOffset
    },
    TO_ORIGIN {
        override val chainPattern = ChainPatterns.ToOrigin()

        override fun operation(offset: Int, staticsOffset: Int) = offset - staticsOffset
    };

    protected abstract val chainPattern: ChainPatterns

    fun getFullOffset(offset: Int, store: MaskStore) = operation(
        offset, store.offsetOfStatics(offset, chainPattern.pattern())
    )

    protected abstract fun operation(offset: Int, staticsOffset: Int): Int
}
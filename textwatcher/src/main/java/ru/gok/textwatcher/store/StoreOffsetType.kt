package ru.gok.textwatcher.store

import ru.gok.textwatcher.store.count_state.CountUnitMapper

enum class StoreOffsetType {

    TO_TRANSFORM {
        override fun mapper() = CountUnitMapper.ToTransform()

        override fun operation(offset: Int, staticsOffset: Int) = offset + staticsOffset
    },
    TO_ORIGIN {
        override fun mapper() = CountUnitMapper.ToOrigin()

        override fun operation(offset: Int, staticsOffset: Int) = offset - staticsOffset
    };

    protected abstract fun mapper(): CountUnitMapper

    fun getFullOffset(offset: Int, store: MaskStore) = operation(
        offset, store.offsetOfStatics(offset, mapper())
    )

    protected abstract fun operation(offset: Int, staticsOffset: Int): Int
}
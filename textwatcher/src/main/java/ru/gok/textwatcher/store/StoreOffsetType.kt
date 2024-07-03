package ru.gok.textwatcher.store

enum class StoreOffsetType {

    TO_TRANSFORM {
        override fun operation(offset: Int, staticsOffset: Int) = offset + staticsOffset
    }, TO_ORIGIN {
        override fun operation(offset: Int, staticsOffset: Int) = offset - staticsOffset
    };

    fun getFullOffset(offset: Int, store: MaskStore) = operation(offset, store.offsetOfStatics(offset))

    protected abstract fun operation(offset: Int, staticsOffset: Int): Int
}
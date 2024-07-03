package ru.gok.textwatcher

import androidx.compose.ui.text.input.OffsetMapping
import ru.gok.textwatcher.store.MaskStore
import ru.gok.textwatcher.store.StoreOffsetType

class MaskOffsetMapping(private val store: MaskStore) : OffsetMapping {

    override fun originalToTransformed(offset: Int) =
        StoreOffsetType.TO_TRANSFORM.getFullOffset(offset, store)

    override fun transformedToOriginal(offset: Int) =
        StoreOffsetType.TO_ORIGIN.getFullOffset(offset, store)
}
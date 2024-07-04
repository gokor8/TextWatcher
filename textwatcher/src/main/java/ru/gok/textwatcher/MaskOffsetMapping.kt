package ru.gok.textwatcher

import android.util.Log
import androidx.compose.ui.text.input.OffsetMapping
import ru.gok.textwatcher.store.MaskStore
import ru.gok.textwatcher.store.StoreOffsetType

class MaskOffsetMapping(private val store: MaskStore) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        Log.e("p2p","originalToTransformed $offset")
        return StoreOffsetType.TO_TRANSFORM.getFullOffset(offset, store).also {
            Log.e("p2p after", "originalToTransformed $it")
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        Log.e("p2p", "transformedToOriginal $offset")
        return StoreOffsetType.TO_ORIGIN.getFullOffset(offset, store).also {
            Log.e("p2p after", "transformedToOriginal $it")
        }
    }
}
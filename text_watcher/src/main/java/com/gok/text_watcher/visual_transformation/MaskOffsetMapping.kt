package com.gok.text_watcher.visual_transformation

import android.util.Log
import androidx.compose.ui.text.input.OffsetMapping
import com.gok.text_watcher.addons.filters.Limiter
import com.gok.text_watcher.addons.store.MaskStore
import com.gok.text_watcher.addons.store.OffsetType

class MaskOffsetMapping(private val store: MaskStore, private val limiter: Limiter) : OffsetMapping {

    override fun originalToTransformed(offset: Int) = limiter.subOffset(offset).let { subOffset ->
        Log.e("p2p", "originalToTransformed $subOffset")

        OffsetType.TO_TRANSFORM.getFullOffset(subOffset, store).also {
            Log.e("p2p after", "originalToTransformed $it")
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        Log.e("p2p", "transformedToOriginal $offset")

        return OffsetType.TO_ORIGIN.getFullOffset(offset, store).also {
            Log.e("p2p after", "transformedToOriginal $it")
        }
    }
}
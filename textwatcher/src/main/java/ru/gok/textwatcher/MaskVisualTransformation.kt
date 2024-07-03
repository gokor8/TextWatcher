package ru.gok.textwatcher

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import ru.gok.textwatcher.store.MaskStore
import ru.gok.textwatcher.store.StoreOffsetType
import ru.gok.textwatcher.text_filter.TextFilter

class MaskVisualTransformation(
    private val store: MaskStore,
    private val filter: TextFilter
) : VisualTransformation {

    constructor(mask: String, filter: TextFilter) : this(
        MaskStore.Default(mask.map { MaskUnit.Replace(it) }.toTypedArray()),
        filter
    )

    override fun filter(text: AnnotatedString) = TransformedText(
        AnnotatedString(
            store.getMaskedString(filter.filter(text.text))
        ),
        MaskOffsetMapping(store)
    )
}
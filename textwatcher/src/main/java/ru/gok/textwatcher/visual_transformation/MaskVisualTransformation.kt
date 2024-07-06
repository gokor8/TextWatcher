package ru.gok.textwatcher.visual_transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import ru.gok.textwatcher.addons.filters.Limiter
import ru.gok.textwatcher.addons.store.MaskStore

class MaskVisualTransformation(
    private val store: MaskStore,
    private val limit: Limiter
) : VisualTransformation {

    override fun filter(text: AnnotatedString) = TransformedText(
        AnnotatedString(
            store.getMaskedString(limit.filter(text.text))
        ),
        MaskOffsetMapping(store, limit)
    )

    fun subText(text: String, type: TransformationType) =
        if (type == TransformationType.Transform) limit.filter(text) else text
}
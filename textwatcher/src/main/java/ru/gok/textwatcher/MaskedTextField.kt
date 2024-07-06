package ru.gok.textwatcher

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gok.textwatcher.visual_transformation.MaskVisualTransformation

@Composable
fun MaskedTextField(
    text: String,
    mask: MaskVisualTransformation,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.padding(top = 8.dp),
        value = text,
        onValueChange = onValueChange,
        visualTransformation = mask
    )
}
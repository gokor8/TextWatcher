package ru.gok.textwatcher

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gok.textwatcher.store.MaskStore

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
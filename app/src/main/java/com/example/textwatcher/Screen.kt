package com.example.textwatcher

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.textwatcher.ui.theme.TextWatcherTheme
import com.gok.text_watcher.MaskedTextField

const val TEXT_FIELD_TAG = "TEXT_FIELD_TAG"


@Composable
fun Screen(viewModel: MainViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var text by remember { mutableStateOf("") }
        MaskedTextField(text, viewModel.maskVisualTransformation, Modifier.testTag(TEXT_FIELD_TAG)) {
            text = it
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextWatcherTheme {
        Screen(MainViewModel())
    }
}
package com.example.textwatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.textwatcher.ui.theme.TextWatcherTheme
import com.gok.text_watcher.MaskedTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextWatcherTheme {
                val viewModel: MainViewModel = viewModel()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(10.dp))
                        var text by remember { mutableStateOf("") }
                        MaskedTextField(
                            text,
                            viewModel.phoneVisualTransformation,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        ) {
                            text = it
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        MaskedTextField(text, viewModel.cardVisualTransformation) {
                            text = it
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        MaskedTextField(text, viewModel.mixVisualTransformation) {
                            text = it
                        }
                    }
                }
            }
        }
    }
}
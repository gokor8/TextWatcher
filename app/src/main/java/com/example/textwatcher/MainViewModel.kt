package com.example.textwatcher

import androidx.lifecycle.ViewModel
import com.gok.text_watcher.addons.filters.Limiter
import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.visual_transformation.MaskVisualTransformation
import com.gok.text_watcher.addons.store.MaskStore

class MainViewModel : ViewModel() {

    val phoneVisualTransformation = MaskVisualTransformation(
        MaskStore.Default(
            arrayOf(
                MaskUnit.Static('+'),
                MaskUnit.Static('7'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
            )
        ), Limiter.Limited(10)
    )

    val cardVisualTransformation = MaskVisualTransformation(
        MaskStore.Default(
            arrayOf(
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static('-'),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty()
            )
        ), Limiter.Limited(16)
    )

    val mixVisualTransformation = MaskVisualTransformation(
        MaskStore.Default(
            arrayOf(
                MaskUnit.Static('+'),
                MaskUnit.Replace('7'),
                MaskUnit.Static('('),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Empty(),
                MaskUnit.Static(')'),
                MaskUnit.Static('.'),
                MaskUnit.Static('.'),
                MaskUnit.Static('.'),
            )
        ), Limiter.Limited(3)
    )
}
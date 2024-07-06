package com.example.textwatcher

import androidx.lifecycle.ViewModel
import ru.gok.textwatcher.addons.filters.Limiter
import ru.gok.textwatcher.addons.store.MaskUnit
import ru.gok.textwatcher.visual_transformation.MaskVisualTransformation
import ru.gok.textwatcher.addons.store.MaskStore

class MainViewModel : ViewModel() {

    val maskVisualTransformation = MaskVisualTransformation(
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
}
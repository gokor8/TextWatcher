package com.example.textwatcher

import androidx.lifecycle.ViewModel
import ru.gok.textwatcher.MaskUnit
import ru.gok.textwatcher.MaskVisualTransformation
import ru.gok.textwatcher.store.MaskStore
import ru.gok.textwatcher.text_filter.TextFilter

class MainViewModel : ViewModel() {

    val maskVisualTransformation = MaskVisualTransformation(
        MaskStore.Default(
//            arrayOf(
//                MaskUnit.Empty(),
//                MaskUnit.Static('-'),
//                MaskUnit.Empty()
//            )
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
        ), TextFilter.Base(10)
    )
}
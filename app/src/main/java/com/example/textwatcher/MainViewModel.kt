package com.example.textwatcher

import androidx.lifecycle.ViewModel
import com.gok.text_watcher.addons.filters.Limiter
import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.visual_transformation.MaskVisualTransformation
import com.gok.text_watcher.addons.store.MaskStore

class MainViewModel : ViewModel() {

    val maskVisualTransformation = MaskVisualTransformation(
        MaskStore.Default(
//            arrayOf(
//                MaskUnit.Replace('8'),
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
        ), Limiter.Limited(10)
    )
}
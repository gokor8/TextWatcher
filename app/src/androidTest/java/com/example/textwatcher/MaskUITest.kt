package com.example.textwatcher

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.gok.text_watcher.MaskedTextField
import com.gok.text_watcher.addons.filters.Limiter
import com.gok.text_watcher.addons.store.MaskStore
import com.gok.text_watcher.addons.store.MaskUnit
import com.gok.text_watcher.visual_transformation.MaskVisualTransformation
import org.junit.Rule
import org.junit.Test

private class TestRuleSettings(
    private val tag: String,
    private val rule: ComposeContentTestRule
) {

    fun prepare(mask: MaskVisualTransformation) {
        rule.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaskedTextField(text = text, mask = mask, modifier = Modifier.testTag(tag)) {
                text = it
            }
        }
    }
}

class MaskUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val tag = "mask"

    private val testRuleSettings = TestRuleSettings(tag, composeTestRule)

    @Test
    fun test_SESE_with_limit() {
        testRuleSettings.prepare(
            MaskVisualTransformation(
                MaskStore.Default(
                    arrayOf(
                        MaskUnit.Static('-'),
                        MaskUnit.Empty(),
                        MaskUnit.Static('-'),
                        MaskUnit.Empty()
                    )
                ),
                Limiter.Limited(2)
            )
        )

        composeTestRule.onNodeWithTag(tag).performTextInput("12")
        composeTestRule.onNodeWithTag(tag).assert(hasText("-1-2"))

        composeTestRule.onNodeWithTag(tag).performTextInput("1234")
        composeTestRule.onNodeWithTag(tag).assert(hasText("-1-2"))
    }

    @Test
    fun test_SESE_with_out_limit() {
        testRuleSettings.prepare(
            MaskVisualTransformation(
                MaskStore.Default(
                    arrayOf(
                        MaskUnit.Static('-'),
                        MaskUnit.Empty(),
                        MaskUnit.Static('-'),
                        MaskUnit.Empty()
                    )
                ),
                Limiter.Unlimited()
            )
        )

        composeTestRule.onNodeWithTag(tag).performTextInput("12")
        composeTestRule.onNodeWithTag(tag).assert(hasText("-1-2"))

        composeTestRule.onNodeWithTag(tag).performTextInput("1234")
        composeTestRule.onNodeWithTag(tag).assert(hasText("-1-21234"))
    }

    @Test
    fun test_mix_with_limit() {
        testRuleSettings.prepare(
            MaskVisualTransformation(
                MaskStore.Default(
                    arrayOf(
                        MaskUnit.Replace('7'),
                        MaskUnit.Empty(),
                        MaskUnit.Static('-'),
                        MaskUnit.Empty()
                    )
                ),
                Limiter.Limited(2)
            )
        )

        composeTestRule.onNodeWithTag(tag).assert(hasText("7"))

        composeTestRule.onNodeWithTag(tag).performTextInput("1")
        composeTestRule.onNodeWithTag(tag).assert(hasText("1"))

        composeTestRule.onNodeWithTag(tag).performTextInput("2")
        composeTestRule.onNodeWithTag(tag).assert(hasText("12-"))

        composeTestRule.onNodeWithTag(tag).performTextInput("3")
        composeTestRule.onNodeWithTag(tag).assert(hasText("12-"))
    }

    @Test
    fun test_mix_with_un_limit() {
        testRuleSettings.prepare(
            MaskVisualTransformation(
                MaskStore.Default(
                    arrayOf(
                        MaskUnit.Replace('7'),
                        MaskUnit.Empty(),
                        MaskUnit.Static('-'),
                        MaskUnit.Empty()
                    )
                ),
                Limiter.Unlimited()
            )
        )
        composeTestRule.onNodeWithTag(tag).assert(hasText("7"))

        composeTestRule.onNodeWithTag(tag).performTextInput("1")
        composeTestRule.onNodeWithTag(tag).assert(hasText("1"))

        composeTestRule.onNodeWithTag(tag).performTextInput("2")
        composeTestRule.onNodeWithTag(tag).assert(hasText("12-"))

        composeTestRule.onNodeWithTag(tag).performTextInput("3")
        composeTestRule.onNodeWithTag(tag).assert(hasText("12-3"))

        composeTestRule.onNodeWithTag(tag).performTextInput("1234")
        composeTestRule.onNodeWithTag(tag).assert(hasText("12-31234"))
    }
}
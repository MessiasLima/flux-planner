package io.appoutlet.flux.desktop.common.components

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.onNodeWithText
import io.appoutlet.flux.desktop.testingutils.UiTest
import io.appoutlet.flux.desktop.testingutils.assertIsInvisibleToUser
import io.appoutlet.flux.desktop.testingutils.assertIsVisibleToUser
import org.junit.Test

@ExperimentalComposeUiApi
class TextFieldErrorMessageTest : UiTest(){
    @Test
    fun `should show error message`() {
        val message = "My error message"

        composeTestRule.setContent {
            TextFieldErrorMessage(true, message)
        }

        composeTestRule.onNodeWithText(message)
            .assertExists()
            .assertIsVisibleToUser()
    }

    @Test
    fun `should not show error message`() {
        val message = "My error message"

        composeTestRule.setContent {
            TextFieldErrorMessage(false, message)
        }

        composeTestRule.onNodeWithText(message)
            .assertExists()
            .assertIsInvisibleToUser()
    }
}

package io.appoutlet.flux.desktop.common.components

import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.appoutlet.flux.desktop.common.FluxIcons
import io.appoutlet.flux.desktop.common.Mail
import io.appoutlet.flux.desktop.testingutils.UiTest
import io.appoutlet.flux.desktop.testingutils.hasError
import org.junit.Test

class PasswordTextFieldTest : UiTest() {
    @Test
    fun `should show encrypted password`() {
        composeTestRule.setContent {
            PasswordTextField(value = "123", enabled = true, error = false, onValueChange = {})
        }

        composeTestRule.onNodeWithText("•••")
            .assertExists()
    }

    @Test
    fun `should toggle password visibility`() {
        composeTestRule.setContent {
            PasswordTextField(value = "123", enabled = true, error = false, onValueChange = {})
        }

        composeTestRule.onNodeWithText("•••").assertExists()
        composeTestRule.onNodeWithText("123").assertDoesNotExist()

        composeTestRule.onNodeWithContentDescription("Show password").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("123").assertExists()
        composeTestRule.onNodeWithText("•••").assertDoesNotExist()

        composeTestRule.onNodeWithContentDescription("Hide password").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("•••").assertExists()
        composeTestRule.onNodeWithText("123").assertDoesNotExist()
    }

    @Test
    fun `should show leading icon`() {
        composeTestRule.setContent {
            PasswordTextField(
                leadingIcon = FluxIcons.Mail,
                enabled = true,
                error = false,
                onValueChange = {},
                value = ""
            )
        }

        composeTestRule.onNodeWithContentDescription("Leading icon")
            .assertExists()
    }

    @Test
    fun `should show error state`() {
        composeTestRule.setContent {
            PasswordTextField(
                leadingIcon = FluxIcons.Mail,
                enabled = true,
                error = true,
                onValueChange = {},
                value = ""
            )
        }

        composeTestRule.onNode(hasError("Invalid input"))
            .assertExists()
    }
}

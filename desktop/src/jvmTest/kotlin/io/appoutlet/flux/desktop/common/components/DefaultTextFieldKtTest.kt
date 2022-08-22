package io.appoutlet.flux.desktop.common.components

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.appoutlet.flux.desktop.common.FluxIcons
import io.appoutlet.flux.desktop.common.Mail
import io.appoutlet.flux.desktop.testingutils.hasError
import org.junit.Rule
import org.junit.Test

class DefaultTextFieldKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `should show value`() {
        val fixtValue = "some value"
        composeTestRule.setContent {
            DefaultTextField(value = fixtValue, onValueChange = {}, enabled = true, error = false)
        }

        composeTestRule.onNodeWithText(fixtValue)
            .assertExists()
            .assertIsEnabled()
            .performClick()
    }

    @Test
    fun `should show idle state`() {
        composeTestRule.setContent {
            DefaultTextField(value = "", onValueChange = {}, enabled = true, error = false)
        }

        composeTestRule.onNodeWithContentDescription("Clear")
            .assertDoesNotExist()

        composeTestRule.onNodeWithText("")
            .assertExists()
            .assertIsEnabled()
    }

    @Test
    fun `should show disable`() {
        composeTestRule.setContent {
            DefaultTextField(value = "", onValueChange = {}, enabled = false, error = false)
        }

        composeTestRule.onNodeWithContentDescription("Clear")
            .assertDoesNotExist()

        composeTestRule.onNodeWithText("")
            .assertExists()
            .assertIsNotEnabled()
    }

    @Test
    fun `should show error`() {
        composeTestRule.setContent {
            DefaultTextField(value = "", onValueChange = {}, enabled = true, error = true)
        }

        composeTestRule.onNode(hasError("Invalid input"))
            .assertExists()
    }

    @Test
    fun `should show leading icon`() {
        composeTestRule.setContent {
            DefaultTextField(
                value = "",
                onValueChange = {},
                enabled = true,
                error = false,
                leadingIcon = FluxIcons.Mail
            )
        }

        composeTestRule.onNodeWithContentDescription("Leading icon")
            .assertExists()
    }
}

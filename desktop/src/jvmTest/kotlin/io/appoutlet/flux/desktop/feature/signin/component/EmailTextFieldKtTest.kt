package io.appoutlet.flux.desktop.feature.signin.component

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class EmailTextFieldKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `should show value`() {
        val fixtValue = "some value"
        composeTestRule.setContent {
            EmailTextField(value = fixtValue, onValueChange = {}, enabled = true, error = false)
        }

        composeTestRule.onNodeWithText(fixtValue)
            .assertExists()
            .assertIsEnabled()
            .performClick()
    }

    @Test
    fun `should show idle state`() {
        composeTestRule.setContent {
            EmailTextField(value = "", onValueChange = {}, enabled = true, error = false)
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
            EmailTextField(value = "", onValueChange = {}, enabled = false, error = false)
        }

        composeTestRule.onNodeWithContentDescription("Clear")
            .assertDoesNotExist()

        composeTestRule.onNodeWithText("")
            .assertExists()
            .assertIsNotEnabled()
    }
}

package io.appoutlet.flux.desktop.common.components

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToString
import io.appoutlet.flux.desktop.testingutils.assertIsInvisibleToUser
import io.appoutlet.flux.desktop.testingutils.assertIsVisibleToUser
import org.junit.Rule
import org.junit.Test

@ExperimentalComposeUiApi
class FluxProgressBarTest {
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun `should show progress bar`() {
        composeTestRule.setContent {
            FluxProgressBar(isLoading = true)
        }

        println(composeTestRule.onRoot().printToString())

        composeTestRule.onNodeWithContentDescription("Progress bar")
            .assertIsVisibleToUser()
    }

    @Test
    fun `should not show progress bar`() {
        composeTestRule.setContent {
            FluxProgressBar(isLoading = false)
        }

        composeTestRule.onNodeWithContentDescription("Progress bar")
            .assertIsInvisibleToUser()
    }
}

package io.appoutlet.flux.desktop.common.components

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.onNodeWithContentDescription
import io.appoutlet.flux.desktop.testingutils.UiTest
import io.appoutlet.flux.desktop.testingutils.assertIsInvisibleToUser
import io.appoutlet.flux.desktop.testingutils.assertIsVisibleToUser
import org.junit.Test

@ExperimentalComposeUiApi
class FluxProgressBarTest : UiTest() {
    @Test
    fun `should show progress bar`() {
        composeTestRule.setContent {
            FluxProgressBar(isLoading = true)
        }

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

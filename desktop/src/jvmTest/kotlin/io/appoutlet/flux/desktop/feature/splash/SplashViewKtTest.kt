package io.appoutlet.flux.desktop.feature.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToString
import io.appoutlet.flux.common.feature.splash.SplashUiState
import io.appoutlet.flux.common.feature.splash.SplashViewModel
import io.appoutlet.flux.desktop.testingutils.UiTest
import io.appoutlet.flux.desktop.testingutils.assertIsInvisibleToUser
import io.appoutlet.flux.desktop.testingutils.assertIsVisibleToUser
import io.appoutlet.karavel.Karavel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlin.test.Test

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@FlowPreview
internal class SplashViewKtTest : UiTest() {
    private val mockSplashViewModel: SplashViewModel = mockk()
    private val mockKaravel: Karavel = mockk()

    @Test
    fun `should show idle state`() {
        every { mockSplashViewModel.uiState } returns flowOf(SplashUiState.Idle)

        composeTestRule.setContent {
            SplashView(viewModel = mockSplashViewModel, karavel = mockKaravel)
        }

        composeTestRule.onNodeWithContentDescription("Progress bar")
            .assertExists()
            .assertIsInvisibleToUser()

        println(composeTestRule.onRoot().printToString())

        composeTestRule.onNodeWithText("Occurred an error when verifying the logged user")
            .assertExists()
            .assertIsInvisibleToUser()

        composeTestRule.onNodeWithText("Try again")
            .assertExists()
            .assertIsInvisibleToUser()

        composeTestRule.onNodeWithText("Login with another user")
            .assertExists()
            .assertIsInvisibleToUser()
    }

    @Test
    fun `should show loading state`() {
        every { mockSplashViewModel.uiState } returns flowOf(SplashUiState.Loading)

        composeTestRule.setContent {
            SplashView(viewModel = mockSplashViewModel, karavel = mockKaravel)
        }

        composeTestRule.onNodeWithContentDescription("Progress bar")
            .assertExists()
            .assertIsVisibleToUser()
    }
}

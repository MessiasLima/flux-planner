package io.appoutlet.flux.desktop.common

import androidx.compose.ui.test.junit4.createComposeRule
import io.appoutlet.flux.common.feature.BaseViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class ViewModelInitializerKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `should init viewModel`() = runBlocking {
        val mockViewModel: BaseViewModel = mockk()
        every { mockViewModel.init(any()) } returns Unit
        val testScope = this
        composeTestRule.setContent {
            mockViewModel.init(testScope)
        }
        composeTestRule.waitForIdle()
        verify { mockViewModel.init(testScope) }
    }
}

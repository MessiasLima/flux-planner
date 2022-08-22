package io.appoutlet.flux.desktop.testingutils

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

abstract class UiTest {
    @get:Rule
    val composeTestRule = createComposeRule()
}

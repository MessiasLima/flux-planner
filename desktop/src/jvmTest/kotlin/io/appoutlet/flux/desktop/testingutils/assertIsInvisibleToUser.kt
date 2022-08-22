package io.appoutlet.flux.desktop.testingutils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert

@ExperimentalComposeUiApi
fun SemanticsNodeInteraction.assertIsInvisibleToUser() = assert(isInvisibleToUser())

@ExperimentalComposeUiApi
fun SemanticsNodeInteraction.assertIsVisibleToUser() = assert(isVisibleToUser())

@ExperimentalComposeUiApi
fun isInvisibleToUser() = SemanticsMatcher.expectValue(SemanticsProperties.InvisibleToUser, Unit)

@ExperimentalComposeUiApi
fun isVisibleToUser() = SemanticsMatcher.keyNotDefined(SemanticsProperties.InvisibleToUser)

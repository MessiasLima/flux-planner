package io.appoutlet.flux.desktop.testingutils

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher

internal fun hasError(errorMessage: String) =
    SemanticsMatcher.expectValue(SemanticsProperties.Error, errorMessage)

package io.appoutlet.flux.desktop.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import io.appoutlet.flux.common.feature.BaseViewModel

@Composable
fun BaseViewModel.initialize() {
    val scope = rememberCoroutineScope()
    LaunchedEffect(this) {
        init(scope)
    }
}

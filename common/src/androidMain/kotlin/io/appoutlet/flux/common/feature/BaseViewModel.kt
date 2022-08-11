package io.appoutlet.flux.common.feature

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope

@Suppress("UnnecessaryAbstractClass")
actual abstract class BaseViewModel : ViewModel() {
    private lateinit var _viewModelScope: CoroutineScope

    protected actual val viewModelScope: CoroutineScope
        get() = _viewModelScope

    actual fun init(composableScope: CoroutineScope) {
        _viewModelScope = composableScope
    }
}

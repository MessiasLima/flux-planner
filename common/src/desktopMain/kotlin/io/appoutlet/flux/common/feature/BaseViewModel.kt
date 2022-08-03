package io.appoutlet.flux.common.feature

import kotlinx.coroutines.CoroutineScope

@Suppress("UnnecessaryAbstractClass")
actual abstract class BaseViewModel {
    private lateinit var _viewModelScope: CoroutineScope
    protected actual val viewModelScope: CoroutineScope
        get() = _viewModelScope

    fun init(composableScope: CoroutineScope) {
        _viewModelScope = composableScope
    }
}

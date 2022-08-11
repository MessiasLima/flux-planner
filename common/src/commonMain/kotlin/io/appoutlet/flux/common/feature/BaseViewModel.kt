package io.appoutlet.flux.common.feature

import kotlinx.coroutines.CoroutineScope

@Suppress("UnnecessaryAbstractClass")
expect abstract class BaseViewModel constructor() {
    protected val viewModelScope: CoroutineScope

    fun init(composableScope: CoroutineScope)
}

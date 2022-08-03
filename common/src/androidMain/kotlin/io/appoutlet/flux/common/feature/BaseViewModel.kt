package io.appoutlet.flux.common.feature

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as androidViewModelScope

@Suppress("UnnecessaryAbstractClass")
actual abstract class BaseViewModel : ViewModel() {
    protected actual val viewModelScope: CoroutineScope
        get() = androidViewModelScope
}

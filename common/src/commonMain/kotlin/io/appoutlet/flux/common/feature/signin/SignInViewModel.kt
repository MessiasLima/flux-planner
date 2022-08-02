package io.appoutlet.flux.common.feature.signin

import io.appoutlet.flux.common.feature.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel : BaseViewModel() {
    private val mutableUiState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val uiState = mutableUiState.asStateFlow()

    fun login(email: String, password: String) {

    }
}

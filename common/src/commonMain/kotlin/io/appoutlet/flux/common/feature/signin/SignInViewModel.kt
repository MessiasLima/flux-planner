package io.appoutlet.flux.common.feature.signin

import io.appoutlet.flux.common.feature.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class SignInViewModel(
    private val signInOrchestrator: SignInOrchestrator
) : BaseViewModel() {
    private val mutableUiState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val uiState = mutableUiState.asStateFlow()

    fun login(email: String, password: String) {
        signInOrchestrator.signIn(email, password)
            .onStart { mutableUiState.emit(SignInUiState.Loading) }
            .catch { mutableUiState.emit(SignInUiState.Error) }
            .onEach { mutableUiState.emit(SignInUiState.Success) }
            .launchIn(viewModelScope)
    }
}

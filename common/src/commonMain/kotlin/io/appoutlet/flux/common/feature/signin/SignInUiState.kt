package io.appoutlet.flux.common.feature.signin

sealed class SignInUiState {
    object Idle : SignInUiState()
    object Loading : SignInUiState()
    object Error : SignInUiState()
    object Success : SignInUiState()
}

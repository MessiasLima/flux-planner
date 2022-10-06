package io.appoutlet.flux.common.feature.emailverification

import io.appoutlet.flux.common.feature.BaseViewModel
import io.appoutlet.flux.common.feature.splash.exception.UserNotLoggedException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmailVerificationViewModel(
    private val orchestrator: EmailVerificationOrchestrator,
) : BaseViewModel() {
    private val mutableUiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = mutableUiState.asStateFlow()

    fun logout() {
        orchestrator.logout()
        mutableUiState.value = UiState.Logout
    }

    fun resendVerificationEmail() {
        viewModelScope.launch {
            try {
                mutableUiState.value = UiState.ResendingEmail
                orchestrator.resendVerificationEmail()
                mutableUiState.value = UiState.EmailSent
            } catch (userNotLoggedException: UserNotLoggedException) {
                userNotLoggedException.printStackTrace()
                mutableUiState.value = UiState.EmailFailure
            }
        }
    }

    sealed class UiState {
        object Idle : UiState()
        object ResendingEmail : UiState()
        object EmailSent : UiState()
        object EmailFailure : UiState()
        object Logout : UiState()
    }
}

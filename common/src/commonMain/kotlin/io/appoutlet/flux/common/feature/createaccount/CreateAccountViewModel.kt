package io.appoutlet.flux.common.feature.createaccount

import io.appoutlet.flux.common.feature.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

@Suppress("RegExpRedundantEscape")
private val REGEX_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$".toRegex()
private const val MIN_PASSWORD_LENGTH = 6

@FlowPreview
class CreateAccountViewModel(
    private val createAccountOrchestrator: CreateAccountOrchestrator
) : BaseViewModel() {
    private val mutableUiState = MutableStateFlow<CreateAccountUiState>(CreateAccountUiState.Idle)
    val uiState = mutableUiState.asStateFlow()

    private val mutableName = MutableStateFlow(InputValue.EMPTY)
    val name = mutableName.asStateFlow()

    private val mutableEmail = MutableStateFlow(InputValue.EMPTY)
    val email = mutableEmail.asStateFlow()

    private val mutablePassword = MutableStateFlow(InputValue.EMPTY)
    val password = mutablePassword.asStateFlow()

    private val mutablePasswordConfirmation = MutableStateFlow(InputValue.EMPTY)
    val passwordConfirmation = mutablePasswordConfirmation.asStateFlow()

    fun onNameChange(name: String) {
        mutableName.value = InputValue(value = name, isValid = name.isNotBlank())
    }

    fun onEmailChange(email: String) {
        mutableEmail.value = InputValue(value = email, isValid = REGEX_EMAIL.matches(email))
    }

    fun onPasswordChange(password: String) {
        val passwordsAreEqual = password == passwordConfirmation.value.value
        val validPasses = passwordsAreEqual && passwordIsComplexEnough(password)

        mutablePassword.value = InputValue(
            value = password,
            isValid = validPasses
        )

        mutablePasswordConfirmation.value = mutablePasswordConfirmation.value.copy(
            isValid = validPasses,
        )
    }

    fun onPasswordConfirmationChange(passwordConfirmation: String) {
        val passwordsAreEqual = passwordConfirmation == password.value.value
        val validPasses = passwordsAreEqual && passwordIsComplexEnough(passwordConfirmation)

        mutablePasswordConfirmation.value = InputValue(
            value = passwordConfirmation,
            isValid = validPasses
        )

        mutablePassword.value = mutablePassword.value.copy(
            isValid = validPasses,
        )
    }

    private fun passwordIsComplexEnough(newPassword: String): Boolean {
        return newPassword.length > MIN_PASSWORD_LENGTH
    }

    fun submit() {
        createAccountOrchestrator.createAccount(
            name = name.value.value,
            email = email.value.value,
            password = password.value.value
        )
            .onStart { mutableUiState.value = CreateAccountUiState.Loading }
            .catch { mutableUiState.value = CreateAccountUiState.Error }
            .onEach { mutableUiState.value = CreateAccountUiState.Success }
            .launchIn(viewModelScope)
    }
}

data class InputValue(
    val value: String,
    val isValid: Boolean,
) {
    companion object {
        val EMPTY = InputValue(value = "", isValid = true)
    }
}

sealed class CreateAccountUiState {
    object Idle : CreateAccountUiState()
    object Loading : CreateAccountUiState()
    object Error : CreateAccountUiState()
    object Success : CreateAccountUiState()
}

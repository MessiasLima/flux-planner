package io.appoutlet.flux.common.feature.createaccount

import io.appoutlet.flux.common.feature.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Suppress("RegExpRedundantEscape")
private val REGEX_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$".toRegex()

class CreateAccountViewModel : BaseViewModel() {
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

        mutablePassword.value = InputValue(
            value = password,
            isValid = passwordsAreEqual
        )

        mutablePasswordConfirmation.value = mutablePasswordConfirmation.value.copy(
            isValid = passwordsAreEqual,
        )
    }

    fun onPasswordConfirmationChange(passwordConfirmation: String) {
        val passwordsAreEqual = passwordConfirmation == password.value.value

        mutablePasswordConfirmation.value = InputValue(
            value = passwordConfirmation,
            isValid = passwordsAreEqual
        )

        mutablePassword.value = mutablePassword.value.copy(
            isValid = passwordsAreEqual,
        )
    }

    fun submit() {
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

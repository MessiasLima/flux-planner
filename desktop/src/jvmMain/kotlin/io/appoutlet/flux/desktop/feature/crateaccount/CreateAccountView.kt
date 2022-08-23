package io.appoutlet.flux.desktop.feature.crateaccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import io.appoutlet.flux.common.core.ui.Spacing
import io.appoutlet.flux.common.feature.createaccount.CreateAccountUiState
import io.appoutlet.flux.common.feature.createaccount.CreateAccountViewModel
import io.appoutlet.flux.common.feature.createaccount.InputValue
import io.appoutlet.flux.desktop.common.AccountCircle
import io.appoutlet.flux.desktop.common.Flux
import io.appoutlet.flux.desktop.common.Key
import io.appoutlet.flux.desktop.common.Mail
import io.appoutlet.flux.desktop.common.components.DefaultTextField
import io.appoutlet.flux.desktop.common.components.FluxProgressBar
import io.appoutlet.flux.desktop.common.components.PasswordTextField
import io.appoutlet.flux.desktop.common.components.TextFieldErrorMessage
import io.appoutlet.flux.desktop.common.initialize
import io.appoutlet.karavel.Karavel
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun CreateAccountView(karavel: Karavel?, viewModel: CreateAccountViewModel, onSuccess: () -> Unit) {
    viewModel.initialize()
    val uiState by viewModel.uiState.collectAsState()
    val isLoading = uiState is CreateAccountUiState.Loading
    val isSuccess = uiState is CreateAccountUiState.Success

    if (isSuccess) {
        onSuccess()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FluxProgressBar(isLoading = isLoading)

        TopBar(onBackClicked = { karavel?.back() })

        CreateAccountContent(viewModel = viewModel, uiState = uiState)

        Spacer(modifier = Modifier.height(Spacing.Medium))
    }
}

private fun isReadyToSubmit(isLoading: Boolean, email: InputValue, password: InputValue): Boolean {
    return (!isLoading) &&
        email.value.isNotBlank() && email.isValid &&
        password.value.isNotBlank() && password.isValid
}

@Composable
private fun TopBar(onBackClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
        title = {
            Text(text = "Create account")
        },
    )
}

@ExperimentalComposeUiApi
@FlowPreview
@Composable
private fun CreateAccountContent(viewModel: CreateAccountViewModel, uiState: CreateAccountUiState) {
    val isLoading = uiState is CreateAccountUiState.Loading
    val isError = uiState is CreateAccountUiState.Error

    Column(
        modifier = Modifier.padding(horizontal = Spacing.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val name by viewModel.name.collectAsState()
        val email by viewModel.email.collectAsState()
        val password by viewModel.password.collectAsState()
        val passwordConfirmation by viewModel.passwordConfirmation.collectAsState()

        DefaultTextField(
            modifier = Modifier.fillMaxWidth().padding(top = Spacing.Small),
            value = name.value,
            onValueChange = { viewModel.onNameChange(it) },
            label = "Name",
            error = !name.isValid,
            enabled = !isLoading,
            leadingIcon = Icons.Flux.AccountCircle
        )
        TextFieldErrorMessage(show = !name.isValid, message = "Invalid name")

        DefaultTextField(
            modifier = Modifier.fillMaxWidth().padding(top = Spacing.VerySmall),
            value = email.value,
            onValueChange = { viewModel.onEmailChange(it) },
            label = "Email",
            error = !email.isValid,
            enabled = !isLoading,
            leadingIcon = Icons.Flux.Mail,
        )
        TextFieldErrorMessage(show = !email.isValid, message = "Invalid email")

        PasswordTextField(
            modifier = Modifier.fillMaxWidth().padding(top = Spacing.VerySmall),
            value = password.value,
            onValueChange = { viewModel.onPasswordChange(it) },
            enabled = !isLoading,
            error = !password.isValid,
            leadingIcon = Icons.Flux.Key
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth().padding(top = Spacing.Medium),
            value = passwordConfirmation.value,
            onValueChange = { viewModel.onPasswordConfirmationChange(it) },
            enabled = !isLoading,
            error = !passwordConfirmation.isValid,
            leadingIcon = Icons.Flux.Key
        )

        MainErrorMessage(passwordIsValid = passwordConfirmation.isValid, isError = isError)

        Spacer(modifier = Modifier.height(Spacing.Small))

        Button(
            enabled = isReadyToSubmit(isLoading, email, password),
            onClick = { viewModel.submit() },
        ) { Text("CREATE ACCOUNT") }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun MainErrorMessage(passwordIsValid: Boolean, isError: Boolean) {
    val errorMessage = if (passwordIsValid) {
        "Error when creating your account"
    } else {
        "The passwords are not equal"
    }
    TextFieldErrorMessage(
        show = (!passwordIsValid) || isError,
        message = errorMessage,
    )
}

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
import androidx.compose.ui.Modifier
import io.appoutlet.flux.common.core.ui.Spacing
import io.appoutlet.flux.common.feature.createaccount.CreateAccountViewModel
import io.appoutlet.flux.common.feature.createaccount.InputValue
import io.appoutlet.flux.desktop.common.AccountCircle
import io.appoutlet.flux.desktop.common.Flux
import io.appoutlet.flux.desktop.common.Key
import io.appoutlet.flux.desktop.common.Mail
import io.appoutlet.flux.desktop.common.components.DefaultTextField
import io.appoutlet.flux.desktop.common.components.PasswordTextField
import io.appoutlet.karavel.Karavel

@Composable
fun CreateAccountView(karavel: Karavel?, viewModel: CreateAccountViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar(onBackClicked = { karavel?.back() })

        val name by viewModel.name.collectAsState()
        val email by viewModel.email.collectAsState()
        val password by viewModel.password.collectAsState()
        val passwordConfirmation by viewModel.passwordConfirmation.collectAsState()

        DefaultTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
            value = name.value,
            onValueChange = { viewModel.onNameChange(it) },
            label = "Name",
            error = !name.isValid,
            enabled = true,
            leadingIcon = Icons.Flux.AccountCircle
        )

        DefaultTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
            value = email.value,
            onValueChange = { viewModel.onEmailChange(it) },
            label = "Email",
            error = !email.isValid,
            enabled = true,
            leadingIcon = Icons.Flux.Mail,
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
            value = password.value,
            onValueChange = { viewModel.onPasswordChange(it) },
            enabled = true,
            error = !password.isValid,
            leadingIcon = Icons.Flux.Key
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
            value = passwordConfirmation.value,
            onValueChange = { viewModel.onPasswordConfirmationChange(it) },
            enabled = true,
            error = !passwordConfirmation.isValid,
            leadingIcon = Icons.Flux.Key
        )

        Spacer(modifier = Modifier.height(Spacing.Large))

        Button(enabled = isReadyToSubmit(email, password), onClick = { viewModel.submit() }) {
            Text("CREATE ACCOUNT")
        }

        Spacer(modifier = Modifier.height(Spacing.Medium))
    }
}

private fun isReadyToSubmit(email: InputValue, password: InputValue): Boolean {
    return email.value.isNotBlank() && email.isValid &&
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

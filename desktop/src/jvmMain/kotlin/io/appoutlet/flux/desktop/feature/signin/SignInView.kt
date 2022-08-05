package io.appoutlet.flux.desktop.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.appoutlet.flux.common.feature.signin.SignInUiState
import io.appoutlet.flux.common.feature.signin.SignInViewModel
import io.appoutlet.flux.desktop.common.initialize
import io.appoutlet.flux.desktop.di.koin
import io.appoutlet.flux.desktop.feature.crateaccount.SignUpPage
import io.appoutlet.flux.desktop.feature.passwordrecovery.PasswordRecoveryPage
import io.appoutlet.flux.desktop.feature.signin.component.EmailTextField
import io.appoutlet.flux.desktop.feature.signin.component.PasswordTextField
import io.appoutlet.karavel.Karavel

@Composable
fun SignInView(karavel: Karavel?, viewModel: SignInViewModel = koin.get()) {
    viewModel.initialize()
    SignInForm(viewModel, karavel)
}

@Composable
fun SignInForm(viewModel: SignInViewModel, karavel: Karavel?) {
    Box {
        val uiState: SignInUiState by viewModel.uiState.collectAsState(initial = SignInUiState.Idle)
        val isLoading = uiState is SignInUiState.Loading

        if (isLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Image(
                modifier = Modifier.width(124.dp),
                painter = painterResource("image/icon/icon.png"),
                contentDescription = "Application icon"
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Welcome to Flux!",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(47.dp))

            EmailTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                onClearIconClick = { email = "" },
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(36.dp))

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(47.dp))

            Button(
                modifier = Modifier.width(159.dp),
                onClick = { viewModel.login(email, password) },
                enabled = !isLoading,
            ) { Text(text = "SIGN IN", style = MaterialTheme.typography.titleSmall) }

            if (uiState is SignInUiState.Error) {
                Text("Error")
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                enabled = !isLoading,
                onClick = { karavel?.navigate(SignUpPage()) },
            ) { Text("Create account") }

            TextButton(
                enabled = !isLoading,
                onClick = { karavel?.navigate(PasswordRecoveryPage()) },
            ) { Text("Forgot password") }
        }
    }
}

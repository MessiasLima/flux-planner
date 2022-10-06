package io.appoutlet.flux.desktop.feature.emailverification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.appoutlet.flux.common.core.ui.Spacing
import io.appoutlet.flux.common.feature.emailverification.EmailVerificationViewModel
import io.appoutlet.flux.desktop.common.components.FluxProgressBar
import io.appoutlet.flux.desktop.common.components.TextFieldErrorMessage
import io.appoutlet.flux.desktop.common.initialize
import io.appoutlet.flux.desktop.di.koin
import io.appoutlet.karavel.Page

@ExperimentalComposeUiApi
class EmailVerificationPage : Page() {
    @Composable
    override fun content() {
        val viewModel: EmailVerificationViewModel = koin.get()
        viewModel.initialize()

        val uiState by viewModel.uiState.collectAsState()
        val isLoading = uiState is EmailVerificationViewModel.UiState.ResendingEmail

        if (uiState is EmailVerificationViewModel.UiState.Logout) {
            karavel?.back()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "We couldn't verify your email", style = MaterialTheme.typography.bodyLarge)

            Text(
                text = "We sent you an verification email to be sure that you have a valid email address.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(Spacing.Medium))

            Button(
                onClick = { viewModel.resendVerificationEmail() },
                enabled = !isLoading,
            ) { Text(text = "Resend verification email", style = MaterialTheme.typography.titleSmall) }

            TextButton(
                enabled = !isLoading,
                onClick = { viewModel.logout() },
            ) { Text("Sign in with another account") }

            FluxProgressBar(modifier = Modifier.width(150.dp), isLoading = isLoading)

            TextFieldErrorMessage(
                show = uiState is EmailVerificationViewModel.UiState.EmailSent,
                message = "We just sent a new verification email. Please also check your spam box"
            )

            TextFieldErrorMessage(
                show = uiState is EmailVerificationViewModel.UiState.EmailFailure,
                message = "Something wrong happened while sending the email. Please try again"
            )
        }
    }
}

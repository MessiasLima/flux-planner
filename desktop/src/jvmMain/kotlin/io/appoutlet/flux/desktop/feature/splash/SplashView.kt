package io.appoutlet.flux.desktop.feature.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import io.appoutlet.flux.common.core.ui.Spacing
import io.appoutlet.flux.common.feature.splash.SplashUiState
import io.appoutlet.flux.common.feature.splash.SplashViewModel
import io.appoutlet.flux.desktop.common.FluxImages
import io.appoutlet.flux.desktop.common.components.FluxProgressBar
import io.appoutlet.flux.desktop.common.components.TextFieldErrorMessage
import io.appoutlet.flux.desktop.common.initialize
import io.appoutlet.flux.desktop.di.koin
import io.appoutlet.flux.desktop.feature.emailverification.EmailVerificationPage
import io.appoutlet.flux.desktop.feature.login.LoginPage
import io.appoutlet.flux.desktop.feature.notes.NotesPage
import io.appoutlet.karavel.Karavel
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@FlowPreview
@Composable
fun SplashView(viewModel: SplashViewModel = koin.get(), karavel: Karavel?) {
    viewModel.initialize()

    val uiState by viewModel.uiState.collectAsState(initial = SplashUiState.Loading)

    verifyUiStateAndNavigate(uiState = uiState, karavel = karavel)

    val isLoading = uiState == SplashUiState.Loading
    val isError = uiState == SplashUiState.Error

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = FluxImages.Logo,
                contentDescription = "Application icon",
                modifier = Modifier.size(128.dp),
            )

            Spacer(Modifier.height(Spacing.Medium))

            Text(text = "Flux planner", style = MaterialTheme.typography.headlineLarge)

            Spacer(Modifier.height(Spacing.Medium))

            FluxProgressBar(isLoading = isLoading)

            ErrorMessage(isError, onLogoutClicked = { viewModel.logOut()}, onTryAgainClicked = {
                viewModel.tryAgain()
            })
        }
    }
}

@FlowPreview
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
fun verifyUiStateAndNavigate(uiState: SplashUiState, karavel: Karavel?) {
    when (uiState) {
        SplashUiState.Logged -> karavel?.navigate(NotesPage())
        SplashUiState.NotLogged -> karavel?.navigate(LoginPage())
        SplashUiState.EmailNotVerified -> karavel?.navigate(EmailVerificationPage())
        else -> {
            /*no-op*/
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun ErrorMessage(onError: Boolean, onLogoutClicked: () -> Unit, onTryAgainClicked: () -> Unit) {
    Spacer(modifier = Modifier.height(Spacing.Large))

    TextFieldErrorMessage(
        show = onError,
        message = "Occurred an error when verifying the logged user"
    )

    val targetAlpha = if (onError) 1f else 0f
    val buttonsAlpha by animateFloatAsState(targetAlpha)

    Spacer(modifier = Modifier.height(Spacing.Medium))

    Button(modifier = Modifier.alpha(buttonsAlpha), onClick = onTryAgainClicked) {
        Text("Try again")
    }

    TextButton(modifier = Modifier.alpha(buttonsAlpha), onClick = onLogoutClicked) {
        Text("Login with another user")
    }
}

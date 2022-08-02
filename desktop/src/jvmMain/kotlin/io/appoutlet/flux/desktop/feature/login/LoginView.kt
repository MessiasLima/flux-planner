package io.appoutlet.flux.desktop.feature.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.appoutlet.flux.common.core.ui.shapeMedium

@ExperimentalFoundationApi
@Composable
fun LoginView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.width(432.dp),
                shape = shapeMedium,
                elevation = 6.dp
            ) {
                var loginPage by remember { mutableStateOf(LoginViewPage.SIGN_IN) }

                val onNavigate = { newPage: LoginViewPage -> loginPage = newPage }

                when (loginPage) {
                    LoginViewPage.SIGN_IN -> SignInView(onNavigate)
                    LoginViewPage.SIGN_UP -> SignUpView(onNavigate)
                    LoginViewPage.FORGOT_PASSWORD -> ForgotPasswordView(onNavigate)
                }
            }
        }
    }
}

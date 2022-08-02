package io.appoutlet.flux.desktop.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.appoutlet.flux.desktop.feature.login.component.EmailTextField
import io.appoutlet.flux.desktop.feature.login.component.PasswordTextField

@Composable
fun SignInView(onNavigate: (LoginViewPage) -> Unit) {
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
        )

        Spacer(modifier = Modifier.height(36.dp))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
        )

        Spacer(modifier = Modifier.height(47.dp))

        Button(modifier = Modifier.width(159.dp), onClick = {}) {
            Text(text = "SIGN IN", style = MaterialTheme.typography.titleSmall)
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = {
            onNavigate(LoginViewPage.SIGN_UP)
        }) {
            Text("Create account")
        }

        TextButton(onClick = {
            onNavigate(LoginViewPage.FORGOT_PASSWORD)
        }) {
            Text("Forgot password")
        }
    }
}

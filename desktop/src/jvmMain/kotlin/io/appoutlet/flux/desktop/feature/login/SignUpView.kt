package io.appoutlet.flux.desktop.feature.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SignUpView(onNavigate: (LoginViewPage) -> Unit) {
    Text("Sign up")
    Button(onClick = {
        onNavigate(LoginViewPage.SIGN_IN)
    }) {
        Text("Back")
    }
}

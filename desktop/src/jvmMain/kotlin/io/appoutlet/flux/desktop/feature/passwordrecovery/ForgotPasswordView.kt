package io.appoutlet.flux.desktop.feature.passwordrecovery

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ForgotPasswordView(onNavigate: () -> Unit) {
    Text("Forgot password")
    Button(onClick = {
        onNavigate()
    }) {
        Text("Back")
    }
}

package io.appoutlet.flux.common.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun LoginView(viewModel: LoginViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var login by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        TextField(login, onValueChange = { login = it })
        TextField(password, onValueChange = { password = it })

        Button(onClick = {
            viewModel.login(login, password)
        }) {
            Text("Login")
        }
    }
}

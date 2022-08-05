package io.appoutlet.flux.desktop.feature.crateaccount

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.appoutlet.karavel.Karavel

@Composable
fun SignUpView(karavel: Karavel?) {
    Text("Sign up")
    Button(onClick = {
        karavel?.back()
    }) {
        Text("Back")
    }
}

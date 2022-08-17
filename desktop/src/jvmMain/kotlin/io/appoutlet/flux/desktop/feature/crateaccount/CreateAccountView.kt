package io.appoutlet.flux.desktop.feature.crateaccount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.appoutlet.flux.common.core.ui.Spacing
import io.appoutlet.flux.desktop.common.components.DefaultTextField
import io.appoutlet.karavel.Karavel

@Composable
fun CreateAccountView(karavel: Karavel?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar { karavel?.back() }

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordConfirmation by remember { mutableStateOf("") }

        DefaultTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
            value = name,
            onValueChange = { name = it },
            label = "Name",
            error = false,
            enabled = true
        )

        DefaultTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Spacing.Medium, vertical = Spacing.Small),
            value = name,
            onValueChange = { name = it },
            error = false,
            enabled = true
        )
    }
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

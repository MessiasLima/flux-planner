package io.appoutlet.flux.desktop.feature.login.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.mouseClickable
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    var showPassword by remember { mutableStateOf(false) }
    val icon = if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
    val contentDescription = if (showPassword) "Hide password" else "Show password"
    val visualTransformation = if (showPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = "Password")
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable { showPassword = !showPassword },
                imageVector = icon,
                contentDescription = contentDescription,
            )
        },
        visualTransformation = visualTransformation,
        singleLine = true
    )
}

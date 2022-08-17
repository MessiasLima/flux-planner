package io.appoutlet.flux.desktop.common.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import io.appoutlet.flux.desktop.common.Flux
import io.appoutlet.flux.desktop.common.Visibility
import io.appoutlet.flux.desktop.common.VisibilityOff

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    error: Boolean,
    leadingIcon: Painter? = null,
) {
    var showPassword by remember { mutableStateOf(false) }
    val icon = if (showPassword) Icons.Flux.VisibilityOff else Icons.Flux.Visibility
    val iconTint = if (error) MaterialTheme.colorScheme.error else LocalContentColor.current
    val contentDescription = if (showPassword) "Hide password" else "Show password"
    val visualTransformation = if (showPassword) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = "Password")
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = it,
                    contentDescription = "Leading icon",
                    tint = iconTint
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    painter = icon,
                    contentDescription = contentDescription,
                    tint = iconTint
                )
            }
        },
        visualTransformation = visualTransformation,
        singleLine = true,
        enabled = enabled,
        isError = error,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary
        )
    )
}

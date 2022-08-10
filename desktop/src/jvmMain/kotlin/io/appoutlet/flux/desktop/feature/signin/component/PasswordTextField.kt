package io.appoutlet.flux.desktop.feature.signin.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import io.appoutlet.flux.common.core.ui.error40
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
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    enabled = true,
                    onClick = { showPassword = !showPassword },
                ),
                painter = icon,
                contentDescription = contentDescription,
                tint = iconTint
            )
        },
        visualTransformation = visualTransformation,
        singleLine = true,
        enabled = enabled,
        isError = error,
    )
}

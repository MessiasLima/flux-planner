package io.appoutlet.flux.desktop.common.components

import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.appoutlet.flux.desktop.common.Cancel
import io.appoutlet.flux.desktop.common.Flux

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String? = null,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    error: Boolean,
) {
    val iconTint = if (error) MaterialTheme.colorScheme.error else LocalContentColor.current

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = {
            label?.let { Text(text = it) }
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        trailingIcon = {
            if (value.isNotBlank()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        painter = Icons.Flux.Cancel,
                        contentDescription = "Clear",
                        tint = iconTint
                    )
                }
            }
        },
        singleLine = true,
        enabled = enabled,
        isError = error,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary
        )
    )
}

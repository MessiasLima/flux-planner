package io.appoutlet.flux.desktop.feature.login.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import io.appoutlet.flux.desktop.common.Cancel
import io.appoutlet.flux.desktop.common.Flux

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearIconClick: () -> Unit
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = "Email")
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        trailingIcon = {
            if (value.isNotBlank()) {
                Icon(
                    modifier = Modifier.clickable(onClick = onClearIconClick, role = Role.Button),
                    painter = Icons.Flux.Cancel,
                    contentDescription = "Clear",
                )
            }
        },
        singleLine = true,
    )
}

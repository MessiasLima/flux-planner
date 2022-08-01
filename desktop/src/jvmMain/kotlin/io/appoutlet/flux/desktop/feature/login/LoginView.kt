package io.appoutlet.flux.desktop.feature.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.mouseClickable
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.appoutlet.flux.common.core.ui.purple95

@ExperimentalFoundationApi
@Composable
fun LoginView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = purple95
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.width(432.dp),
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var email by remember { mutableStateOf("") }

                    Image(
                        modifier = Modifier.width(124.dp),
                        painter = painterResource("image/icon/icon.png"),
                        contentDescription = "Application icon"
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(text = "Welcome to flux", style = MaterialTheme.typography.h4)

                    Spacer(modifier = Modifier.height(47.dp))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(text = "Email", style = MaterialTheme.typography.caption)
                        },
                        textStyle = MaterialTheme.typography.body1,
                        trailingIcon = {
                            if (email.isNotBlank()) {
                                Icon(
                                    modifier = Modifier.mouseClickable {
                                        email = ""
                                    },
                                    imageVector = Icons.Outlined.Cancel,
                                    contentDescription = "Clear"
                                )
                            }
                        },
                    )
                }
            }
        }
    }
}

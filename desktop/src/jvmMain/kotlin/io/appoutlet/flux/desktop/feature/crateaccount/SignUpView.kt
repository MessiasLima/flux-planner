package io.appoutlet.flux.desktop.feature.crateaccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.appoutlet.flux.common.core.ui.Spacing
import io.appoutlet.flux.desktop.common.FluxImages
import io.appoutlet.karavel.Karavel

@Composable
fun CreateAccountView(karavel: Karavel?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.padding(Spacing.Medium),
            painter = FluxImages.Logo,
            contentDescription = "Application logo"
        )

        Text("Create account")

        Button(onClick = {
            karavel?.back()
        }) {
            Text("Back")
        }
    }

}

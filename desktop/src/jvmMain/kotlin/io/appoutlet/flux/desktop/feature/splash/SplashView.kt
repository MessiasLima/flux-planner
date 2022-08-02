package io.appoutlet.flux.desktop.feature.splash

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashView(onSuccess: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LaunchedEffect(Unit) {
                // TODO: Verify if user is logged
                delay(1000)
                onSuccess()
            }

            Image(
                painter = painterResource("image/icon/icon.png"),
                contentDescription = "Application icon",
                modifier = Modifier.size(128.dp),
            )

            Spacer(Modifier.height(24.dp))

            Text(text = "Flux planner", style = MaterialTheme.typography.headlineLarge)

            Spacer(Modifier.height(24.dp))

            CircularProgressIndicator()
        }
    }
}

package io.appoutlet.flux.desktop.feature.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.appoutlet.flux.common.core.ui.shapeMedium
import io.appoutlet.flux.desktop.feature.signin.SignInPage
import io.appoutlet.karavel.Karavel

@ExperimentalFoundationApi
@Composable
fun LoginView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val loginKaravel = Karavel(SignInPage())
            Card(
                modifier = Modifier.width(432.dp),
                shape = shapeMedium,
                elevation = 6.dp,
                backgroundColor = MaterialTheme.colorScheme.surface
            ) {
                loginKaravel.currentPage().content()
            }
        }
    }
}

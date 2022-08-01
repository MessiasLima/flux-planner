@file:JvmName("Flux")

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.appoutlet.flux.common.core.ui.FluxTheme
import io.appoutlet.flux.desktop.feature.splash.SplashPage
import io.appoutlet.karavel.Karavel
import java.awt.Dimension

fun main() {
    application {

        val karavel = Karavel(SplashPage())

        Window(
            onCloseRequest = ::exitApplication,
            title = "Flux planner",
            icon = painterResource("image/icon/icon.png"),
        ) {
            window.minimumSize = Dimension(800, 600)

            FluxTheme {
                karavel.currentPage().content()
            }
        }
    }
}

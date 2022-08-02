@file:JvmName("Flux")

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.appoutlet.flux.common.core.ui.FluxTheme
import io.appoutlet.flux.desktop.di.initKoin
import io.appoutlet.flux.desktop.feature.splash.SplashPage
import io.appoutlet.karavel.Karavel
import java.awt.Dimension

private const val WINDOW_MIN_WIDTH = 800
private const val WINDOW_MIN_HEIGHT = 700

@ExperimentalFoundationApi
fun main() {
    initKoin()
    application {
        val karavel = Karavel(SplashPage())

        Window(
            onCloseRequest = ::exitApplication,
            title = "Flux planner",
            icon = painterResource("image/icon/icon.png"),
        ) {
            window.minimumSize = Dimension(WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT)

            FluxTheme {
                karavel.currentPage().content()
            }
        }
    }
}

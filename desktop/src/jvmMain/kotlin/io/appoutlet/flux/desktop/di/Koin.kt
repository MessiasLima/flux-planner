package io.appoutlet.flux.desktop.di

import io.appoutlet.flux.common.commonModules
import io.appoutlet.flux.desktop.feature.featureModules
import org.koin.core.Koin
import org.koin.core.context.startKoin

lateinit var koin: Koin

fun initKoin() {
    val koinApplication = startKoin {
        printLogger()
        modules(
            *featureModules,
            *commonModules
        )
    }

    koin = koinApplication.koin
}

package io.appoutlet.flux.desktop.di

import io.appoutlet.flux.common.commonModules
import io.appoutlet.flux.desktop.feature.featureModules
import kotlinx.coroutines.FlowPreview
import org.koin.core.Koin
import org.koin.core.context.startKoin

lateinit var koin: Koin

@FlowPreview
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

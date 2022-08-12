package io.appoutlet.flux.common.core.network

import io.appoutlet.flux.common.core.network.authentication.AuthenticationApi
import org.koin.dsl.module

internal val networkModule = module {
    single {
        createHttpClient()
    }

    factory { AuthenticationApi(get()) }
}

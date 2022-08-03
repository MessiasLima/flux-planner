package io.appoutlet.flux.common.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

actual fun createHttpClient() = HttpClient(Android) {
    install(ContentNegotiation) {
        json()
    }
}

package io.appoutlet.flux.common.core.network

import io.ktor.client.HttpClient

expect fun createHttpClient(): HttpClient

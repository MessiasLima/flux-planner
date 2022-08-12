package io.appoutlet.flux.common.core.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

suspend inline fun <reified T> HttpResponse.getResult(): T {
    return if (status.isSuccess()) {
        body()
    } else {
        throw FluxNetworkException(body())
    }
}

class FluxNetworkException(val errorBody: FluxNetworkErrorBody) : Throwable()

@Serializable
data class FluxNetworkErrorBody(
    val error: NetworkError
)

@Serializable
data class NetworkError(
    val code: Int,
    val status: String? = null,
    val message: String? = null
)

package io.appoutlet.flux.common.core.network

import io.appoutlet.flux.common.test.UnitTest
import io.appoutlet.flux.common.test.api.ApiTest
import io.appoutlet.flux.common.test.api.ApiTestImpl
import io.appoutlet.flux.common.test.api.respond
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class NetworkErrorBodyKtTest : UnitTest<Unit>(), ApiTest by ApiTestImpl() {
    override fun buildSut() = Unit

    @Test
    fun `should return body`() = runTest {
        val fixtUrl: String = "http://" + fixture()
        val fixtSampleResponse: SampleResponse = fixture()

        setResponseResolver(fixtUrl) {
            respond(content = fixtSampleResponse)
        }

        val actual = mockHttpClient.get(fixtUrl).getResult<SampleResponse>()

        assertEquals(actual, fixtSampleResponse)
    }

    @Test(expected = FluxNetworkException::class)
    fun `should throw exception`() = runTest {
        val fixtUrl: String = "http://" + fixture()
        val fixtFluxNetworkErrorBody: FluxNetworkErrorBody = fixture()

        setResponseResolver(fixtUrl) {
            respond(content = fixtFluxNetworkErrorBody, status = HttpStatusCode.InternalServerError)
        }

        mockHttpClient.get(fixtUrl).getResult<String>()
    }
}

@Serializable
data class SampleResponse(val nameValue: String)

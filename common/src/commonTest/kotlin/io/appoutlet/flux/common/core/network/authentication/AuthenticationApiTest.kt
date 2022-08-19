package io.appoutlet.flux.common.core.network.authentication

import io.appoutlet.flux.common.core.network.common.Accounts
import io.appoutlet.flux.common.core.network.common.Route
import io.appoutlet.flux.common.test.UnitTest
import io.appoutlet.flux.common.test.api.ApiTest
import io.appoutlet.flux.common.test.api.ApiTestImpl
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AuthenticationApiTest : UnitTest<AuthenticationApi>(), ApiTest by ApiTestImpl() {
    override fun buildSut() = AuthenticationApi(mockHttpClient)

    @Test
    fun `should sign in with password`() = runTest {
        val fixtAuthenticationRequest: AuthenticationRequest = fixture()
        val fixtAuthenticationResponse: AuthenticationResponse = fixture()

        setResponseResolver(Route.Accounts.signInWithPassword() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(
                content = Json.encodeToString(fixtAuthenticationResponse),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val actual = sut.signInWithPassword(fixtAuthenticationRequest)

        assertEquals(fixtAuthenticationResponse, actual)
    }
}

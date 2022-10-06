package io.appoutlet.flux.common.core.network.authentication

import io.appoutlet.flux.common.core.network.common.Accounts
import io.appoutlet.flux.common.core.network.common.Route
import io.appoutlet.flux.common.test.UnitTest
import io.appoutlet.flux.common.test.api.ApiTest
import io.appoutlet.flux.common.test.api.ApiTestImpl
import io.appoutlet.flux.common.test.api.respond
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AuthenticationApiTest : UnitTest<AuthenticationApi>(), ApiTest by ApiTestImpl() {
    override fun buildSut() = AuthenticationApi(mockHttpClient)

    @Test
    fun `should sign in with password`() = runTest {
        val fixtAuthenticationRequest: AuthenticationRequest = fixture()
        val fixtAuthenticationResponse: AuthenticationResponse = fixture()

        addResponseResolver(Route.Accounts.signInWithPassword() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(content = fixtAuthenticationResponse)
        }

        val actual = sut.signInWithPassword(fixtAuthenticationRequest)

        assertEquals(fixtAuthenticationResponse, actual)
    }

    @Test
    fun `should sign up with email`() = runTest {
        val fixtSignUpWithEmailRequest: SignUpWithEmailRequest = fixture()
        val fixtSignUpWithEmailResponse: SignUpWithEmailResponse = fixture()

        addResponseResolver(Route.Accounts.signUp() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(content = fixtSignUpWithEmailResponse)
        }

        val actual = sut.signUpWithEmail(fixtSignUpWithEmailRequest)

        assertEquals(actual, fixtSignUpWithEmailResponse)
    }

    @Test
    fun `should update profile`() = runTest {
        val fixtUpdateProfileRequest: UpdateProfileRequest = fixture()
        val fixtUpdateProfileResponse: UpdateProfileResponse = fixture()

        addResponseResolver(Route.Accounts.updateProfile() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(content = fixtUpdateProfileResponse)
        }

        val actual = sut.updateProfile(fixtUpdateProfileRequest)

        assertEquals(actual, fixtUpdateProfileResponse)
    }

    @Test
    fun `should send email confirmation`() = runTest {
        val fixtSendEmailConfirmationRequest: SendEmailConfirmationRequest = fixture()
        val fixtSendEmailConfirmationResponse: SendEmailConfirmationResponse = fixture()

        addResponseResolver(Route.Accounts.sendEmailConfirmation() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(content = fixtSendEmailConfirmationResponse)
        }

        val actual = sut.sendEmailConfirmation(fixtSendEmailConfirmationRequest)

        assertEquals(fixtSendEmailConfirmationResponse, actual)
    }

    @Test
    fun `should look up for an account`() = runTest {
        val fixtLookUpRequest: LookUpRequest = fixture()
        val fixtLookUpResponse: LookUpResponse = fixture()

        addResponseResolver(url = Route.Accounts.lookUp() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(content = fixtLookUpResponse)
        }

        val actual = sut.lookUp(lookUpRequest = fixtLookUpRequest)

        assertEquals(fixtLookUpResponse, actual)
    }
}

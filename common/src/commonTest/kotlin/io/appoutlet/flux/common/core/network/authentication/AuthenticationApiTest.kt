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
        val mockSignUpWithEmailRequest: SignUpWithEmailRequest = fixture()
        val mockSignUpWithEmailResponse: SignUpWithEmailResponse = fixture()

        addResponseResolver(Route.Accounts.signUp() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(content = mockSignUpWithEmailResponse)
        }

        val actual = sut.signUpWithEmail(mockSignUpWithEmailRequest)

        assertEquals(actual, mockSignUpWithEmailResponse)
    }

    @Test
    fun `should update profile`() = runTest {
        val mockUpdateProfileRequest: UpdateProfileRequest = fixture()
        val mockUpdateProfileResponse: UpdateProfileResponse = fixture()

        addResponseResolver(Route.Accounts.updateProfile() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(content = mockUpdateProfileResponse)
        }

        val actual = sut.updateProfile(mockUpdateProfileRequest)

        assertEquals(actual, mockUpdateProfileResponse)
    }

    @Test
    fun `should send email confirmation`() = runTest {
        val mockSendEmailConfirmationRequest: SendEmailConfirmationRequest = fixture()
        val mockSendEmailConfirmationResponse: SendEmailConfirmationResponse = fixture()

        addResponseResolver(Route.Accounts.sendEmailConfirmation() + "?key=" + AuthenticationApi.SIGN_IN_API_KEY) {
            respond(content = mockSendEmailConfirmationResponse)
        }

        val actual = sut.sendEmailConfirmation(mockSendEmailConfirmationRequest)

        assertEquals(actual, mockSendEmailConfirmationResponse)
    }
}

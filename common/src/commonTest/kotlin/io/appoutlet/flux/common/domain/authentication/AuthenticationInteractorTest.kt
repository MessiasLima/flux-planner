package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.AuthenticationApi
import io.appoutlet.flux.common.core.network.authentication.AuthenticationRequest
import io.appoutlet.flux.common.core.network.authentication.AuthenticationResponse
import io.appoutlet.flux.common.core.network.authentication.LookUpRequest
import io.appoutlet.flux.common.core.network.authentication.LookUpResponse
import io.appoutlet.flux.common.core.network.authentication.SendEmailConfirmationRequest
import io.appoutlet.flux.common.core.network.authentication.SendEmailConfirmationResponse
import io.appoutlet.flux.common.core.network.authentication.SignUpWithEmailRequest
import io.appoutlet.flux.common.core.network.authentication.SignUpWithEmailResponse
import io.appoutlet.flux.common.core.network.authentication.UpdateProfileRequest
import io.appoutlet.flux.common.core.network.authentication.UpdateProfileResponse
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserDomainMapper
import io.appoutlet.flux.common.test.UnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AuthenticationInteractorTest : UnitTest<AuthenticationInteractor>() {
    private val mockAuthenticationApi: AuthenticationApi = mockk()
    private val mockAuthenticationRequestMapper: AuthenticationRequestMapper = mockk()
    private val mockUserDomainMapper: UserDomainMapper = mockk()
    private val mockUpdateProfileRequestMapper: UpdateProfileRequestMapper = mockk()
    private val mockSendEmailConfirmationRequestMapper: SendEmailConfirmationRequestMapper = mockk()
    private val mockSignUpWithEmailRequestMapper: SignUpWithEmailRequestMapper = mockk()
    private val mockLookUpRequestMapper: LookUpRequestMapper = mockk()

    override fun buildSut() = AuthenticationInteractor(
        authenticationApi = mockAuthenticationApi,
        authenticationRequestMapper = mockAuthenticationRequestMapper,
        userDomainMapper = mockUserDomainMapper,
        updateProfileRequestMapper = mockUpdateProfileRequestMapper,
        sendEmailConfirmationRequestMapper = mockSendEmailConfirmationRequestMapper,
        sighUpWithEmailRequestMapper = mockSignUpWithEmailRequestMapper,
        lookUpRequestMapper = mockLookUpRequestMapper
    )

    @Test
    fun `should authenticate`() = runTest {
        val fixtEmail: String = fixture()
        val fixtPassword: String = fixture()
        val fixtAuthenticationRequest: AuthenticationRequest = fixture()
        val fixtAuthenticationResponse: AuthenticationResponse = fixture()
        val fixtUserDomain: UserDomain = fixture()

        every {
            mockAuthenticationRequestMapper.map(
                email = fixtEmail,
                password = fixtPassword
            )
        } returns fixtAuthenticationRequest

        coEvery {
            mockAuthenticationApi.signInWithPassword(authenticationRequest = fixtAuthenticationRequest)
        } returns fixtAuthenticationResponse

        every { mockUserDomainMapper.map(fixtAuthenticationResponse) } returns fixtUserDomain

        val actual = sut.signIn(
            email = fixtEmail,
            password = fixtPassword
        ).first()

        assertEquals(fixtUserDomain, actual)
    }

    @Test
    fun `should create user`() = runTest {
        val fixtEmail: String = fixture()
        val fixtPassword: String = fixture()
        val fixtSignUpWithEmailRequest: SignUpWithEmailRequest = fixture()
        val fixtSignUpWithEmailResponse: SignUpWithEmailResponse = fixture()
        val fixtUserDomain: UserDomain = fixture()

        every {
            mockSignUpWithEmailRequestMapper.map(
                email = fixtEmail,
                password = fixtPassword
            )
        } returns fixtSignUpWithEmailRequest

        coEvery {
            mockAuthenticationApi.signUpWithEmail(fixtSignUpWithEmailRequest)
        } returns fixtSignUpWithEmailResponse

        every { mockUserDomainMapper.map(fixtSignUpWithEmailResponse) } returns fixtUserDomain

        val actual = sut.createUser(email = fixtEmail, password = fixtPassword).first()

        assertEquals(actual, fixtUserDomain)
    }

    @Test
    fun `should update display name`() = runTest {
        val fixtUserDomain: UserDomain = fixture()
        val fixtDisplayName: String = fixture()
        val fixtUpdateProfileRequest: UpdateProfileRequest = fixture()
        val fixtUpdateProfileResponse: UpdateProfileResponse = fixture()

        every {
            mockUpdateProfileRequestMapper.map(
                user = fixtUserDomain,
                displayName = fixtDisplayName
            )
        } returns fixtUpdateProfileRequest

        coEvery { mockAuthenticationApi.updateProfile(fixtUpdateProfileRequest) } returns fixtUpdateProfileResponse

        sut.updateDisplayName(user = fixtUserDomain, name = fixtDisplayName)

        coVerify(exactly = 1) { mockAuthenticationApi.updateProfile(fixtUpdateProfileRequest) }
    }

    @Test
    fun `should email confirmation`() = runTest {
        val fixtUserDomain: UserDomain = fixture()
        val fixtSendEmailConfirmationRequest: SendEmailConfirmationRequest = fixture()
        val fixtSendEmailConfirmationResponse: SendEmailConfirmationResponse = fixture()

        every {
            mockSendEmailConfirmationRequestMapper.map(fixtUserDomain)
        } returns fixtSendEmailConfirmationRequest

        coEvery {
            mockAuthenticationApi.sendEmailConfirmation(fixtSendEmailConfirmationRequest)
        } returns fixtSendEmailConfirmationResponse

        sut.sendEmailConfirmation(fixtUserDomain)

        coVerify(exactly = 1) {
            mockAuthenticationApi.sendEmailConfirmation(
                fixtSendEmailConfirmationRequest
            )
        }
    }

    @Test
    fun `should look up for an account`() = runTest {
        val fixtUserDomain: UserDomain = fixture()
        val fixtLookUpRequest: LookUpRequest = fixture()
        val fixtLookUpResponse: LookUpResponse = fixture()

        every { mockLookUpRequestMapper.map(fixtUserDomain) } returns fixtLookUpRequest
        coEvery { mockAuthenticationApi.lookUp(fixtLookUpRequest) } returns fixtLookUpResponse
        every {
            mockUserDomainMapper.map(
                userDomain = fixtUserDomain,
                lookUpResponse = fixtLookUpResponse
            )
        } returns fixtUserDomain

        val actual = sut.lookUp(user = fixtUserDomain)

        assertEquals(fixtUserDomain, actual)
    }
}

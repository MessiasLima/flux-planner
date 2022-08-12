package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.AuthenticationApi
import io.appoutlet.flux.common.core.network.authentication.AuthenticationRequest
import io.appoutlet.flux.common.core.network.authentication.AuthenticationResponse
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserDomainMapper
import io.appoutlet.flux.common.test.UnitTest
import io.mockk.coEvery
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

    override fun buildSut() = AuthenticationInteractor(
        authenticationApi = mockAuthenticationApi,
        authenticationRequestMapper = mockAuthenticationRequestMapper,
        userDomainMapper = mockUserDomainMapper,
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
            mockAuthenticationApi.authenticate(authenticationRequest = fixtAuthenticationRequest)
        } returns fixtAuthenticationResponse

        every { mockUserDomainMapper.map(fixtAuthenticationResponse) } returns fixtUserDomain

        val actual = sut.signIn(
            email = fixtEmail,
            password = fixtPassword
        ).first()

        assertEquals(fixtUserDomain, actual)
    }
}

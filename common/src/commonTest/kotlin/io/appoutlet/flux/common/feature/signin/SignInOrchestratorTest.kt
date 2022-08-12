package io.appoutlet.flux.common.feature.signin

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserInteractor
import io.appoutlet.flux.common.test.UnitTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class SignInOrchestratorTest : UnitTest<SignInOrchestrator>() {
    private val mockAuthenticationInteractor: AuthenticationInteractor = mockk()
    private val mockUserInteractor: UserInteractor = mockk()

    override fun buildSut() = SignInOrchestrator(
        authenticationInteractor = mockAuthenticationInteractor,
        userInteractor = mockUserInteractor
    )

    @Test
    fun `should perform sign in`() = runTest {
        val fixtEmail: String = fixture()
        val fixtPassword: String = fixture()
        val fixtUserDomain: UserDomain = fixture()

        every {
            mockAuthenticationInteractor.signIn(fixtEmail, fixtPassword)
        } returns flowOf(fixtUserDomain)
        every { mockUserInteractor.save(fixtUserDomain) } returns fixtUserDomain

        val actual = sut.signIn(fixtEmail, fixtPassword).first()

        assertEquals(fixtUserDomain, actual)
    }
}

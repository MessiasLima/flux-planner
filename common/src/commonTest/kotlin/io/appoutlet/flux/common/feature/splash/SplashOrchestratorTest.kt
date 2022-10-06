package io.appoutlet.flux.common.feature.splash

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserInteractor
import io.appoutlet.flux.common.feature.splash.exception.UserNotLoggedException
import io.appoutlet.flux.common.test.UnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
internal class SplashOrchestratorTest : UnitTest<SplashOrchestrator>() {
    private val mockUserInteractor: UserInteractor = mockk()
    private val mockAuthenticationInteractor: AuthenticationInteractor = mockk()

    override fun buildSut() = SplashOrchestrator(
        userInteractor = mockUserInteractor,
        authenticationInteractor = mockAuthenticationInteractor,
    )

    @Test
    fun `should verify logged user`() = runTest {
        val fixtUserDomain: UserDomain = fixture()
        val fixtUserDomainList = listOf(fixtUserDomain)

        every { mockUserInteractor.findAll() } returns fixtUserDomainList
        coEvery { mockAuthenticationInteractor.lookUp(fixtUserDomain) } returns fixtUserDomain

        val actual = sut.verifyLoggedUser().first()

        assertEquals(expected = fixtUserDomain, actual = actual)
    }

    @Test(UserNotLoggedException::class)
    fun `should throw exception if there is no logged user`() = runTest {
        every { mockUserInteractor.findAll() } returns emptyList()

        sut.verifyLoggedUser().first()
    }

    @Test
    fun `should throw exception if there is more than one logged user`() = runTest {
        val fixtUserDomainList: List<UserDomain> = fixture()

        every { mockUserInteractor.findAll() } returns fixtUserDomainList
        every { mockUserInteractor.deleteAll() } returns Unit

        assertFailsWith<UserNotLoggedException> {
            sut.verifyLoggedUser().first()
        }

        verify { mockUserInteractor.deleteAll() }
    }

    @Test
    fun `should throw exception if could not look up for the logged user`() = runTest {
        val fixtUserDomain: UserDomain = fixture()
        val fixtUserDomainList = listOf(fixtUserDomain)

        coEvery { mockAuthenticationInteractor.lookUp(fixtUserDomain) } throws UserNotLoggedException()
        every { mockUserInteractor.findAll() } returns fixtUserDomainList
        every { mockUserInteractor.deleteAll() } returns Unit

        assertFailsWith<UserNotLoggedException> {
            sut.verifyLoggedUser().first()
        }

        verify { mockUserInteractor.deleteAll() }
    }
}

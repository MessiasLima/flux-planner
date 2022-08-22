package io.appoutlet.flux.common.feature.createaccount

import io.appoutlet.flux.common.domain.authentication.AuthenticationInteractor
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.domain.user.UserInteractor
import io.appoutlet.flux.common.test.UnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@FlowPreview
internal class CreateAccountOrchestratorTest : UnitTest<CreateAccountOrchestrator>() {
    private val mockAuthenticationInteractor: AuthenticationInteractor = mockk()
    private val mockUserInteractor: UserInteractor = mockk()

    override fun buildSut() = CreateAccountOrchestrator(
        authenticationInteractor = mockAuthenticationInteractor,
        userInteractor = mockUserInteractor,
    )

    @Test
    fun `should create account`() = runTest {
        val fixtName: String = fixture()
        val fixtEmail: String = fixture()
        val fixtPassword: String = fixture()
        val fixtUserDomain: UserDomain = fixture()

        every {
            mockAuthenticationInteractor.createUser(
                email = fixtEmail,
                password = fixtPassword
            )
        } returns flowOf(fixtUserDomain)

        coEvery {
            mockAuthenticationInteractor.updateDisplayName(
                user = fixtUserDomain,
                name = fixtName
            )
        } returns Unit

        coEvery {
            mockAuthenticationInteractor.sendEmailConfirmation(user = fixtUserDomain)
        } returns Unit

        every {
            mockAuthenticationInteractor.signIn(
                email = fixtEmail,
                password = fixtPassword
            )
        } returns flowOf(fixtUserDomain)

        every { mockUserInteractor.save(user = fixtUserDomain) } returns fixtUserDomain

        val actual = sut.createAccount(
            name = fixtName,
            email = fixtEmail,
            password = fixtPassword,
        ).first()

        assertEquals(fixtUserDomain, actual)
    }
}

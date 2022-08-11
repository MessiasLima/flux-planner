package io.appoutlet.flux.common.feature.signin

import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.test.UnitTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class SignInViewModelTest : UnitTest<SignInViewModel>() {
    private val mockSignInOrchestrator: SignInOrchestrator = mockk()

    override fun buildSut() = SignInViewModel(mockSignInOrchestrator)

    @Test
    fun `should emit success when complete`() = runTest {
        sut.init(this)

        val fixtEmail: String = fixture()
        val fixtPassword: String = fixture()
        val fixtUserDomain: UserDomain = fixture()

        every { mockSignInOrchestrator.signIn(fixtEmail, fixtPassword) } returns flow {
            delay(2)
            emit(fixtUserDomain)
        }

        sut.login(fixtEmail, fixtPassword)

        assertEquals(SignInUiState.Idle, sut.uiState.value)
        advanceTimeBy(1)
        assertEquals(SignInUiState.Loading, sut.uiState.value)
        advanceUntilIdle()
        assertEquals(SignInUiState.Success, sut.uiState.value)
    }

    @Suppress("TooGenericExceptionThrown")
    @Test
    fun `should emit error when an exception is thrown`() = runTest {
        sut.init(this)

        val fixtEmail: String = fixture()
        val fixtPassword: String = fixture()

        val exceptionFlow = flow<UserDomain> {
            delay(2)
            throw RuntimeException()
        }

        every { mockSignInOrchestrator.signIn(fixtEmail, fixtPassword) } returns exceptionFlow

        sut.login(fixtEmail, fixtPassword)

        assertEquals(SignInUiState.Idle, sut.uiState.value)
        advanceTimeBy(1)
        assertEquals(SignInUiState.Loading, sut.uiState.value)
        advanceUntilIdle()
        assertEquals(SignInUiState.Error, sut.uiState.value)
    }
}

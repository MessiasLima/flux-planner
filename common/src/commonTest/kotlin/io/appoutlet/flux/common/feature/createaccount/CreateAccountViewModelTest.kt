package io.appoutlet.flux.common.feature.createaccount

import io.appoutlet.flux.common.test.UnitTest
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@FlowPreview
internal class CreateAccountViewModelTest : UnitTest<CreateAccountViewModel>() {
    private val mockCreateAccountOrchestrator: CreateAccountOrchestrator = mockk()

    override fun buildSut() = CreateAccountViewModel(mockCreateAccountOrchestrator)

    @Test
    fun `should change name`() = runTest {
        val fixtName: String = fixture()

        sut.onNameChange(fixtName)

        assertEquals(fixtName, sut.name.value.value)
        assertTrue(sut.name.value.isValid)
    }

    @Test
    fun `should validate name - space`() = runTest {
        val fixtName = " "

        sut.onNameChange(fixtName)

        assertEquals(fixtName, sut.name.value.value)
        assertFalse(sut.name.value.isValid)
    }

    @Test
    fun `should validate name - empty string`() = runTest {
        val fixtName = ""

        sut.onNameChange(fixtName)

        assertEquals(fixtName, sut.name.value.value)
        assertFalse(sut.name.value.isValid)
    }

    @Test
    fun `should change email`() = runTest {
        val fixtEmail = "someemail@domain.com"

        sut.onEmailChange(fixtEmail)

        assertEquals(fixtEmail, sut.email.value.value)
        assertTrue(sut.email.value.isValid)
    }

    @Test
    fun `should validate email - invalid email`() = runTest {
        val fixtEmail = "some-emaildomain/.com"

        sut.onEmailChange(fixtEmail)

        assertEquals(fixtEmail, sut.email.value.value)
        assertFalse(sut.email.value.isValid)
    }

    @Test
    fun `should change password`() {
        val fixtPassword: String = fixture()

        sut.onPasswordChange(fixtPassword)
        sut.onPasswordConfirmationChange(fixtPassword)

        assertEquals(fixtPassword, sut.password.value.value)
        assertTrue(sut.password.value.isValid)

        assertEquals(fixtPassword, sut.passwordConfirmation.value.value)
        assertTrue(sut.passwordConfirmation.value.isValid)
    }

    @Test
    fun `should verify password - different passes`() {
        val fixtPassword: String = fixture()
        val fixtPasswordConfirmation: String = fixture()

        sut.onPasswordChange(fixtPassword)
        sut.onPasswordConfirmationChange(fixtPasswordConfirmation)

        assertEquals(fixtPassword, sut.password.value.value)
        assertFalse(sut.password.value.isValid)

        assertEquals(fixtPasswordConfirmation, sut.passwordConfirmation.value.value)
        assertFalse(sut.passwordConfirmation.value.isValid)
    }

    @Test
    fun `should verify password - not so complex passes`() {
        val fixtPassword = "123"
        val fixtPasswordConfirmation = "123"

        sut.onPasswordChange(fixtPassword)
        sut.onPasswordConfirmationChange(fixtPasswordConfirmation)

        assertEquals(fixtPassword, sut.password.value.value)
        assertFalse(sut.password.value.isValid)

        assertEquals(fixtPasswordConfirmation, sut.passwordConfirmation.value.value)
        assertFalse(sut.passwordConfirmation.value.isValid)
    }
}

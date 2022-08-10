package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.test.BaseUnitTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AuthenticationRequestMapperTest : BaseUnitTest<AuthenticationRequestMapper> () {
    override fun buildSut() = AuthenticationRequestMapper()

    @Test
    fun `should map authentication request`() {
        val fixtEmail: String = fixture()
        val fixtPassword: String = fixture()

        val actual  = sut.map(email = fixtEmail, password = fixtPassword)

        assertEquals(fixtEmail, actual.email)
        assertEquals(fixtPassword, actual.password)
        assertTrue(actual.returnSecureToken)
    }
}

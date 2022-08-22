package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.test.UnitTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SignUpWithEmailRequestMapperTest : UnitTest<SignUpWithEmailRequestMapper>() {
    override fun buildSut() = SignUpWithEmailRequestMapper()

    @Test
    fun `should map SignUpWithEmailRequest`() {
        val fixtEmail: String = fixture()
        val fixtPassword: String = fixture()

        val actual = sut.map(email = fixtEmail, password = fixtPassword)

        assertEquals(actual.email, fixtEmail)
        assertEquals(actual.password, fixtPassword)
    }
}

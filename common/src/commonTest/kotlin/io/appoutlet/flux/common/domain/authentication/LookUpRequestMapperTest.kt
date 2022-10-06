package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.test.UnitTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LookUpRequestMapperTest : UnitTest<LookUpRequestMapper>() {
    override fun buildSut() = LookUpRequestMapper()

    @Test
    fun `should map look up request`() {
        val fixtUserDomain: UserDomain = fixture()

        val actual = sut.map(fixtUserDomain)

        assertEquals(fixtUserDomain.idToken, actual = actual.idToken)
    }
}

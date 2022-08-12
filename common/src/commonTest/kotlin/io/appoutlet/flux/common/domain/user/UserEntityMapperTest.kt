package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.test.UnitTest
import io.appoutlet.flux.common.util.toLong
import kotlin.test.Test
import kotlin.test.assertEquals

class UserEntityMapperTest : UnitTest<UserEntityMapper>() {
    override fun buildSut() = UserEntityMapper()

    @Test
    fun `should map from user domain`() {
        val fixtUserDomain: UserDomain = fixture()

        val actual = sut.map(fixtUserDomain)

        assertEquals(fixtUserDomain.id, actual.id)
        assertEquals(fixtUserDomain.email, actual.email)
        assertEquals(fixtUserDomain.displayName, actual.displayName)
        assertEquals(fixtUserDomain.idToken, actual.idToken)
        assertEquals(fixtUserDomain.refreshToken, actual.refreshToken)
        assertEquals(fixtUserDomain.registered.toLong(), actual.registered)
    }
}

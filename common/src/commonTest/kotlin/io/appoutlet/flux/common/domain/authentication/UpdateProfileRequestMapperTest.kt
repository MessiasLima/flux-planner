package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.test.UnitTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class UpdateProfileRequestMapperTest : UnitTest<UpdateProfileRequestMapper>(){
    override fun buildSut() = UpdateProfileRequestMapper()

    @Test
    fun `should map UpdateProfileRequest`() {
        val fixtUserDomain: UserDomain = fixture()
        val fixtDisplayName: String = fixture()

        val actual = sut.map(user = fixtUserDomain, displayName = fixtDisplayName)

        assertEquals(actual.idToken, fixtUserDomain.idToken)
        assertEquals(actual.displayName, fixtDisplayName)
    }
}

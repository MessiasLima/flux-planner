package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.UserEntity
import io.appoutlet.flux.common.core.network.authentication.AuthenticationResponse
import io.appoutlet.flux.common.test.BaseUnitTest
import io.appoutlet.flux.common.util.toBoolean
import kotlin.test.Test
import kotlin.test.assertEquals

class UserDomainMapperTest : BaseUnitTest<UserDomainMapper>() {
    override fun buildSut() = UserDomainMapper()

    @Test
    fun `should map from authentication response`() {
        val fixtAuthenticationResponse: AuthenticationResponse = fixture()

        val actual = sut.map(fixtAuthenticationResponse)

        assertEquals(fixtAuthenticationResponse.localId, actual.id)
        assertEquals(fixtAuthenticationResponse.email, actual.email)
        assertEquals(fixtAuthenticationResponse.displayName, actual.displayName)
        assertEquals(fixtAuthenticationResponse.idToken, actual.idToken)
        assertEquals(fixtAuthenticationResponse.refreshToken, actual.refreshToken)
        assertEquals(fixtAuthenticationResponse.registered, actual.registered)
    }

    @Test
    fun `should map from user entity`() {
        val fixtUserEntity: UserEntity = fixture()

        val actual = sut.map(fixtUserEntity)

        assertEquals(fixtUserEntity.id, actual.id)
        assertEquals(fixtUserEntity.email, actual.email)
        assertEquals(fixtUserEntity.displayName, actual.displayName)
        assertEquals(fixtUserEntity.idToken, actual.idToken)
        assertEquals(fixtUserEntity.refreshToken, actual.refreshToken)
        assertEquals(fixtUserEntity.registered.toBoolean(), actual.registered)
    }
}

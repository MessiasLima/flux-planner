package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.generated.UserEntity
import io.appoutlet.flux.common.core.network.authentication.AuthenticationResponse
import io.appoutlet.flux.common.core.network.authentication.LookUpResponse
import io.appoutlet.flux.common.core.network.authentication.SignUpWithEmailResponse
import io.appoutlet.flux.common.feature.splash.exception.UserNotLoggedException
import io.appoutlet.flux.common.test.UnitTest
import io.appoutlet.flux.common.util.toBoolean
import kotlin.test.Test
import kotlin.test.assertEquals

class UserDomainMapperTest : UnitTest<UserDomainMapper>() {
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

    @Test
    fun `should map from SignUpWithEmailResponse`() {
        val fixtSignUpWithEmailResponse: SignUpWithEmailResponse = fixture()

        val actual = sut.map(fixtSignUpWithEmailResponse)

        assertEquals(fixtSignUpWithEmailResponse.localId, actual.id)
        assertEquals(fixtSignUpWithEmailResponse.email, actual.email)
        assertEquals("", actual.displayName)
        assertEquals(fixtSignUpWithEmailResponse.idToken, actual.idToken)
        assertEquals(fixtSignUpWithEmailResponse.refreshToken, actual.refreshToken)
        assertEquals(false, actual.registered)
    }

    @Test
    fun `should map from LookUpResponse`() {
        val fixtUserDomain: UserDomain = fixture()
        val fixtLookUpResponse: LookUpResponse = fixture()

        val actual = sut.map(userDomain = fixtUserDomain, lookUpResponse = fixtLookUpResponse)

        val userResponse = fixtLookUpResponse.users.first()

        assertEquals(userResponse.localId, actual.id)
        assertEquals(userResponse.email, actual.email)
        assertEquals(userResponse.displayName, actual.displayName)
        assertEquals(fixtUserDomain.idToken, actual.idToken)
        assertEquals(fixtUserDomain.refreshToken, actual.refreshToken)
        assertEquals(userResponse.emailVerified, actual.registered)
    }

    @Test(expected = UserNotLoggedException::class)
    fun `should throw exception when lookup response don't return any user`() {
        val fixtUserDomain: UserDomain = fixture()
        val fixtLookUpResponse: LookUpResponse = fixture<LookUpResponse>().copy(users = emptyList())

        sut.map(userDomain = fixtUserDomain, lookUpResponse = fixtLookUpResponse)
    }
}

package io.appoutlet.flux.common.feature.splash

import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.feature.splash.exception.UserNotLoggedException
import io.appoutlet.flux.common.test.UnitTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SplashUiStateMapperTest : UnitTest<SplashUiStateMapper>(){
    override fun buildSut() = SplashUiStateMapper()

    @Test
    fun `should map from userDomain - registered`() {
        val fixtUser = fixture<UserDomain>().copy(registered = true)

        val actual = sut.map(fixtUser)

        assertEquals(expected = SplashUiState.Logged, actual = actual)
    }

    @Test
    fun `should map from userDomain - not registered`() {
        val fixtUser = fixture<UserDomain>().copy(registered = false)

        val actual = sut.map(fixtUser)

        assertEquals(expected = SplashUiState.EmailNotVerified, actual = actual)
    }

    @Test
    fun `should map from throwable - UserNotLoggedException`() {
        val exception = UserNotLoggedException()

        val actual = sut.map(exception)

        assertEquals(expected = SplashUiState.NotLogged, actual = actual)
    }

    @Test
    fun `should map from throwable - unknown exception`() {
        val fixtThrowable: Throwable = fixture()

        val actual = sut.map(fixtThrowable)

        assertEquals(expected = SplashUiState.Error, actual = actual)
    }
}

package io.appoutlet.flux.common.domain.authentication

import io.appoutlet.flux.common.core.network.authentication.SendEmailConfirmationRequest
import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.test.UnitTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SendEmailConfirmationRequestMapperTest : UnitTest<SendEmailConfirmationRequestMapper>() {
    override fun buildSut() = SendEmailConfirmationRequestMapper()

    @Test
    fun `should map UserDomain to SendEmailConfirmationRequest`() {
        val fixtUserDomain: UserDomain = fixture()

        val actual = sut.map(fixtUserDomain)

        assertEquals(actual.idToken, fixtUserDomain.idToken)
        assertEquals(actual.requestType, SendEmailConfirmationRequest.REQUEST_TYPE_VERIFY_EMAIL)
    }
}

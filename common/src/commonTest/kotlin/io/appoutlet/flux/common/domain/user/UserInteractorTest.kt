package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.UserEntity
import io.appoutlet.flux.common.core.database.user.UserRepository
import io.appoutlet.flux.common.test.UnitTest
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class UserInteractorTest : UnitTest<UserInteractor>() {
    private val mockUserRepository: UserRepository = mockk()
    private val mockUserEntityMapper: UserEntityMapper = mockk()
    private val mockUserDomainMapper: UserDomainMapper = mockk()

    override fun buildSut() = UserInteractor(
        userRepository = mockUserRepository,
        userEntityMapper = mockUserEntityMapper,
        userDomainMapper = mockUserDomainMapper
    )

    @Test
    fun `should save user`() {
        val fixtUserDomain: UserDomain = fixture()
        val fixtUserEntity: UserEntity = fixture()

        every { mockUserEntityMapper.map(fixtUserDomain) } returns fixtUserEntity
        every { mockUserRepository.save(fixtUserEntity) } returns fixtUserEntity
        every { mockUserDomainMapper.map(fixtUserEntity) } returns fixtUserDomain

        val actual = sut.save(fixtUserDomain)

        assertEquals(fixtUserDomain, actual)
    }
}

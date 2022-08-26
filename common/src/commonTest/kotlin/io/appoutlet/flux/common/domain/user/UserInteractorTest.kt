package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.generated.UserEntity
import io.appoutlet.flux.common.core.database.user.UserRepository
import io.appoutlet.flux.common.test.UnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertContentEquals
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

    @Test
    fun `should find all`() {
        val fixtUserEntities: List<UserEntity> = fixture()
        val fixtUserDomains: List<UserDomain> = fixture()

        fixtUserEntities.onEachIndexed { index, userEntity ->
            every { mockUserDomainMapper.map(userEntity) } returns fixtUserDomains[index]
        }

        every { mockUserRepository.findAll() } returns fixtUserEntities

        val actual = sut.findAll()

        assertContentEquals(expected = fixtUserDomains, actual = actual)
    }

    @Test
    fun `should delete all`(){
        every { mockUserRepository.deleteAll() } returns Unit

        sut.deleteAll()

        verify { mockUserRepository.deleteAll() }
    }
}

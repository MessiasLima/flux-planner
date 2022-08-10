package io.appoutlet.flux.common.core.database.user

import com.squareup.sqldelight.Query
import io.appoutlet.flux.common.core.database.FluxDB
import io.appoutlet.flux.common.core.database.UserEntity
import io.appoutlet.flux.common.core.database.UserEntityQueries
import io.appoutlet.flux.common.test.BaseUnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRepositoryTest : BaseUnitTest<UserRepository>() {

    private val mockUserEntityQueries: UserEntityQueries = mockk()
    private val mockFluxDB: FluxDB = mockk<FluxDB>().also {
        every { it.userEntityQueries } returns mockUserEntityQueries
    }

    override fun buildSut() = UserRepository(mockFluxDB)

    @Test
    fun `should find all users`() {
        val mockUserQuery: Query<UserEntity> = mockk()
        val fixtList: List<UserEntity> = fixture()

        every { mockUserQuery.executeAsList() } returns fixtList
        every { mockUserEntityQueries.findAll() } returns mockUserQuery

        val actual = sut.findAll()

        assertEquals(fixtList, actual)
    }

    @Test
    fun `should save UserEntity`() {
        val mockUserQuery: Query<UserEntity> = mockk()
        val fixtUserEntity: UserEntity = fixture()

        every { mockUserQuery.executeAsOne() } returns fixtUserEntity
        every { mockUserEntityQueries.findById(fixtUserEntity.id) } returns mockUserQuery
        every { mockUserEntityQueries.save(any(), any(), any(), any(), any(), any()) } returns Unit

        val actual = sut.save(fixtUserEntity)

        assertEquals(fixtUserEntity, actual)

        verify {
            mockUserEntityQueries.save(
                id = fixtUserEntity.id,
                email = fixtUserEntity.email,
                displayName = fixtUserEntity.displayName,
                idToken = fixtUserEntity.idToken,
                refreshToken = fixtUserEntity.refreshToken,
                registered = fixtUserEntity.registered
            )
        }
    }
}

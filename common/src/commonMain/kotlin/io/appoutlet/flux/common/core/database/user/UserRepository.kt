package io.appoutlet.flux.common.core.database.user

import io.appoutlet.flux.common.core.database.generated.FluxDB
import io.appoutlet.flux.common.core.database.generated.UserEntity

class UserRepository(
    private val database: FluxDB
) {
    fun findAll(): List<UserEntity> = database.userEntityQueries.findAll().executeAsList()

    fun save(userEntity: UserEntity): UserEntity {
        database.userEntityQueries.save(
            id = userEntity.id,
            email = userEntity.email,
            displayName = userEntity.displayName,
            idToken = userEntity.idToken,
            refreshToken = userEntity.refreshToken,
            registered = userEntity.registered
        )

        return database.userEntityQueries.findById(userEntity.id).executeAsOne()
    }

    fun deleteAll() {
        database.userEntityQueries.deleteAll()
    }
}

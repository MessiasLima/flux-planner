package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.user.UserRepository

class UserInteractor(
    private val userRepository: UserRepository,
    private val userEntityMapper: UserEntityMapper,
    private val userDomainMapper: UserDomainMapper,
) {
    fun save(user: UserDomain): UserDomain {
        val userEntity = userEntityMapper.map(user)

        val savedUser = userRepository.save(userEntity)

        return userDomainMapper.map(savedUser)
    }

    fun findAll() = userRepository.findAll()
        .map { userDomainMapper.map(it) }

    fun deleteAll() {
        userRepository.deleteAll()
    }
}

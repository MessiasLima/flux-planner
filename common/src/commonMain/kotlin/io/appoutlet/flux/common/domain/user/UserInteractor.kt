package io.appoutlet.flux.common.domain.user

import io.appoutlet.flux.common.core.database.user.UserRepository
import io.appoutlet.flux.common.feature.splash.exception.UserNotLoggedException

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

    fun getLoggedUser(): UserDomain {
        val userEntity = userRepository.findAll().firstOrNull() ?: throw UserNotLoggedException("No user logged")
        return userDomainMapper.map(userEntity)
    }

    fun deleteAll() {
        userRepository.deleteAll()
    }
}

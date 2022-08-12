package io.appoutlet.flux.common.domain.user

import org.koin.dsl.module

val userDomainModule = module {
    factory { UserDomainMapper() }
    factory { UserEntityMapper() }
    factory { UserInteractor(get(), get(), get()) }
}

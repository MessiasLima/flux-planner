package io.appoutlet.flux.common.domain

import io.appoutlet.flux.common.domain.authentication.authenticationDomainModule
import io.appoutlet.flux.common.domain.user.userDomainModule

val domainModules = arrayOf(
    authenticationDomainModule,
    userDomainModule,
)

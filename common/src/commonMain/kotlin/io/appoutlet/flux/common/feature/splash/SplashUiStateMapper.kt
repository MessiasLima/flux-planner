package io.appoutlet.flux.common.feature.splash

import io.appoutlet.flux.common.domain.user.UserDomain
import io.appoutlet.flux.common.feature.splash.exception.UserNotLoggedException

class SplashUiStateMapper {
    fun map(user: UserDomain) = if (user.registered) {
        SplashUiState.Logged
    } else {
        SplashUiState.EmailNotValidated
    }

    fun map(throwable: Throwable) = when (throwable) {
        is UserNotLoggedException -> SplashUiState.NotLogged
        else -> SplashUiState.Error
    }
}

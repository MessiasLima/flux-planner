package io.appoutlet.flux.common.core.network.common

object AccountRoutes {
    private const val baseUrl = "https://identitytoolkit.googleapis.com/v1/accounts"

    fun signInWithPassword() = "$baseUrl:signInWithPassword"

    fun signUp() = "$baseUrl:signUp"
}

val Route.Accounts
    get() = AccountRoutes

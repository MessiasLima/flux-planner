package io.appoutlet.flux.common.domain.user

data class UserDomain(
    val id: String,
    val email: String,
    val displayName: String,
    val idToken: String,
    val refreshToken: String?,
    val registered: Boolean,
)

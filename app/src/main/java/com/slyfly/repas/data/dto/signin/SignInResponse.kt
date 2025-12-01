package com.slyfly.repas.data.dto.signin

data class SignInResponse(
    val success: Boolean,
    val message: String?,
    val token: String?,
    val user: UserDto?
)

data class UserDto(
    val id_user: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val city: String,
    val postalCode: String
)
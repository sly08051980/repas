package com.slyfly.repas.data.dto.register

data class RegisterResponse(
    val success: Boolean,
    val userId: String?,
    val message: String?,
    val token: String?
)

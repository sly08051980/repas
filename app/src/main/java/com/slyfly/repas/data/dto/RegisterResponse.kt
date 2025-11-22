package com.slyfly.repas.data.dto

data class RegisterResponse(
    val success: Boolean,
    val userId: String?,
    val message: String?,
    val token: String?
)

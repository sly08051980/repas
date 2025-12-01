package com.slyfly.repas.data.dto.register

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val city:String,
    val postalCode:String,
    val email: String,
    val password: String
)

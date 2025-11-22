package com.slyfly.repas.domain.model

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val city:String,
    val postalCode:String,
    val email: String,
    val token: String? = null
)

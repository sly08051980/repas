package com.slyfly.repas.data.dto.token

import com.slyfly.repas.logic.interfaces.SignUpIntent

data class TokenResponse(
    val success :Boolean,
    val message:String,
    val user:UserTokenDto?,
    val token:String?
)

data class UserTokenDto(
    val id_user:String,
    val email:String,
    val city:String,
    val postalCode:String,
    val firstName: String,
    val lastName:String
)
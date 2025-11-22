package com.slyfly.repas.data.dto.signin

data class SignInResponse(
    val success:Boolean,
    val userId:String?,
    val message:String?,
    val token:String?
)
package com.slyfly.repas.domain.model

import com.slyfly.repas.data.dto.token.UserTokenDto

data class Token(
    val token:String,
    val user:UserTokenDto?
)

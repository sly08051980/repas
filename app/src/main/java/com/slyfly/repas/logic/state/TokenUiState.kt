package com.slyfly.repas.logic.state

import com.slyfly.repas.data.dto.token.UserTokenDto
import kotlinx.serialization.descriptors.PrimitiveKind

data class TokenUiState(
    val token :String="",
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:Boolean=false,
    val user:UserTokenDto?=null
)

package com.slyfly.repas.domain.repository

import com.slyfly.repas.domain.model.Token

interface TokenRepository {
    suspend fun tokenRepo(
        token:String
    ):Result<Token>
}
package com.slyfly.repas.domain.usecase

import com.slyfly.repas.domain.model.Token
import com.slyfly.repas.domain.repository.TokenRepository

class TokenUserUseCase(private val repo:TokenRepository) {
    suspend operator fun invoke(
        token:String
    ):Result<Token> = repo.tokenRepo(token)
}
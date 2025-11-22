package com.slyfly.repas.domain.usecase

import com.slyfly.repas.domain.model.SignIn
import com.slyfly.repas.domain.repository.SignInRepository

class SignInUserUseCase(private val repo:SignInRepository) {
    suspend operator fun invoke(
        email:String,
        password:String
    ):Result<SignIn> = repo.signIn(email,password)
}
package com.slyfly.repas.domain.repository

import com.slyfly.repas.domain.model.SignIn

interface SignInRepository {
    suspend fun signIn(
        email: String,
        password: String
    ):Result<SignIn>
}
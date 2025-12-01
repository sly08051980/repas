package com.slyfly.repas.domain.usecase

import com.slyfly.repas.domain.model.User
import com.slyfly.repas.domain.repository.UserRepository

class RegisterUserUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        city: String,
        postalCode: String,
        email: String,
        password: String
    ): Result<User> {
        return repo.register(
            firstName = firstName,
            lastName = lastName,
            city = city,
            postalCode = postalCode,
            email = email,
            password = password
        )
    }
}


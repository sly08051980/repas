package com.slyfly.repas.domain.repository


import com.slyfly.repas.domain.model.User

interface UserRepository {
    suspend fun register(
        firstName: String,
        lastName: String,
        city: String,
        postalCode: String,
        email: String,
        password: String,
        password1: String
    ): Result<User>
    // (tu ajouteras login() plus tard)
}

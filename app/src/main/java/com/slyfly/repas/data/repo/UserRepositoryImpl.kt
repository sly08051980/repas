package com.slyfly.repas.data.repo



import com.slyfly.repas.core.datastore.SessionManager
import com.slyfly.repas.data.dto.register.RegisterRequest
import com.slyfly.repas.data.service.UserService
import com.slyfly.repas.domain.model.User
import com.slyfly.repas.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: UserService,
    private val session: SessionManager
) : UserRepository {

    override suspend fun register(
        firstName: String,
        lastName: String,
        city: String,
        postalCode: String,
        email: String,
        password: String
    ): Result<User> = try {
        val res = api.register(RegisterRequest(firstName, lastName,city, postalCode, email, password))
        if (res.success && res.userId != null) {
            res.token?.let { session.saveToken(it) }
            Result.success(
                User(
                    id = res.userId,
                    firstName = firstName,
                    lastName = lastName,
                    city=city,
                    postalCode=postalCode,
                    email = email,
                    token = res.token
                )
            )
        } else {
            Result.failure(Exception(res.message ?: "Inscription impossible"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}

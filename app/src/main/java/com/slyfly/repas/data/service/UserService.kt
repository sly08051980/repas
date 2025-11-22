package com.slyfly.repas.data.service

import com.slyfly.repas.data.dto.RegisterRequest
import com.slyfly.repas.data.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("nutrition/register.php")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse
}

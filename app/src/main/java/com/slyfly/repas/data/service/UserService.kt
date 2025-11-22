package com.slyfly.repas.data.service

import com.slyfly.repas.data.dto.register.RegisterRequest
import com.slyfly.repas.data.dto.register.RegisterResponse
import com.slyfly.repas.data.dto.signin.SignInRequest
import com.slyfly.repas.data.dto.signin.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("nutrition/register.php")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse
    @POST("nutrition/userConnexion.php")
    suspend fun signIn(@Body body:SignInRequest): SignInResponse
}

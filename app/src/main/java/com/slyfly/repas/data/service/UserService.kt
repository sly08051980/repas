package com.slyfly.repas.data.service

import com.slyfly.repas.data.dto.register.RegisterRequest
import com.slyfly.repas.data.dto.register.RegisterResponse
import com.slyfly.repas.data.dto.signin.SignInRequest
import com.slyfly.repas.data.dto.signin.SignInResponse
import com.slyfly.repas.data.dto.token.TokenRequest
import com.slyfly.repas.data.dto.token.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("nutrition/register.php")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse
    @POST("nutrition/userConnexion.php")
    suspend fun signIn(@Body body:SignInRequest): SignInResponse
    @POST("nutrition/tokenConnect.php")
    suspend fun token(@Body body:TokenRequest):TokenResponse
}

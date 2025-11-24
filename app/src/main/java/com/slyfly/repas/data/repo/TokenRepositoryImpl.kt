package com.slyfly.repas.data.repo

import com.slyfly.repas.core.datastore.SessionManager
import com.slyfly.repas.data.dto.token.TokenRequest
import com.slyfly.repas.data.service.UserService
import com.slyfly.repas.domain.model.Token
import com.slyfly.repas.domain.repository.TokenRepository

class TokenRepositoryImpl(
    private val api : UserService,

):TokenRepository {

    override suspend fun tokenRepo(
        token:String
    ):Result<Token>{
        return try{
            val res=api.token(TokenRequest(token))

            if(res.success && res.token!=null){
                Result.success(
                    Token(
                        token=res.token,
                        user=res.user
                    )
                )
            }else{
                Result.failure(Exception(res.message))
            }
        }catch (
            e:Exception
        ){
            Result.failure(e)
        }
    }
}
import com.slyfly.repas.core.datastore.SessionManager
import com.slyfly.repas.data.dto.signin.SignInRequest
import com.slyfly.repas.data.service.UserService
import com.slyfly.repas.domain.model.SignIn
import com.slyfly.repas.domain.repository.SignInRepository

class SignInRepositoryImpl(
    private val api: UserService,
    private val session: SessionManager
) : SignInRepository {

    override suspend fun signIn(
        email: String,
        password: String
    ): Result<SignIn> = try {

        val res = api.signIn(SignInRequest(email, password))

        if (res.success && res.user != null && res.token != null) {


            session.saveToken(res.token)

            Result.success(
                SignIn(
                    email = email,
                    token = res.token
                )
            )

        } else {
            Result.failure(Exception(res.message ?: "Connexion impossible"))
        }

    } catch (e: Exception) {
        Result.failure(e)
    }
}

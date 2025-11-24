package com.slyfly.repas.core.di



import SignInRepositoryImpl
import org.koin.dsl.module


import com.slyfly.repas.core.network.NetworkModule
import com.slyfly.repas.core.utils.Constants
import com.slyfly.repas.data.repo.UserRepositoryImpl
import com.slyfly.repas.data.service.UserService
import com.slyfly.repas.domain.repository.UserRepository
import com.slyfly.repas.domain.usecase.RegisterUserUseCase
import com.slyfly.repas.logic.viewmodel.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import com.slyfly.repas.core.datastore.SessionManager
import com.slyfly.repas.data.repo.TokenRepositoryImpl
import com.slyfly.repas.domain.repository.SignInRepository
import com.slyfly.repas.domain.repository.TokenRepository
import com.slyfly.repas.domain.usecase.SignInUserUseCase
import com.slyfly.repas.domain.usecase.TokenUserUseCase
import com.slyfly.repas.logic.viewmodel.SignInViewModel
import com.slyfly.repas.logic.viewmodel.TokenAutoLogViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    // Retrofit
    single<Retrofit> {
        NetworkModule.provideRetrofit(Constants.BASE_URL)
    }
    single<UserService> { get<Retrofit>().create(UserService::class.java) }

    // Session
   single { SessionManager(androidContext()) }
//INSCRIPTION
    // Repositories
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    // UseCases
    single { RegisterUserUseCase(get()) }

    // ViewModels
    viewModel { SignUpViewModel(get()) }

    //CONNEXION
    single<SignInRepository>{SignInRepositoryImpl(get(),get())}
    single { SignInUserUseCase(get ()) }
    viewModel { SignInViewModel(get()) }

    //auto connexion
    single<TokenRepository>{TokenRepositoryImpl(get())}
    single {TokenUserUseCase(get())}
    viewModel{TokenAutoLogViewModel(get(),get())}

}


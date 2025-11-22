package com.slyfly.repas.core.di



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

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    // UseCases
    single { RegisterUserUseCase(get()) }

    // ViewModels
    viewModel { SignUpViewModel(get()) }
}


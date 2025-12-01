package com.slyfly.repas.core.di


import com.slyfly.repas.core.network.NetworkModule
import com.slyfly.repas.core.utils.Constants
import com.slyfly.repas.data.repo.ScannerProductRepositoryImpl
import com.slyfly.repas.data.service.ScannerService
import com.slyfly.repas.domain.repository.ScannerProductRepository
import com.slyfly.repas.domain.usecase.ScannerUseCase
import com.slyfly.repas.logic.viewmodel.ScannerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val scanModule= module {
    single <Retrofit>(qualifier= named("Scan")){
        NetworkModule.provideRetrofit(Constants.BASE_URL_SCAN)
    }
    single<ScannerService>{get<Retrofit>(named("Scan")).create(ScannerService::class.java)}

single<ScannerProductRepository>{ScannerProductRepositoryImpl(get())}
    single{ScannerUseCase(get())}
    viewModel{ ScannerViewModel(get()) }
}
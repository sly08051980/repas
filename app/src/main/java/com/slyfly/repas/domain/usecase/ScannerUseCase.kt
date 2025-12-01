package com.slyfly.repas.domain.usecase

import com.google.gson.annotations.SerializedName
import com.slyfly.repas.domain.model.ScannerProduct
import com.slyfly.repas.domain.model.ScannerProductNutriments
import com.slyfly.repas.domain.repository.ScannerProductRepository

class ScannerUseCase(private val repo :ScannerProductRepository) {
    suspend operator fun invoke(barCode: String): Result<ScannerProduct> {
        return repo.scannerProduct(barCode)
    }}
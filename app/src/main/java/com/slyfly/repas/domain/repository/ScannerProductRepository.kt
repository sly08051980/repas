package com.slyfly.repas.domain.repository

import com.slyfly.repas.domain.model.ScannerProduct


interface ScannerProductRepository {
        suspend fun scannerProduct(barCode: String): Result<ScannerProduct>

}
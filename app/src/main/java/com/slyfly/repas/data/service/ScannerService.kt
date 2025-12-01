package com.slyfly.repas.data.service

import com.slyfly.repas.data.dto.scan.ScanResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ScannerService {
    @GET("api/v2/product/{barcode}.json")
   suspend fun scannerProduct(@Path("barcode") barcode: String): ScanResponse
}
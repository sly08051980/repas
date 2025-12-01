package com.slyfly.repas.core.barcode

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class BarCodeScanner(context : Context) {

    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_ALL_FORMATS)
        .enableAutoZoom()
        .build()

    val scanner = GmsBarcodeScanning.getClient(context,options)

    fun startScanner(onResult: (String?) -> Unit) {
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                onResult(barcode.rawValue)
            }
            .addOnCanceledListener {
                onResult(null)
            }
            .addOnFailureListener { e ->
                onResult(null)
            }
    }

}
package com.slyfly.repas

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.slyfly.repas.core.di.appModule
import com.slyfly.repas.core.di.scanModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class RepasApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

            androidContext(this@RepasApp)
            modules(appModule,scanModule)
        }
    }
}

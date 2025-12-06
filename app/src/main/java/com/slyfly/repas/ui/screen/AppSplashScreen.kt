package com.slyfly.repas.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.slyfly.repas.R
import com.slyfly.repas.core.datastore.SessionManager
import com.slyfly.repas.logic.viewmodel.TokenAutoLogViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@Composable
fun AppSplashScreen(
    onValidate: () -> Unit,
    onFailure: () -> Unit,
    viewModel: TokenAutoLogViewModel = koinViewModel(),
    sessionManager: SessionManager = koinInject()
) {

    LaunchedEffect(true) {

        delay(1000)
        val token = sessionManager.getTokenOnce()

        if (token.isNullOrBlank()) {
            onFailure()
            return@LaunchedEffect
        }


        viewModel.checkToken(token) { success ->

            if (success) {
                onValidate()
            } else {
                onFailure()
            }
        }
    }

    AppSplashScreenContent()
}


@Composable
fun AppSplashScreenContent(){
    Column(modifier= Modifier.fillMaxSize()) {
        Image(painter = painterResource(R.drawable.splashscreen),contentDescription = "splashscreen"
            , modifier = Modifier.fillMaxSize())
    }

}
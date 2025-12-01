package com.slyfly.repas.ui.nav


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.slyfly.repas.core.datastore.SessionManager
import com.slyfly.repas.domain.usecase.ScannerUseCase
import com.slyfly.repas.logic.viewmodel.ScannerViewModel
import com.slyfly.repas.ui.appbar.AppBarScreen
import com.slyfly.repas.ui.screen.AppSplashScreen
import com.slyfly.repas.ui.screen.HomeScreen

import com.slyfly.repas.ui.screen.ScannerProductScreen

import com.slyfly.repas.ui.screen.SignInScreen
import com.slyfly.repas.ui.screen.SignUpScreen
import com.slyfly.repas.ui.screen.Test
import org.koin.androidx.compose.getViewModel


object Routes {
    const val SignUp = "signup"
    const val Home = "home"
    const val SignIn = "signin"
    const val SplashScreen="splashcreen"
    const val ScannerProductScreen="scannerproductscreen"
    const val Test="test"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavHost(navController: NavHostController) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scannerViewModel: ScannerViewModel = getViewModel()

    Scaffold(
        topBar = { AppBarScreen() },
        containerColor = Color.Transparent,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.SplashScreen,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Routes.SignUp) {
                SignUpScreen(
                    onRegistered = {
                        navController.navigate(Routes.SignIn)
                    },
                    onGoToSignIn = {
                        navController.navigate(Routes.SignIn)
                    }
                )
            }

            composable(Routes.SignIn) {
                SignInScreen(
                    onValidate = {
                        navController.navigate(Routes.Home) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onGoToSignUp = {
                        navController.navigate(Routes.SignUp)
                    }
                )
            }

            composable(Routes.Home) {


                HomeScreen(viewModel = scannerViewModel,navController)

            }
            composable(Routes.SplashScreen){
               AppSplashScreen(
                   onValidate={navController.navigate(Routes.Home){
                       popUpTo(0) { inclusive = true }
                   }},
                   onFailure={navController.navigate(Routes.SignUp){
                       popUpTo(0) { inclusive = true }
                   }}
               )
            }
            composable(Routes.ScannerProductScreen){

                ScannerProductScreen(viewModel=scannerViewModel)
            }
            composable(Routes.Test){
                Test(

                )
            }
        }
    }
}


package com.slyfly.repas.ui.nav

import HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.slyfly.repas.core.datastore.SessionManager
import com.slyfly.repas.ui.appbar.AppBarScreen

import com.slyfly.repas.ui.screen.SignInScreen
import com.slyfly.repas.ui.screen.SignUpScreen
import org.koin.androidx.compose.get



object Routes {
    const val SignUp = "signup"
    const val Home = "home"
    const val SignIn = "signin"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavHost(navController: NavHostController) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { AppBarScreen() },
        containerColor = Color.Transparent,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.SignUp,
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
                val session = get<SessionManager>()
                HomeScreen(sessionManager = session)
            }
        }
    }
}

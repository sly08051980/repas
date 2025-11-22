package com.slyfly.repas.ui.nav

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
import com.slyfly.repas.ui.appbar.AppBarScreen
import com.slyfly.repas.ui.screen.SignUpScreen

object Routes {
    const val SignUp = "signup"
    const val Home = "home"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavHost(navController: NavHostController) {

    //pour appbar toujours visible 1
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { AppBarScreen() },
        containerColor = Color.Transparent,
        //pour appbar toujours visible 2
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.SignUp,
            //pour appbar toujours visible 3
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Routes.SignUp) {
                SignUpScreen(
                    onRegistered = {
                        navController.navigate(Routes.Home) {
                            popUpTo(Routes.SignUp) { inclusive = true }
                        }
                    }
                )
            }

            composable(Routes.Home) {
                // TODO
            }
        }
    }
}

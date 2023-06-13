package com.tolgakurucay.mynotebooknew.modules

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.window.layout.DisplayFeature
import com.tolgakurucay.mynotebooknew.MyNotebookNavigationActions
import com.tolgakurucay.mynotebooknew.MyNotebookNewDestinations
import com.tolgakurucay.mynotebooknew.MyNotebookNewDestinationsArgs.USER_ID_ARG
import com.tolgakurucay.mynotebooknew.MyNotebookNewDestinationsArgs.USER_NAME_ARG
import com.tolgakurucay.mynotebooknew.appstate.MyNotebookAppState
import com.tolgakurucay.mynotebooknew.appstate.rememberMyNotebookAppState
import com.tolgakurucay.mynotebooknew.modules.forgot_password.ForgotPassword
import com.tolgakurucay.mynotebooknew.modules.home.Home
import com.tolgakurucay.mynotebooknew.modules.login.Login
import com.tolgakurucay.mynotebooknew.modules.register.Register

@Composable
fun MyNotebookAppGraph(
    windowSizeClass: WindowSizeClass,
    displayFeature: List<DisplayFeature>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MyNotebookNewDestinations.REGISTER_ROUTE,
    appState: MyNotebookAppState = rememberMyNotebookAppState(),
    navActions: MyNotebookNavigationActions = remember(navController) {
        MyNotebookNavigationActions(navController)
    }

) {

    //Use this when you use drawer
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    if (appState.isOnline) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {

            composable(
                MyNotebookNewDestinations.HOME_ROUTE,
                arguments = listOf(
                    navArgument(USER_ID_ARG) {
                        type = NavType.IntType
                        nullable = false
                    },
                    navArgument(USER_NAME_ARG) {
                        type = NavType.StringType
                        nullable = true
                    }
                ),
            ) { entry ->
                val userId = remember {
                    entry.arguments?.getInt(USER_ID_ARG)
                }
                val userName = remember {
                    entry.arguments?.getString(USER_NAME_ARG)
                }
                Home(userId = userId, userName = userName)
            }

            composable(MyNotebookNewDestinations.PROFILE_ROUTE) {

            }

            composable(MyNotebookNewDestinations.LOGIN_ROUTE) {
                Login(
                    onNavigateToForgotPasswordMain = {
                        navActions.navigateToHome(100,"Tolga")
                        //navActions.navigateToForgotPassword()
                    },
                    onNavigateToRegisterMain = {
                        navActions.navigateToRegister()
                    },
                )
            }

            composable(MyNotebookNewDestinations.FORGOT_PASSWORD_ROUTE) {
                ForgotPassword()
            }

            composable(MyNotebookNewDestinations.REGISTER_ROUTE) {
                Register(onNavigateToLoginParent = {
                    navActions.navigateToLogin()
                })
            }


        }

    }

}
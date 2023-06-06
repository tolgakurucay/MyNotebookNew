package com.tolgakurucay.mynotebooknew.modules

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.window.layout.DisplayFeature
import com.tolgakurucay.mynotebooknew.MyNotebookNavigationActions
import com.tolgakurucay.mynotebooknew.MyNotebookNewDestinations
import com.tolgakurucay.mynotebooknew.MyNotebookNewDestinationsArgs.USER_ID_ARG
import com.tolgakurucay.mynotebooknew.MyNotebookNewDestinationsArgs.USER_NAME_ARG
import com.tolgakurucay.mynotebooknew.appstate.MyNotebookAppState
import com.tolgakurucay.mynotebooknew.appstate.rememberMyNotebookAppState
import com.tolgakurucay.mynotebooknew.modules.forgot_password.ForgotPassword
import com.tolgakurucay.mynotebooknew.modules.forgot_password.ForgotPasswordViewModel
import com.tolgakurucay.mynotebooknew.modules.home.Home
import com.tolgakurucay.mynotebooknew.modules.login.Login
import com.tolgakurucay.mynotebooknew.modules.register.Register
import com.tolgakurucay.mynotebooknew.modules.register.RegisterViewModel

@Composable
fun MyNotebookApp(
    windowSizeClass: WindowSizeClass,
    displayFeature: List<DisplayFeature>,
    modifier: Modifier = Modifier,
    startDestination: String = MyNotebookNewDestinations.LOGIN_ROUTE,
    appState: MyNotebookAppState = rememberMyNotebookAppState(),
    navActions: MyNotebookNavigationActions = remember(appState.navController) {
        MyNotebookNavigationActions(appState.navController)
    }

) {
    if (appState.isOnline) {
        NavHost(navController = appState.navController, startDestination = startDestination) {

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
                    entry.arguments?.getInt("userId")
                }
                val userName = remember {
                    entry.arguments?.getString("userName")
                }
                Home(userId = userId, userName = userName)
            }

            composable(MyNotebookNewDestinations.PROFILE_ROUTE) {

            }


            composable(MyNotebookNewDestinations.LOGIN_ROUTE) {
                Login(
                    onNavigateToForgotPasswordMain = {
                        navActions.navigateToForgotPassword()
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
                Register()
            }


        }
    }

}
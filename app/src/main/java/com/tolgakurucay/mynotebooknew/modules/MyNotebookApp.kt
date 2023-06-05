package com.tolgakurucay.mynotebooknew.modules

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.tolgakurucay.mynotebooknew.appstate.MyNotebookAppState
import com.tolgakurucay.mynotebooknew.appstate.Screen
import com.tolgakurucay.mynotebooknew.appstate.rememberMyNotebookAppState
import com.tolgakurucay.mynotebooknew.modules.forgot_password.ForgotPassword
import com.tolgakurucay.mynotebooknew.modules.forgot_password.ForgotPasswordViewModel
import com.tolgakurucay.mynotebooknew.modules.home.Home
import com.tolgakurucay.mynotebooknew.modules.home.HomeViewModel
import com.tolgakurucay.mynotebooknew.modules.login.Login
import com.tolgakurucay.mynotebooknew.modules.login.LoginViewModel
import com.tolgakurucay.mynotebooknew.modules.register.Register
import com.tolgakurucay.mynotebooknew.modules.register.RegisterViewModel

@Composable
fun MyNotebookApp(
    windowSizeClass : WindowSizeClass,
    displayFeature : List<DisplayFeature>,
    appState: MyNotebookAppState = rememberMyNotebookAppState()
){
    if(appState.isOnline){
        NavHost(navController = appState.navController, startDestination = Screen.Home.route){

            composable(Screen.Home.route){
                Home(viewModel = hiltViewModel<HomeViewModel>())
            }

            composable(Screen.Profile.route){

            }

            composable(Screen.Player.route){

            }

            composable(Screen.Login.route){
                val loginViewModel : LoginViewModel = viewModel()
                Login(viewModel = loginViewModel,appState = appState)
            }

            composable(Screen.ForgotPassword.route){
                val registerViewModel : ForgotPasswordViewModel = viewModel()
                ForgotPassword(registerViewModel)
            }

            composable(Screen.Register.route){
                val registerViewModel : RegisterViewModel = viewModel()
                Register(registerViewModel,appState)
            }


        }
    }

}
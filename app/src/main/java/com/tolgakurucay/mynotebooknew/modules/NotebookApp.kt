package com.tolgakurucay.mynotebooknew.modules

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import com.tolgakurucay.mynotebooknew.appstate.MyNotebookAppState
import com.tolgakurucay.mynotebooknew.appstate.Screen
import com.tolgakurucay.mynotebooknew.appstate.rememberMyNotebookAppState
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
        NavHost(navController = appState.navController, startDestination = Screen.Login.route){

            composable(Screen.Home.route){ navBackStackEntry ->

            }

            composable(Screen.Profile.route){

            }

            composable(Screen.Player.route){

            }

            composable(Screen.Login.route){
                val loginViewModel : LoginViewModel = viewModel()
                Login(viewModel = loginViewModel)
            }

            composable(Screen.ForgotPassword.route){

            }

            composable(Screen.Register.route){
                val registerViewModel : RegisterViewModel = viewModel()
                Register(registerViewModel)
            }


        }
    }

}
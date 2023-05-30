package com.tolgakurucay.mynotebooknew.modules

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import com.tolgakurucay.mynotebooknew.appstate.MyNotebookAppState
import com.tolgakurucay.mynotebooknew.appstate.Screen
import com.tolgakurucay.mynotebooknew.appstate.rememberMyNotebookAppState

@Composable
fun MyNotebookApp(
    windowSizeClass : WindowSizeClass,
    displayFeature : List<DisplayFeature>,
    appState: MyNotebookAppState = rememberMyNotebookAppState()
){
    if(appState.isOnline){
        NavHost(navController = appState.navController, startDestination = Screen.Home.route){

            composable(Screen.Home.route){ navBackStackEntry ->


            }

            composable(Screen.Profile.route){


            }

            composable(Screen.Player.route){


            }


        }
    }

}
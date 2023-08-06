package com.tolgakurucay.mynotebooknew.presentation.activity

import android.Manifest
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookAppGraph
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewDestinations
import com.tolgakurucay.mynotebooknew.presentation.theme.MyNotebookNewTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Main activity for the MyNotebookApp
 */
@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {


    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            MyNotebookNewTheme {

                setup()
                observeState()

            }


        }
    }


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalPermissionsApi::class)
    @Composable
    private fun setup() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
//        val windowSizeClass = calculateWindowSizeClass(activity = this)
//        val displayFeatures = calculateDisplayFeatures(activity = this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationState =
                rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
            SideEffect {
                notificationState.launchPermissionRequest()
            }
        }
        mainViewModel.isUserLoggedIn()

    }

    @Composable
    private fun observeState() {
        var state = mainViewModel.state.value
        if(state.isUserLoggedIn){
            MyNotebookAppGraph(startDestination = MyNotebookNewDestinations.HOME_ROUTE)
        }
        else{
            MyNotebookAppGraph(startDestination = MyNotebookNewDestinations.LOGIN_ROUTE)

        }
    }

}

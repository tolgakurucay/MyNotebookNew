package com.tolgakurucay.mynotebooknew.presentation.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookAppGraph
import com.tolgakurucay.mynotebooknew.presentation.Destinations
import com.tolgakurucay.mynotebooknew.presentation.theme.MyNotebookNewTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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


    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun setup() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationState =
                rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
            SideEffect {
                notificationState.launchPermissionRequest()
            }
        }

    }

    @Composable
    private fun observeState() {
        val loggedIn = mainViewModel.state.collectAsStateWithLifecycle().value
        when (loggedIn.isUserLoggedIn) {
            true -> MyNotebookAppGraph(startDestination = Destinations.HOME_ROUTE)

            false -> MyNotebookAppGraph(startDestination = Destinations.LOGIN_ROUTE)

        }
    }

}

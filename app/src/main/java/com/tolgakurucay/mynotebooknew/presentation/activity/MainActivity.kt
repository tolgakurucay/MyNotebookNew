package com.tolgakurucay.mynotebooknew.presentation.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.tolgakurucay.mynotebooknew.data.database.DataStoreManager
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookAppGraph
import com.tolgakurucay.mynotebooknew.presentation.Destinations
import com.tolgakurucay.mynotebooknew.presentation.theme.MyNotebookNewTheme
import com.tolgakurucay.mynotebooknew.util.AppLanguage
import com.tolgakurucay.mynotebooknew.util.setCurrentLanguage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    @Inject lateinit var dataStoreManager: DataStoreManager


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
        LaunchedEffect(
            key1 = dataStoreManager.getLanguageTag(),
            block = {
                dataStoreManager.getLanguageTag().onEach {
                    setCurrentLanguage(AppLanguage.valueOf(it.uppercase()))
                }.collect()
            },
        )

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

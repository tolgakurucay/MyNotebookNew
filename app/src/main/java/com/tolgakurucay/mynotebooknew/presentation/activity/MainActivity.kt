package com.tolgakurucay.mynotebooknew.presentation.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.tolgakurucay.mynotebooknew.data.database.DataStoreManager
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookAppGraph
import com.tolgakurucay.mynotebooknew.presentation.Destinations
import com.tolgakurucay.mynotebooknew.presentation.main.profile.light_dark_mode.ViewMode
import com.tolgakurucay.mynotebooknew.presentation.theme.MyNotebookNewTheme
import com.tolgakurucay.mynotebooknew.util.AppLanguage
import com.tolgakurucay.mynotebooknew.util.setCurrentLanguage
import com.tolgakurucay.mynotebooknew.util.setStateFalse
import com.tolgakurucay.mynotebooknew.util.setStateTrue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = mainViewModel.state.collectAsStateWithLifecycle()
            setup()
            observeState(state = state.value)
        }


    }

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
    }
}


@Composable
private fun observeState(
    state: MainState,
    onViewModeChanged: (ViewMode) -> Unit = {}
) {
    val viewModeChanged = remember { mutableStateOf<ViewMode?>(null) }
    if (state.isUserLoggedIn) {
        val darkTheme = if (state.isDarkMode == true || viewModeChanged.value == ViewMode.DARK) {
            true
        } else if (state.isDarkMode == false || viewModeChanged.value == ViewMode.LIGHT) {
            false
        } else {
            null
        }

        MyNotebookNewTheme(darkTheme = darkTheme) {
            MyNotebookAppGraph(
                startDestination = Destinations.HOME_ROUTE,
                onViewModeChanged = onViewModeChanged
            )
        }

    } else {
        MyNotebookNewTheme(darkTheme = (state.isDarkMode == true || viewModeChanged.value == ViewMode.DARK)) {
            MyNotebookAppGraph(
                startDestination = Destinations.LOGIN_ROUTE,
                onViewModeChanged = onViewModeChanged
            )
        }
    }
}



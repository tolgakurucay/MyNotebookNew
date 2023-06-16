package com.tolgakurucay.mynotebooknew

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowCompat
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.tolgakurucay.mynotebooknew.modules.MyNotebookAppGraph
import com.tolgakurucay.mynotebooknew.theme.MyNotebookNewTheme
import dagger.hilt.android.AndroidEntryPoint


/**
 * Main activity for the MyNotebookApp
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val displayFeatures = calculateDisplayFeatures(activity = this)

            MyNotebookNewTheme {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val notificationState =
                        rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)
                    SideEffect {
                        notificationState.launchPermissionRequest()
                    }
                }
                MyNotebookAppGraph(
                    windowSizeClass = windowSizeClass,
                    displayFeature = displayFeatures
                )

            }


        }
    }
}

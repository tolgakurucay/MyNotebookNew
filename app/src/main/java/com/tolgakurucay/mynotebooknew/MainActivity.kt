package com.tolgakurucay.mynotebooknew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.tolgakurucay.mynotebooknew.modules.MyNotebookAppGraph
import com.tolgakurucay.mynotebooknew.theme.MyNotebookNewTheme
import dagger.hilt.android.AndroidEntryPoint


/**
 * Main activity for the MyNotebookApp
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val displayFeatures = calculateDisplayFeatures(activity = this)

            MyNotebookNewTheme {
                MyNotebookAppGraph(
                    windowSizeClass = windowSizeClass,
                    displayFeature = displayFeatures
                )
            }
        }
    }
}

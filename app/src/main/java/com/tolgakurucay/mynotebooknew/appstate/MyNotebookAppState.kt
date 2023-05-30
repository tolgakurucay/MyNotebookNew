package com.tolgakurucay.mynotebooknew.appstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Profile : Screen("profile")

    object Player : Screen("player/{episodeUri}") {
        fun createRoute(episodeUri: String) = "player/$episodeUri"
    }
}


@Composable
fun rememberMyNotebookAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    MyNotebookAppState(navController, context)
}


class MyNotebookAppState(val navController: NavHostController, private val context: Context) {

    var isOnline by mutableStateOf(checkIfOnline())
        private set

    fun refreshOnline(){
        isOnline = checkIfOnline()
    }

    fun navigateToPlayer(episodeUri: String, from: NavBackStackEntry){
        // In order to discard duplicated navigation events, we check the Lifecycle
        if(from.lifecycleIsResumed()){
            val encodedUri = Uri.encode(episodeUri)
            navController.navigate(Screen.Player.createRoute(encodedUri))
        }
    }

    fun navigateBack() {
        navController.popBackStack()
    }


    private fun checkIfOnline(): Boolean {
        val cm = ContextCompat.getSystemService(context, ConnectivityManager::class.java)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = cm?.getNetworkCapabilities(cm.activeNetwork) ?: return false
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } else {
            cm?.activeNetworkInfo?.isConnectedOrConnecting == true
        }


    }
}


/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.getLifecycle().currentState == Lifecycle.State.RESUMED


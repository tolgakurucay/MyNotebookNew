package com.tolgakurucay.mynotebooknew.appstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat


@Composable
fun rememberMyNotebookAppState(
    context: Context = LocalContext.current
) = remember(context) {
    MyNotebookAppState(context)
}


class MyNotebookAppState(private val context: Context) {

    val isOnline = checkIfOnline()


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



package com.tolgakurucay.mynotebooknew.presentation

import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.DestinationsArgs.NOTE_ARG
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.FORGOT_PASSWORD_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.HOME_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.LOGIN_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.REGISTER_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.DestinationsArgs.USER_ID_ARG
import com.tolgakurucay.mynotebooknew.presentation.DestinationsArgs.USER_NAME_ARG
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.ADD_NOTE_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.CLOUD_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.EDIT_OR_VIEW_NOTE_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.FAVORITES_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.ScreenNames.PROFILE_SCREEN
import com.tolgakurucay.mynotebooknew.util.showLog
import com.tolgakurucay.mynotebooknew.util.toJson


/**
 * Screens used in [Destinations]
 */
private object ScreenNames {
    const val HOME_SCREEN = "home"
    const val LOGIN_SCREEN = "login"
    const val FORGOT_PASSWORD_SCREEN = "forgotPassword"
    const val REGISTER_SCREEN = "register"
    const val PROFILE_SCREEN = "profile"
    const val ADD_NOTE_SCREEN = "addNote"
    const val FAVORITES_SCREEN = "favorites"
    const val CLOUD_SCREEN = "cloud"
    const val EDIT_OR_VIEW_NOTE_SCREEN = "editOrViewNote"
}


/**
 * Arguments used in [Destinations] routes
 */
object DestinationsArgs {
    const val USER_ID_ARG = "userId"
    const val USER_NAME_ARG = "userName"
    const val NOTE_ARG = "noteArg"
    const val NOTE_MODEL_ARG = "noteModel"
}


/**
 * Destinations used in the [MainActivity]
 */

object Destinations {

    const val LOGIN_ROUTE = LOGIN_SCREEN
    const val FORGOT_PASSWORD_ROUTE = FORGOT_PASSWORD_SCREEN
    const val REGISTER_ROUTE = REGISTER_SCREEN
    const val HOME_ROUTE = HOME_SCREEN
    const val PROFILE_ROUTE = "$PROFILE_SCREEN/{$USER_ID_ARG}"
    const val TEST_ROUTE = "$HOME_SCREEN/{$USER_ID_ARG}/{$USER_NAME_ARG}"
    const val ADD_NOTE_ROUTE = ADD_NOTE_SCREEN
    const val FAVORITES_ROUTE = FAVORITES_SCREEN
    const val CLOUD_ROUTE = CLOUD_SCREEN
    const val EDIT_OR_VIEW_ROUTE = "$EDIT_OR_VIEW_NOTE_SCREEN/{$NOTE_ARG}"


}


class Actions(private val navController: NavHostController) {


    fun navigateToForgotPassword() {
        navController.navigate(Destinations.FORGOT_PASSWORD_ROUTE) {

        }
    }

    fun navigateToRegister() {
        navController.navigate(Destinations.REGISTER_ROUTE) {

        }
    }

    fun navigateToLogin(popUpRoute: String? = null) {
        navController.navigate(Destinations.LOGIN_ROUTE) {
            showLog(popUpRoute.toString())
            popUpRoute?.let { safeRoute ->
                this.popUpTo(safeRoute) {
                    inclusive = true
                }
            }
        }
    }

    fun navigateToHome() {
        navController.navigate(Destinations.HOME_ROUTE) {
            this.popUpTo(Destinations.ADD_NOTE_ROUTE) {
                inclusive = true
            }
        }
    }

    fun navigateToAddNote() {
        navController.navigate(Destinations.ADD_NOTE_ROUTE) {
            this.popUpTo(Destinations.ADD_NOTE_ROUTE) {
                inclusive = true
            }
        }
    }

    fun navigateToFavorites() {
        navController.navigate(Destinations.FAVORITES_ROUTE)
    }

    fun navigateToCloud() {
        navController.navigate(Destinations.CLOUD_ROUTE) {
            this.popUpTo(Destinations.REGISTER_ROUTE)
        }
    }

    fun navigateToProfile() {
        navController.navigate(Destinations.PROFILE_ROUTE)

    }

    fun navigateToEditOrView(noteModel: NoteModel, popUpRoute: String? = null) {
        val serializedModel = noteModel.toJson()
        navController.navigate("$EDIT_OR_VIEW_NOTE_SCREEN/$serializedModel") {
            popUpRoute?.let { safeRoute ->
                popUpTo(safeRoute) {
                    inclusive = true
                }
            }
        }
    }

    fun onBackPressed() {
        navController.navigateUp()
    }

}
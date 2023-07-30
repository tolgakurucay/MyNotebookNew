package com.tolgakurucay.mynotebooknew.presentation

import androidx.navigation.NavHostController
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.FORGOT_PASSWORD_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.HOME_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.LOGIN_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.REGISTER_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewDestinationsArgs.USER_ID_ARG
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewDestinationsArgs.USER_NAME_ARG
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.PROFILE_SCREEN


/**
 * Screens used in [MyNotebookNewDestinations]
 */
private object MyNotebookNewScreens {
    const val HOME_SCREEN = "home"
    const val LOGIN_SCREEN = "login"
    const val FORGOT_PASSWORD_SCREEN = "forgotPassword"
    const val REGISTER_SCREEN = "register"
    const val PROFILE_SCREEN = "profile"
}


/**
 * Arguments used in [MyNotebookNewDestinations] routes
 */
object MyNotebookNewDestinationsArgs {
    //    const val USER_MESSAGE_ARG = "userMessage"
//    const val TASK_ID_ARG = "taskId"
//    const val TITLE_ARG = "title"
    const val USER_ID_ARG = "userId"
    const val USER_NAME_ARG = "userName"
}


/**
 * Destinations used in the [MainActivity]
 */

object MyNotebookNewDestinations {

    const val LOGIN_ROUTE = LOGIN_SCREEN
    const val FORGOT_PASSWORD_ROUTE = FORGOT_PASSWORD_SCREEN
    const val REGISTER_ROUTE = REGISTER_SCREEN
    const val HOME_ROUTE = HOME_SCREEN
    const val PROFILE_ROUTE = "$PROFILE_SCREEN/{$USER_ID_ARG}"
    const val TEST_ROUTE ="$HOME_SCREEN/{$USER_ID_ARG}/{$USER_NAME_ARG}"


}


class MyNotebookNavigationActions(private val navController: NavHostController) {

    fun navigateToForgotPassword() {
        navController.navigate(MyNotebookNewDestinations.FORGOT_PASSWORD_ROUTE) {

        }
    }

    fun navigateToRegister() {
        navController.navigate(MyNotebookNewDestinations.REGISTER_ROUTE){

        }
    }

    fun navigateToLogin(){
        navController.navigate(MyNotebookNewDestinations.LOGIN_ROUTE){

        }
    }

    fun navigateToHome(){
        navController.navigate(MyNotebookNewDestinations.HOME_ROUTE)
    }

    fun navigateToTest(userId : Int, userName : String){
        navController.navigate("$HOME_SCREEN/$userId/$userName")

    }

}
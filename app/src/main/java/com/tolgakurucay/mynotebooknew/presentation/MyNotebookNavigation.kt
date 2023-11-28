package com.tolgakurucay.mynotebooknew.presentation

import androidx.navigation.NavHostController
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewDestinationsArgs.NOTE_ID_ARG
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.FORGOT_PASSWORD_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.HOME_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.LOGIN_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.REGISTER_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewDestinationsArgs.USER_ID_ARG
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewDestinationsArgs.USER_NAME_ARG
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.ADD_NOTE_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.CLOUD_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.EDIT_OR_VIEW_NOTE_SCREEN
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewScreens.FAVORITES_SCREEN
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
    const val ADD_NOTE_SCREEN = "addNote"
    const val FAVORITES_SCREEN = "favorites"
    const val CLOUD_SCREEN = "cloud"
    const val EDIT_OR_VIEW_NOTE_SCREEN = "editOrViewNote"
}


/**
 * Arguments used in [MyNotebookNewDestinations] routes
 */
object MyNotebookNewDestinationsArgs {
    const val USER_ID_ARG = "userId"
    const val USER_NAME_ARG = "userName"
    const val NOTE_ID_ARG = "noteId"
    const val NOTE_MODEL_ARG = "noteModel"
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
    const val ADD_NOTE_ROUTE = ADD_NOTE_SCREEN
    const val FAVORITES_ROUTE = FAVORITES_SCREEN
    const val CLOUD_ROUTE = CLOUD_SCREEN
    const val EDIT_OR_VIEW_ROUTE = "$EDIT_OR_VIEW_NOTE_SCREEN/$NOTE_ID_ARG"


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
        navController.navigate(MyNotebookNewDestinations.HOME_ROUTE){

        }


    }

    fun navigateToAddNote(){
        navController.navigate(MyNotebookNewDestinations.ADD_NOTE_ROUTE){
            this.popUpTo(MyNotebookNewDestinations.FAVORITES_ROUTE){
                inclusive = true
                saveState = true
            }

        }
    }

    fun navigateToFavorites(){
        navController.navigate(MyNotebookNewDestinations.FAVORITES_ROUTE){
            this.popUpTo(MyNotebookNewDestinations.HOME_ROUTE)

        }
    }

    fun navigateToCloud(){
        navController.navigate(MyNotebookNewDestinations.CLOUD_ROUTE){
            this.popUpTo(MyNotebookNewDestinations.REGISTER_ROUTE)

        }

    }
    fun navigateToProfile(){
        navController.navigate(MyNotebookNewDestinations.PROFILE_ROUTE)
    }

    fun navigateToTest(userId : Int, userName : String){
        navController.navigate("$HOME_SCREEN/$userId/$userName")
//        navController.navigate(MyNotebookNewDestinations.TEST_ROUTE)

    }

    fun navigateToEditOrView(noteModel: NoteModel){
        navController.navigate("$EDIT_OR_VIEW_NOTE_SCREEN/$noteModel")
    }

    fun onBackPressed(){
        navController.navigateUp()
    }

}
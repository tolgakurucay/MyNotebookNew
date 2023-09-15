package com.tolgakurucay.mynotebooknew.presentation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.main.add_note.AddNotePage
import com.tolgakurucay.mynotebooknew.presentation.main.cloud.CloudPage
import com.tolgakurucay.mynotebooknew.presentation.main.favorites.FavoritesPage
import com.tolgakurucay.mynotebooknew.presentation.auth.forgot_password.ForgotPasswordPage
import com.tolgakurucay.mynotebooknew.presentation.main.home.Home
import com.tolgakurucay.mynotebooknew.presentation.main.home.HomeNavigations
import com.tolgakurucay.mynotebooknew.presentation.auth.login.Login
import com.tolgakurucay.mynotebooknew.presentation.main.profile.ProfilePage
import com.tolgakurucay.mynotebooknew.presentation.auth.register.Register
import com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note.EditOrViewNotePage

@Composable
fun MyNotebookAppGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MyNotebookNewDestinations.LOGIN_ROUTE,
    navActions: MyNotebookNavigationActions = remember(navController) {
        MyNotebookNavigationActions(navController)
    }

) {

    //Use this when you use drawer
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            MyNotebookNewDestinations.HOME_ROUTE,
        ) {
            Home(
                homeNavigations = { homeNavigation ->
                    when (homeNavigation) {
                        HomeNavigations.FAVORITES -> {
                            navActions.navigateToFavorites()
                        }

                        HomeNavigations.CLOUD -> {
                            navActions.navigateToCloud()
                        }

                        HomeNavigations.PROFILE -> {
                            navActions.navigateToProfile()
                        }

                        HomeNavigations.ADD_NOTE -> {
                            navActions.navigateToAddNote()
                        }

                        else -> {}

                    }
                },
                loggedOut = {
                    navActions.navigateToLogin()
                },
                gotToEditOrView = {
                    navActions.navigateToEditOrView(it)
                }
            )
        }

        composable(MyNotebookNewDestinations.PROFILE_ROUTE) {
            ProfilePage()
        }

        composable(MyNotebookNewDestinations.LOGIN_ROUTE) {
            Login(
                onNavigateToForgotPasswordMain = {
                    navActions.navigateToForgotPassword()
                },
                onNavigateToRegisterMain = {
                    navActions.navigateToRegister()
                },
                onNavigateToHome = {
                    navActions.navigateToHome()
                }
            )
        }

        composable(MyNotebookNewDestinations.FORGOT_PASSWORD_ROUTE) {
            ForgotPasswordPage(
                onNavigateToLogin = {
                    navActions.navigateToLogin()
                },
            )
        }

        composable(MyNotebookNewDestinations.REGISTER_ROUTE) {
            Register(
                onNavigateToLogin = {
                    navActions.navigateToLogin()
                }
            )
        }

        composable(MyNotebookNewDestinations.FAVORITES_ROUTE) {
            FavoritesPage()
        }

        composable(MyNotebookNewDestinations.ADD_NOTE_ROUTE) {
            AddNotePage(
                onBackPressed = { navActions.onBackPressed() },
                goToHome = {
                    navActions.navigateToHome()
                },
            )
        }
        composable(MyNotebookNewDestinations.CLOUD_ROUTE) {
            CloudPage()
        }

        composable(MyNotebookNewDestinations.EDIT_OR_VIEW_ROUTE) {
          if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
              it.arguments?.getParcelable(MyNotebookNewDestinationsArgs.NOTE_MODEL_ARG,NoteModel::class.java)
              
          }
            else{
              // TODO: convert this parceable to noteId and insert to the repository and create usecase 
          }
            /*it.arguments?.getParcelable(MyNotebookNewDestinationsArgs.NOTE_ID_ARG)?.let {
                EditOrViewNotePage(
                    noteModel = ,
                    onBackPressed = {
                        navController.navigateUp()
                    },
                    goToHome = { navActions.navigateToHome() },
                )

            }*/

        }


    }


}
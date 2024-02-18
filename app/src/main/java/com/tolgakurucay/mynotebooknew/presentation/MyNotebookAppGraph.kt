package com.tolgakurucay.mynotebooknew.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.main.add_note.AddNotePage
import com.tolgakurucay.mynotebooknew.presentation.main.cloud.CloudPage
import com.tolgakurucay.mynotebooknew.presentation.main.favorites.FavoritesPage
import com.tolgakurucay.mynotebooknew.presentation.auth.forgot_password.ForgotPasswordPage
import com.tolgakurucay.mynotebooknew.presentation.main.home.HomePage
import com.tolgakurucay.mynotebooknew.presentation.main.home.HomeNavigations
import com.tolgakurucay.mynotebooknew.presentation.auth.login.LoginPage
import com.tolgakurucay.mynotebooknew.presentation.main.profile.main.ProfilePage
import com.tolgakurucay.mynotebooknew.presentation.auth.register.Register
import com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note.EditOrViewNotePage
import com.tolgakurucay.mynotebooknew.presentation.main.profile.light_dark_mode.ViewMode
import com.tolgakurucay.mynotebooknew.util.slideLeftEnter
import com.tolgakurucay.mynotebooknew.util.slideRightExit


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNotebookAppGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME_ROUTE,
    onViewModeChanged: (viewMode: ViewMode) -> Unit = {},
    navActions: Actions = remember(navController) {
        Actions(navController)
    }

) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {


        composable(
            Destinations.HOME_ROUTE,
        ) {
            HomePage(
                homeNavigations = { homeNavigation ->
                    when (homeNavigation) {
                        HomeNavigations.FAVORITES -> navActions.navigateToFavorites()
                        HomeNavigations.CLOUD -> navActions.navigateToCloud()
                        HomeNavigations.PROFILE -> navActions.navigateToProfile()
                        HomeNavigations.ADD_NOTE -> navActions.navigateToAddNote()
                        else -> {}
                    }
                },
                onLogOutClicked = {
                    navActions.navigateToLogin(popUpRoute = Destinations.HOME_ROUTE)
                },
                onNoteItemClicked = {
                    navActions.navigateToEditOrView(it, Destinations.HOME_ROUTE)
                }
            )

        }

        composable(Destinations.PROFILE_ROUTE,
            enterTransition = { slideLeftEnter() },
            exitTransition = { slideRightExit() }) {
            ProfilePage(
                onBackPressed = {
                    navActions.onBackPressed()
                },
                onViewModeChanged = onViewModeChanged
            )
        }

        composable(Destinations.LOGIN_ROUTE,
            enterTransition = { slideLeftEnter() },
            exitTransition = { slideRightExit() }) {
            LoginPage(
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

        composable(
            Destinations.FORGOT_PASSWORD_ROUTE,
            enterTransition = { slideLeftEnter() },
            exitTransition = { slideRightExit() }

        ) {
            ForgotPasswordPage(
                onNavigateToLogin = {
                    navActions.navigateToLogin()
                },
            )
        }

        composable(
            Destinations.REGISTER_ROUTE,
            enterTransition = { slideLeftEnter() },
            exitTransition = { slideRightExit() }) {
            Register(
                onNavigateToLogin = {
                    navActions.navigateToLogin()
                }
            )
        }

        composable(Destinations.FAVORITES_ROUTE,
            enterTransition = { slideLeftEnter() },
            exitTransition = { slideRightExit() }) {
            FavoritesPage(
                onBackPressed = {
                    navActions.onBackPressed()
                },
                onNoteItemClicked = {
                    navActions.navigateToEditOrView(it)
                }
            )
        }

        composable(Destinations.ADD_NOTE_ROUTE,
            enterTransition = { slideLeftEnter() },
            exitTransition = { slideRightExit() }) {

            AddNotePage(
                onBackPressed = { navActions.onBackPressed() },
                goToHome = {
                    navActions.navigateToHome()
                },
            )
        }
        composable(Destinations.CLOUD_ROUTE,
            enterTransition = { slideLeftEnter() },
            exitTransition = { slideRightExit() }) {
            CloudPage(onNoteItemClicked = {
                navActions.navigateToEditOrView(it)
            })
        }

        composable(
            Destinations.EDIT_OR_VIEW_ROUTE,
            enterTransition = { slideLeftEnter() },
            exitTransition = { slideRightExit() },
            arguments = listOf(
                navArgument(DestinationsArgs.NOTE_ARG) {
                    this.type = NavType.StringType
                }
            ),
        ) {

            it.arguments?.getString(DestinationsArgs.NOTE_ARG)?.let { noteModelJson ->
                val model = Gson().fromJson(noteModelJson, NoteModel::class.java)
                EditOrViewNotePage(
                    noteModel = model,
                    onBackPressed = {
                        navActions.onBackPressed()
                    },
                )

            }


        }


    }


}
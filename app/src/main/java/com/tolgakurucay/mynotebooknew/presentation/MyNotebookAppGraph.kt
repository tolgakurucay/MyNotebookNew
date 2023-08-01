package com.tolgakurucay.mynotebooknew.presentation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.window.layout.DisplayFeature
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewDestinationsArgs.USER_ID_ARG
import com.tolgakurucay.mynotebooknew.presentation.MyNotebookNewDestinationsArgs.USER_NAME_ARG
import com.tolgakurucay.mynotebooknew.presentation.add_note.AddNotePage
import com.tolgakurucay.mynotebooknew.presentation.cloud.CloudPage
import com.tolgakurucay.mynotebooknew.presentation.favorites.FavoritesPage
import com.tolgakurucay.mynotebooknew.presentation.forgot_password.ForgotPassword
import com.tolgakurucay.mynotebooknew.presentation.home.Home
import com.tolgakurucay.mynotebooknew.presentation.home.HomeNavigations
import com.tolgakurucay.mynotebooknew.presentation.login.Login
import com.tolgakurucay.mynotebooknew.presentation.profile.ProfilePage
import com.tolgakurucay.mynotebooknew.presentation.register.Register

@Composable
fun MyNotebookAppGraph(
    windowSizeClass: WindowSizeClass,
    displayFeature: List<DisplayFeature>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MyNotebookNewDestinations.HOME_ROUTE,
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
                        HomeNavigations.FAVORITES -> { navActions.navigateToFavorites() }
                        HomeNavigations.CLOUD -> { navActions.navigateToCloud() }
                        HomeNavigations.PROFILE -> { navActions.navigateToProfile() }
                        HomeNavigations.LOGOUT -> {  }
                        HomeNavigations.ADD_NOTE -> { navActions.navigateToAddNote() }

                    }
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
            ForgotPassword(
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

        composable(MyNotebookNewDestinations.FAVORITES_ROUTE){
            FavoritesPage()
        }

        composable(MyNotebookNewDestinations.ADD_NOTE_ROUTE){
            AddNotePage()
        }
        composable(MyNotebookNewDestinations.CLOUD_ROUTE){
            CloudPage()
        }


    }


}
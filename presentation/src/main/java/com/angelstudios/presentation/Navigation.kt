package com.angelstudios.presentation

import LoginScreen
import MainScreen
import SignInScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelstudios.presentation.forgetPassword.ForgotPassword


@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.LoginScreen.route) {

        composable(route= ScreenRoutes.LoginScreen.route){
            LoginScreen(navController)
        }

        composable(route= ScreenRoutes.SignInScreen.route){
            SignInScreen(navController)
        }

        composable(route= ScreenRoutes.MainScreen.route){
            MainScreen(navController)
        }

        composable(route = ScreenRoutes.ForgetPassword.route) {
            ForgotPassword(navController = navController)
        }
    }

}

sealed class ScreenRoutes(val route: String) {
    object LoginScreen : ScreenRoutes("login")
    object SignInScreen : ScreenRoutes("sign-in")
    object MainScreen : ScreenRoutes("main")
    object ForgetPassword : ScreenRoutes("forget-password")

}
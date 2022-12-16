package com.angelstudios.presentation

import LoginScreen
import MainScreen
import SignInScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelstudios.presentation.forgetPassword.ForgotPassword
import com.angelstudios.presentation.loginScreen.LoginViewModel
import com.angelstudios.presentation.signInScreen.SignInViewModel


@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.LoginScreen.route) {

        composable(route = ScreenRoutes.LoginScreen.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController, loginViewModel)
        }

        composable(route = ScreenRoutes.SignInScreen.route) {
            val signInViewModel = hiltViewModel<SignInViewModel>()
            SignInScreen(navController, signInViewModel)
        }

        composable(route = ScreenRoutes.MainScreen.route) {
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
package com.angelstudios.presentation.loginScreen

sealed class NavigationEvent {
    object ToMain : NavigationEvent()
    object Skip : NavigationEvent()
    object ToForgetPasswordScreen : NavigationEvent()
    object ToSignInScreen : NavigationEvent()

}
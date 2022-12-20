package com.angelstudios.presentation.loginScreen


sealed class LoginScreenEvent {
    data class EmailChanged(val email: String) : LoginScreenEvent()
    data class PasswordChanged(val password: String) : LoginScreenEvent()
    object Submit : LoginScreenEvent()
    object Skip : LoginScreenEvent()
    object GoToSignInScreen : LoginScreenEvent()
    object GoToForgetPasswordScreen : LoginScreenEvent()
}

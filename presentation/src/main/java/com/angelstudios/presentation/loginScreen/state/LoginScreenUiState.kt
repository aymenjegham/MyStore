package com.angelstudios.presentation.loginScreen.state

import java.io.Serializable


data class LoginScreenUiState(
    val email : String  = "",
    val emailError: Int? = null,
    val password : String = "",
    val passwordError : Int ? = null,
    val apiError : Int ? = null,
    val showLoader : Boolean = false,
) : Serializable

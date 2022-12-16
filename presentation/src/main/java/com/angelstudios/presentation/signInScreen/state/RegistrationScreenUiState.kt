package com.angelstudios.presentation.signInScreen.state

import java.io.Serializable


data class RegistrationScreenUiState(
    val email : String  = "",
    val emailError: Int? = null,
    val password : String = "",
    val passwordError : Int ? = null,
    val repeatedPassword : String = "",
    val repeatedPasswordError : Int ? = null,
    val apiError : Int ? = null,
    val showLoader : Boolean = false,

) : Serializable

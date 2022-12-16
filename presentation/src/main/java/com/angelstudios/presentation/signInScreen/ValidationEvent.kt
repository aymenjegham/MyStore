package com.angelstudios.presentation.signInScreen

sealed class ValidationEvent {
    object Success : ValidationEvent()
}
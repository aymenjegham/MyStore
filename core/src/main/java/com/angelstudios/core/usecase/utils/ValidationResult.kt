package com.angelstudios.core.usecase.utils


data class ValidationResult(
    val success :Boolean,
    val formErrorMessage : FormError? = null
)



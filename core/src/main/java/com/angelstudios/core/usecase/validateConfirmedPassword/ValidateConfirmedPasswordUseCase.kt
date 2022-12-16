package com.angelstudios.core.usecase.validateConfirmedPassword

import com.angelstudios.core.usecase.utils.ValidationResult


interface ValidateConfirmedPasswordUseCase {

    operator fun invoke(
        password : String,
        confirmedPassword :String
    ): ValidationResult
}

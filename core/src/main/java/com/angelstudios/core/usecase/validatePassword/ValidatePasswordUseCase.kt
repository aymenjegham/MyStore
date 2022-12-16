package com.angelstudios.core.usecase.validatePassword

import com.angelstudios.core.usecase.utils.ValidationResult


interface ValidatePasswordUseCase {

    operator fun invoke(
        password : String
    ): ValidationResult
}

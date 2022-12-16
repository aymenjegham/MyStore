package com.angelstudios.core.usecase.validateEmail

import com.angelstudios.core.usecase.utils.ValidationResult


interface ValidateEmailUseCase {

    operator fun invoke(
        email : String
    ): ValidationResult
}

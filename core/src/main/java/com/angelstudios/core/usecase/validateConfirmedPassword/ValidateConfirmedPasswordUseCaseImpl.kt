package com.angelstudios.core.usecase.validateConfirmedPassword

import com.angelstudios.core.usecase.utils.FormError
import com.angelstudios.core.usecase.utils.ValidationResult
import javax.inject.Inject

class ValidateConfirmedPasswordUseCaseImpl @Inject constructor(
) : ValidateConfirmedPasswordUseCase {
    override fun invoke(password: String, confirmedPassword: String): ValidationResult {

        return when {

            password != confirmedPassword -> {
                ValidationResult(success = false, formErrorMessage = FormError.PasswordNotMatch)
            }
            else -> {
                return ValidationResult(success = true)
            }
        }

    }


}
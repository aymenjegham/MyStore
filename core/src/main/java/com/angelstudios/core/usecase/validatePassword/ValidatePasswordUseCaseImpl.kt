package com.angelstudios.core.usecase.validatePassword

import com.angelstudios.core.usecase.utils.FormError
import com.angelstudios.core.usecase.utils.ValidationResult
import javax.inject.Inject

class ValidatePasswordUseCaseImpl @Inject constructor(
) : ValidatePasswordUseCase {

    override fun invoke(password: String): ValidationResult {

        val containsLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }

        return when {
            password.isBlank() -> {
                ValidationResult(
                    success = false,
                    formErrorMessage = FormError.EmptyPassword)
            }

            password.length < PASSWORD_MINIMUM_LENGTH -> {
                ValidationResult(
                    success = false,
                    formErrorMessage = FormError.LessThanEightCharactersPassword)
            }

            !containsLettersAndDigits -> {
                ValidationResult(
                    success = false,
                    formErrorMessage = FormError.PasswordMustContainsOneDigitOneLetter)
            }

            else -> {
                ValidationResult(success = true)
            }
        }


    }

    companion object{
        private const val PASSWORD_MINIMUM_LENGTH = 8
    }
}
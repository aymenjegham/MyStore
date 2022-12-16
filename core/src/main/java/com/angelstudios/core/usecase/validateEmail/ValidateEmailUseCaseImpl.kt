package com.angelstudios.core.usecase.validateEmail


import com.angelstudios.core.data.dataSource.helper.EmailMatcher
import com.angelstudios.core.usecase.utils.FormError
import com.angelstudios.core.usecase.utils.ValidationResult
import javax.inject.Inject

class ValidateEmailUseCaseImpl @Inject constructor(
    private val emailMatcher: EmailMatcher,
) : ValidateEmailUseCase {
    override fun invoke(email: String): ValidationResult {

        return when {
            email.isBlank() -> {
                ValidationResult(success = false,
                    formErrorMessage = FormError.EmptyEmail
                )
            }
            !emailMatcher.matches(email) -> {
                ValidationResult(success = false, formErrorMessage = FormError.EmailBadlyFormatted)
            }

            else -> {
                ValidationResult(success = true)
            }
        }
    }


}
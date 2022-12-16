package com.angelstudios.core.usecase.utils


sealed class FormError{
    object EmptyEmail : FormError()
    object EmailBadlyFormatted : FormError()
    object LessThanEightCharactersPassword : FormError()
    object EmptyPassword : FormError()
    object PasswordNotMatch : FormError()
    object PasswordMustContainsOneDigitOneLetter : FormError()
}

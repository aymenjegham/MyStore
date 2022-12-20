package com.angelstudios.presentation.signInScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.angelstudios.core.domain.network.NetworkResult
import com.angelstudios.core.usecase.registeruserWithEmailAndPassword.RegisterUserWithEmailAndPasswordUseCase
import com.angelstudios.core.usecase.utils.FormError
import com.angelstudios.core.usecase.validateConfirmedPassword.ValidateConfirmedPasswordUseCase
import com.angelstudios.core.usecase.validateEmail.ValidateEmailUseCase
import com.angelstudios.core.usecase.validatePassword.ValidatePasswordUseCase
import com.angelstudios.presentation.R
import com.angelstudios.presentation.authErrors
import com.angelstudios.presentation.signInScreen.state.RegistrationScreenUiState
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val registerUserWithEmailAndPassword: RegisterUserWithEmailAndPasswordUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateConfirmedPasswordUseCase: ValidateConfirmedPasswordUseCase,
    private val firebaseCrashlytics: FirebaseCrashlytics,
) : ViewModel() {

    @OptIn(SavedStateHandleSaveableApi::class)
    private var _registrationScreenUiState by savedStateHandle.saveable {
        mutableStateOf(RegistrationScreenUiState())
    }
    val registrationScreenUiState
        get() = _registrationScreenUiState


    private val validateEventChannel = Channel<ValidationEvent>()
    val validationEvent = validateEventChannel.receiveAsFlow()


    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                _registrationScreenUiState = _registrationScreenUiState.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                _registrationScreenUiState =
                    _registrationScreenUiState.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                _registrationScreenUiState =
                    _registrationScreenUiState.copy(repeatedPassword = event.repeatedPassword)
            }
            RegistrationFormEvent.Submit -> {
                submitData()

            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmailUseCase(registrationScreenUiState.email)
        val passwordResult = validatePasswordUseCase(registrationScreenUiState.password)
        val repeatedPasswordResult =
            validateConfirmedPasswordUseCase(
                registrationScreenUiState.password,
                registrationScreenUiState.repeatedPassword)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult
        ).any {
            !it.success
        }

        if (hasError) {
            val emailError = when (emailResult.formErrorMessage) {
                is FormError.EmptyEmail -> R.string.email_cant_be_blank
                is FormError.EmailBadlyFormatted -> R.string.email_not_valid
                else -> null
            }

            val passwordError = when (passwordResult.formErrorMessage) {
                is FormError.EmptyPassword -> R.string.password_cant_be_blank
                is FormError.LessThanEightCharactersPassword -> R.string.password_cant_be_less_than_8
                is FormError.PasswordMustContainsOneDigitOneLetter -> R.string.password_should_have_atleast_oneletter_onedigit
                else -> null
            }

            val confirmedPasswordError = when (repeatedPasswordResult.formErrorMessage) {
                is FormError.PasswordNotMatch -> R.string.password_dont_match
                else -> null
            }

            _registrationScreenUiState = _registrationScreenUiState.copy(
                emailError = emailError,
                passwordError = passwordError,
                repeatedPasswordError = confirmedPasswordError
            )
            return
        }

        viewModelScope.launch {
            clearErrors()
            registerUser(registrationScreenUiState.email, registrationScreenUiState.password)
        }

    }

    private fun clearErrors() {
        _registrationScreenUiState = _registrationScreenUiState.copy(
            emailError = null,
            passwordError = null,
            repeatedPasswordError = null,
            apiError = null
        )
    }


    private fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registerUserWithEmailAndPassword(
                email,
                password
            ).collectLatest { response ->
                when (response) {
                    is NetworkResult.Success<*> -> {
                        response.data?.let {
                            withContext(Dispatchers.Main.immediate) {
                                validateEventChannel.send(ValidationEvent.Success)
                            }
                            firebaseCrashlytics
                                .setCustomKey("userEmail", it as String)

                        }
                    }
                    is NetworkResult.Error -> {
                        response.message?.let {
                            (authErrors[it] ?: R.string.error_login_default_error)
                                .let { errorMessage ->
                                    _registrationScreenUiState =
                                        _registrationScreenUiState.copy(
                                            apiError = errorMessage,
                                            showLoader = false
                                        )
                                }
                        }
                    }
                    is NetworkResult.Loading -> {
                        _registrationScreenUiState =
                            _registrationScreenUiState.copy(showLoader = true)
                    }
                }
            }
        }
    }
}
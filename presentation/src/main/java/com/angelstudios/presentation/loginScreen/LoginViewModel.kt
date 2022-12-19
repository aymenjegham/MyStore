package com.angelstudios.presentation.loginScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.angelstudios.core.domain.network.NetworkResult
import com.angelstudios.core.usecase.loginUserWithEmailAndPassword.LoginUserWithEmailAndPasswordUseCase
import com.angelstudios.core.usecase.utils.FormError
import com.angelstudios.core.usecase.utils.ValidationResult
import com.angelstudios.core.usecase.validateEmail.ValidateEmailUseCase
import com.angelstudios.presentation.R
import com.angelstudios.presentation.authErrors
import com.angelstudios.presentation.loginScreen.state.LoginScreenUiState
import com.angelstudios.presentation.signInScreen.ValidationEvent
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
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val loginUserWithEmailAndPasswordUseCase: LoginUserWithEmailAndPasswordUseCase,
    private val firebaseCrashlytics: FirebaseCrashlytics,

    ) : ViewModel() {

    @OptIn(SavedStateHandleSaveableApi::class)
    private var _loginUiState by savedStateHandle.saveable {
        mutableStateOf(LoginScreenUiState())
    }
    val loginUiState
        get() = _loginUiState

    private val _navigationEventChannel = Channel<NavigationEvent>()
    val navigationEventChannel = _navigationEventChannel.receiveAsFlow()

    private val validateEventChannel = Channel<ValidationEvent>()
    val validationEvent = validateEventChannel.receiveAsFlow()


    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.EmailChanged -> {
                _loginUiState = _loginUiState.copy(email = event.email)
            }
            is LoginScreenEvent.PasswordChanged -> {
                _loginUiState = _loginUiState.copy(password = event.password)
            }
            LoginScreenEvent.GoToForgetPasswordScreen -> {
                navigateTo(NavigationEvent.ToForgetPasswordScreen)
            }
            LoginScreenEvent.GoToSignInScreen -> {
                navigateTo(NavigationEvent.ToSignInScreen)
            }
            LoginScreenEvent.Skip -> {
                navigateTo(NavigationEvent.Skip)
            }
            LoginScreenEvent.Submit -> {
                login()
            }
        }
    }

    private fun login() {
        val emailResult = validateEmailUseCase(loginUiState.email)
        val passwordResult = when {
            loginUiState.password.isBlank() -> {
                ValidationResult(
                    success = false,
                    formErrorMessage = FormError.EmptyPassword)
            }
            else -> {
                ValidationResult(success = true)
            }
        }


        val hasError = listOf(
            emailResult,
            passwordResult
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
                else -> null
            }

            _loginUiState = _loginUiState.copy(
                emailError = emailError,
                passwordError = passwordError
            )
            return
        }

        viewModelScope.launch {
            clearErrors()
            loginUser(loginUiState.email, loginUiState.password)
        }

    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUserWithEmailAndPasswordUseCase(email, password)
                .collectLatest { response ->
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
                                        _loginUiState =
                                            _loginUiState.copy(
                                                apiError = errorMessage,
                                                showLoader = false
                                            )
                                    }
                            }
                        }
                        is NetworkResult.Loading -> {
                            _loginUiState =
                                _loginUiState.copy(showLoader = true)
                        }
                    }
                }
        }

    }

    private fun clearErrors() {
        _loginUiState = _loginUiState.copy(
            emailError = null,
            passwordError = null,
            apiError = null
        )
    }

    private fun navigateTo(destination: NavigationEvent) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _navigationEventChannel.send(destination)
        }
    }
}
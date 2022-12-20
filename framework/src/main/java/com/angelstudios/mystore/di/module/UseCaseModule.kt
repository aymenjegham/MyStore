package com.angelstudios.mystore.di.module

import com.angelstudios.core.usecase.loginUserWithEmailAndPassword.LoginUserWithEmailAndPasswordUseCase
import com.angelstudios.core.usecase.loginUserWithEmailAndPassword.LoginUserWithEmailAndPasswordUseCaseImpl
import com.angelstudios.core.usecase.registeruserWithEmailAndPassword.RegisterUserWithEmailAndPasswordUseCase
import com.angelstudios.core.usecase.registeruserWithEmailAndPassword.RegisterUserWithEmailAndPasswordUseCaseImpl
import com.angelstudios.core.usecase.validateConfirmedPassword.ValidateConfirmedPasswordUseCase
import com.angelstudios.core.usecase.validateConfirmedPassword.ValidateConfirmedPasswordUseCaseImpl
import com.angelstudios.core.usecase.validateEmail.ValidateEmailUseCase
import com.angelstudios.core.usecase.validateEmail.ValidateEmailUseCaseImpl
import com.angelstudios.core.usecase.validatePassword.ValidatePasswordUseCase
import com.angelstudios.core.usecase.validatePassword.ValidatePasswordUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindsRegisterUser(registerFirebaseUser: RegisterUserWithEmailAndPasswordUseCaseImpl): RegisterUserWithEmailAndPasswordUseCase

    @Singleton
    @Binds
    abstract fun bindsLoginUser(loginUserWithEmailAndPasswordUseCaseImpl: LoginUserWithEmailAndPasswordUseCaseImpl): LoginUserWithEmailAndPasswordUseCase

    @Singleton
    @Binds
    abstract fun bindsValidateEmail(validateEmailUseCaseImpl: ValidateEmailUseCaseImpl): ValidateEmailUseCase

    @Singleton
    @Binds
    abstract fun bindsValidatePassword(validatePasswordUseCaseImpl: ValidatePasswordUseCaseImpl): ValidatePasswordUseCase

    @Singleton
    @Binds
    abstract fun bindsConfirmedValidatePassword(validatedConfirmedPasswordUseCaseImpl: ValidateConfirmedPasswordUseCaseImpl): ValidateConfirmedPasswordUseCase
}
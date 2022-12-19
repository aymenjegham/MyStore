package com.angelstudios.core.usecase.loginUserWithEmailAndPassword

import com.angelstudios.core.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface LoginUserWithEmailAndPasswordUseCase {

    operator fun invoke(
        userName: String,
        password: String,
    ): Flow<NetworkResult<Any>>
}

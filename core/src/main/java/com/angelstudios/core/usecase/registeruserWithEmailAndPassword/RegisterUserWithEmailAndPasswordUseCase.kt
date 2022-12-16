package com.angelstudios.core.usecase.registeruserWithEmailAndPassword

import com.angelstudios.core.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RegisterUserWithEmailAndPasswordUseCase {

    operator fun invoke(
        userName: String,
        password: String,
    ): Flow<NetworkResult<Any>>
}

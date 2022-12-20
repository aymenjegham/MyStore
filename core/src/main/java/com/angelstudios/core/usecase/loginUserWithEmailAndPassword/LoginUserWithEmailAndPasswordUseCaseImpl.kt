package com.angelstudios.core.usecase.loginUserWithEmailAndPassword

import com.angelstudios.core.data.repository.user.UserRepository
import com.angelstudios.core.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserWithEmailAndPasswordUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : LoginUserWithEmailAndPasswordUseCase {

    override  fun invoke(userName: String, password: String): Flow<NetworkResult<Any>> {
        return userRepository.loginUserWithEmailAndPassword(userName, password)
    }
}
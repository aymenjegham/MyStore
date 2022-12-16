package com.angelstudios.core.usecase.registeruserWithEmailAndPassword

import com.angelstudios.core.data.repository.user.UserRepository
import com.angelstudios.core.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUserWithEmailAndPasswordUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : RegisterUserWithEmailAndPasswordUseCase {

    override  fun invoke(userName: String, password: String): Flow<NetworkResult<Any>> {
        return userRepository.registerUserWithEmailAndPassword(userName, password)
    }
}
package com.angelstudios.core.data.repository.user

import com.angelstudios.core.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

     fun registerUserWithEmailAndPassword(userName :String, password :String ) : Flow<NetworkResult<Any>>

     fun loginUserWithEmailAndPassword(email :String, password :String ) : Flow<NetworkResult<Any>>

}
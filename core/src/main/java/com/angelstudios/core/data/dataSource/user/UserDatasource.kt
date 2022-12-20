package com.angelstudios.core.data.dataSource.user

import com.angelstudios.core.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow


interface UserDatasource {
    fun registerUserWithEmailAndPassword(email: String, password: String) : Flow<NetworkResult<Any>>

    fun loginUserWithEmailAndPassword(email: String, password: String) : Flow<NetworkResult<Any>>

}


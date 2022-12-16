package com.angelstudios.core.data.dataSource.user

import com.angelstudios.core.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow


interface UserDatasource {
    fun registerUserWithEmailAndPassword(userName: String, password: String) : Flow<NetworkResult<Any>>
}


package com.angelstudios.core.data.repository.user

import com.angelstudios.core.data.dataSource.user.UserDatasource
import com.angelstudios.core.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val datasource: UserDatasource,
) : UserRepository {
    override  fun registerUserWithEmailAndPassword(userName: String, password: String): Flow<NetworkResult<Any>> {
        return datasource.registerUserWithEmailAndPassword(userName, password)
    }

    override fun loginUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<NetworkResult<Any>> {
        return datasource.loginUserWithEmailAndPassword(email,password)
    }
}
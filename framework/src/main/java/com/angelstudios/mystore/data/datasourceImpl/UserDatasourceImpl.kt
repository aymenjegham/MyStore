package com.angelstudios.mystore.data.datasourceImpl

import com.angelstudios.core.data.dataSource.user.UserDatasource
import com.angelstudios.core.domain.network.NetworkResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserDatasourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : UserDatasource {

    override fun registerUserWithEmailAndPassword(
        userName: String,
        password: String,
    ): Flow<NetworkResult<Any>> = callbackFlow {
        
        trySend(NetworkResult.Loading())

        auth.createUserWithEmailAndPassword(userName, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(NetworkResult.Success(task.result.user!!))
                    close()
                }
            }
            .addOnFailureListener { exception ->
                exception as FirebaseAuthException

                trySend(NetworkResult.Error(message = exception.errorCode))
                close()
            }
        awaitClose()
    }
}

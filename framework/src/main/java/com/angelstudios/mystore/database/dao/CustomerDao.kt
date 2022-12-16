package com.angelstudios.mystore.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.angelstudios.core.domain.user.User
import com.angelstudios.mystore.Entity.user.UserEntity

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customerEntity: UserEntity)

    @Query("SELECT * FROM customer")
    suspend fun getAllCustomers(): List<User>
}
package com.angelstudios.mystore.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.angelstudios.core.domain.customer.Customer
import com.angelstudios.mystore.domain.customer.CustomerEntity

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customerEntity: CustomerEntity)

    @Query("SELECT * FROM customer")
    suspend fun getAllCustomers(): List<Customer>
}
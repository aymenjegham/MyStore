package com.angelstudios.mystore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.angelstudios.mystore.database.dao.CustomerDao
import com.angelstudios.mystore.database.dao.ProductDao
import com.angelstudios.mystore.domain.customer.CustomerEntity
import com.angelstudios.mystore.domain.product.ProductEntity


const val DATABASE_NAME = "mystore_db"
const val DATABASE_VERSION = 3

@Database(
    entities = [ProductEntity::class, CustomerEntity::class],
    version = DATABASE_VERSION,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    abstract fun productDao(): ProductDao
}
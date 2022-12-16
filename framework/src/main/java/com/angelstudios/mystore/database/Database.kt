package com.angelstudios.mystore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.angelstudios.mystore.Entity.product.ProductEntity
import com.angelstudios.mystore.Entity.user.UserEntity
import com.angelstudios.mystore.database.dao.CustomerDao
import com.angelstudios.mystore.database.dao.ProductDao


const val DATABASE_NAME = "mystore_db"
const val DATABASE_VERSION = 3

@Database(
    entities = [ProductEntity::class, UserEntity::class],
    version = DATABASE_VERSION,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    abstract fun productDao(): ProductDao
}
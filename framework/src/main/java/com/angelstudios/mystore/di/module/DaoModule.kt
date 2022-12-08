package com.angelstudios.mystore.di.module

import com.angelstudios.mystore.database.Database
import com.angelstudios.mystore.database.dao.CustomerDao
import com.angelstudios.mystore.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DaoModule {


    @Provides
    @Singleton
    fun provideProductDao(database: Database): ProductDao = database.productDao()

    @Provides
    @Singleton
    fun provideCustomerDao(database: Database): CustomerDao = database.customerDao()
}
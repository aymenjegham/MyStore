package com.angelstudios.mystore.di.module

import android.content.Context
import androidx.room.Room
import com.angelstudios.mystore.database.DATABASE_NAME
import com.angelstudios.mystore.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {

        return Room.databaseBuilder(
            context,
            Database::class.java,
            DATABASE_NAME
        ).build()

    }
}
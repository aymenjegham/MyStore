package com.angelstudios.mystore.di.module

import com.angelstudios.core.data.repository.user.UserRepository
import com.angelstudios.core.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsUserRepository(userRepository: UserRepositoryImpl) : UserRepository

}
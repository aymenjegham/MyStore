package com.angelstudios.mystore.di.module

import com.angelstudios.core.data.dataSource.helper.EmailMatcher
import com.angelstudios.core.data.dataSource.user.UserDatasource
import com.angelstudios.mystore.data.datasourceImpl.EmailAndroidMatcher
import com.angelstudios.mystore.data.datasourceImpl.UserDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindsUserDatasource(userDatasource: UserDatasourceImpl) : UserDatasource

    @Singleton
    @Binds
    abstract fun bindsEmailDatasource(emailAndroidMatcher: EmailAndroidMatcher) : EmailMatcher
}

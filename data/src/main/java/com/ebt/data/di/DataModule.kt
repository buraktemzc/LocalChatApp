package com.ebt.data.di

import android.content.Context
import com.ebt.data.local.datasource.LocalDataSource
import com.ebt.data.local.datasource.LocalDataSourceImpl
import com.ebt.data.local.room.ChatDao
import com.ebt.data.mapper.MessagesMapper
import com.ebt.data.preference.PreferenceProvider
import com.ebt.data.preference.PreferenceProviderImpl
import com.ebt.data.remote.api.ApiService
import com.ebt.data.remote.datasource.RemoteDataSource
import com.ebt.data.remote.datasource.RemoteDataSourceImpl
import com.ebt.data.repository.ChatRepositoryImpl
import com.ebt.data.session.LoggedInUser
import com.ebt.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideLocalDataSource(chatDao: ChatDao): LocalDataSource = LocalDataSourceImpl(chatDao)

    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
        RemoteDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideChatRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        loggedInUser: LoggedInUser,
        messagesMapper: MessagesMapper
    ): ChatRepository =
        ChatRepositoryImpl(localDataSource, remoteDataSource, loggedInUser, messagesMapper)

    @Singleton
    @Provides
    fun providePreferenceProvider(@ApplicationContext context: Context): PreferenceProvider =
        PreferenceProviderImpl(context)

    @Singleton
    @Provides
    fun provideLoggedInUser(preferenceProvider: PreferenceProvider): LoggedInUser =
        LoggedInUser(preferenceProvider)

}
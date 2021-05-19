package com.ebt.data.di

import android.content.Context
import androidx.room.Room
import com.ebt.data.local.room.ChatDao
import com.ebt.data.local.room.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ChatDatabase =
        Room.databaseBuilder(
            context, ChatDatabase::class.java, ChatDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideChatDao(chatDatabase: ChatDatabase): ChatDao = chatDatabase.chatDao()

}
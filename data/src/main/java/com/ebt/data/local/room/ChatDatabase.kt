package com.ebt.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.entity.UserEntity

@Database(entities = [UserEntity::class, MessageEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "chat_db"
    }

    abstract fun chatDao(): ChatDao
}
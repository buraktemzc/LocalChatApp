package com.ebt.data.local.datasource

import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertUser(userEntity: UserEntity): Long

    suspend fun insertUsers(userEntities: List<UserEntity>): LongArray

    suspend fun getUserByNickname(nickname: String): UserEntity?

    suspend fun insertMessages(messageEntities: List<MessageEntity>): LongArray

    suspend fun insertMessage(messageEntitiy: MessageEntity): Long

    fun getAllMessages(): Flow<List<MessageEntity>>
}
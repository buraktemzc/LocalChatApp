package com.ebt.domain.repository

import com.ebt.common.utils.network.Result
import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun insertUserToDB(userEntity: UserEntity): Long

    suspend fun insertUsersToDB(userEntities: List<UserEntity>): LongArray

    suspend fun getUserByNicknameFromDB(nickname: String): UserEntity?

    suspend fun getMessagesFromRemote(): Result<List<MessageEntity>>

    suspend fun insertMessagesToDB(messageEntities: List<MessageEntity>): LongArray

    suspend fun insertMessageToDB(messageEntity: MessageEntity): Long

    fun saveLoggedInUser(nickname: String)

    fun getLoggedInUser(): String

    fun getAllMessages(): Flow<List<MessageEntity>>
}
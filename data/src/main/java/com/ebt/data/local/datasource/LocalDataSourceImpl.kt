package com.ebt.data.local.datasource

import com.ebt.data.local.room.ChatDao
import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val chatDao: ChatDao) : LocalDataSource {
    override suspend fun insertUser(userEntity: UserEntity): Long {
        return chatDao.insertUser(userEntity)
    }

    override suspend fun insertUsers(userEntities: List<UserEntity>): LongArray {
        return chatDao.insertUsers(userEntities)
    }

    override suspend fun getUserByNickname(nickname: String): UserEntity? {
        return chatDao.selectUserByNickName(nickname)
    }

    override suspend fun insertMessages(messageEntities: List<MessageEntity>): LongArray {
        return chatDao.insertMessages(messageEntities)
    }

    override suspend fun insertMessage(messageEntitiy: MessageEntity): Long {
        return chatDao.insertMessage(messageEntitiy)
    }

    override fun getAllMessages(): Flow<List<MessageEntity>> {
        return chatDao.selectAllMessages()
    }
}
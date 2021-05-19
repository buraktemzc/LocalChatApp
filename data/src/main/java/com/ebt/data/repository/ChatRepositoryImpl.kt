package com.ebt.data.repository

import com.ebt.common.utils.network.Result
import com.ebt.data.entity.ApiResponse
import com.ebt.data.local.datasource.LocalDataSource
import com.ebt.data.mapper.MessagesMapper
import com.ebt.data.remote.datasource.RemoteDataSource
import com.ebt.data.session.LoggedInUser
import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.entity.UserEntity
import com.ebt.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ChatRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val loggedInUser: LoggedInUser,
    private val messagesMapper: MessagesMapper
) : ChatRepository {
    override suspend fun insertUserToDB(userEntity: UserEntity): Long {
        return localDataSource.insertUser(userEntity)
    }

    override suspend fun insertUsersToDB(userEntities: List<UserEntity>): LongArray {
        return localDataSource.insertUsers(userEntities)
    }

    override suspend fun getUserByNicknameFromDB(nickname: String): UserEntity? {
        return localDataSource.getUserByNickname(nickname)
    }

    override suspend fun getMessagesFromRemote(): Result<List<MessageEntity>> {
        val result = responseToResult(remoteDataSource.getMessages())
        return when (result) {
            is Result.Success -> Result.Success(messagesMapper.mapFromEntity(result.data))
            is Result.Error -> Result.Error(result.message)
            else -> Result.Error("Unknown")
        }
    }

    override suspend fun insertMessagesToDB(messageEntities: List<MessageEntity>): LongArray {
        return localDataSource.insertMessages(messageEntities)
    }

    override suspend fun insertMessageToDB(messageEntity: MessageEntity): Long {
        return localDataSource.insertMessage(messageEntity)
    }

    override fun saveLoggedInUser(nickname: String) {
        loggedInUser.userNickname = nickname
    }

    override fun getLoggedInUser(): String {
        return loggedInUser.userNickname
    }

    override fun getAllMessages(): Flow<List<MessageEntity>> {
        return localDataSource.getAllMessages()
    }

    private fun responseToResult(response: Response<ApiResponse>): Result<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Result.Success(result)
            }
        }
        return Result.Error(response.message())
    }
}
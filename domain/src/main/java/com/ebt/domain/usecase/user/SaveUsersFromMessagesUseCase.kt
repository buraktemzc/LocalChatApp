package com.ebt.domain.usecase.user

import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.entity.UserEntity
import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import javax.inject.Inject

class SaveUsersFromMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<SaveUsersFromMessagesUseCase.Params, LongArray> {

    override suspend fun execute(params: Params): LongArray {
        val messageList = params.messages
        val userMap = mutableMapOf<String, UserEntity>()
        for (messageEntity in messageList) {
            userMap[messageEntity.user.id] = messageEntity.user
        }

        return chatRepository.insertUsersToDB(ArrayList(userMap.values))
    }

    data class Params(val messages: List<MessageEntity>)
}
package com.ebt.domain.usecase.message

import com.ebt.common.utils.network.Result
import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import javax.inject.Inject

class GetMessagesFromRemoteUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<Unit, Result<List<MessageEntity>>> {
    override suspend fun execute(params: Unit): Result<List<MessageEntity>> {
        val messagesFromRemote = chatRepository.getMessagesFromRemote()
        messagesFromRemote.data?.map { it.timestamp = it.timestamp * 1000L }
        return messagesFromRemote
    }
}
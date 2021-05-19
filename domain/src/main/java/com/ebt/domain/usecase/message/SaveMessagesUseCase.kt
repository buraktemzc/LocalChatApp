package com.ebt.domain.usecase.message

import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import javax.inject.Inject

class SaveMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<SaveMessagesUseCase.Params, LongArray> {

    override suspend fun execute(params: Params): LongArray {
        return chatRepository.insertMessagesToDB(params.messages)
    }

    data class Params(val messages: List<MessageEntity>)
}
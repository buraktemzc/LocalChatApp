package com.ebt.domain.usecase.message

import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesFromDBUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<Unit, Flow<List<MessageEntity>>> {

    override suspend fun execute(params: Unit): Flow<List<MessageEntity>> {
        return chatRepository.getAllMessages()
    }
}
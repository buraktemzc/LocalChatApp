package com.ebt.domain.usecase.message

import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.entity.UserEntity
import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import java.util.*
import javax.inject.Inject

class SaveMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<SaveMessageUseCase.Params, Long> {

    override suspend fun execute(params: Params): Long {
        val messageEntity = MessageEntity(
            UUID.randomUUID().toString(),
            params.message,
            Calendar.getInstance().timeInMillis,
            params.userEntity
        )

        return chatRepository.insertMessageToDB(messageEntity)
    }

    data class Params(val message: String, val userEntity: UserEntity)
}
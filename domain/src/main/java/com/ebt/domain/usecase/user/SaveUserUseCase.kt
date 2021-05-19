package com.ebt.domain.usecase.user

import com.ebt.domain.entity.UserEntity
import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import java.util.*
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<SaveUserUseCase.Params, Unit> {

    override suspend fun execute(params: Params) {
        val existingUser = chatRepository.getUserByNicknameFromDB(params.nickname)
        if (existingUser == null) {
            val userEntity = UserEntity(UUID.randomUUID().toString(), null, params.nickname)
            chatRepository.insertUserToDB(userEntity)
        }
    }

    data class Params(val nickname: String)
}
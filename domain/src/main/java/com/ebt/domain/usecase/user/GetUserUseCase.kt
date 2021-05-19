package com.ebt.domain.usecase.user

import com.ebt.domain.entity.UserEntity
import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<GetUserUseCase.Params, UserEntity?> {

    override suspend fun execute(params: Params): UserEntity? {
        return chatRepository.getUserByNicknameFromDB(params.nickname)
    }

    data class Params(val nickname: String)
}
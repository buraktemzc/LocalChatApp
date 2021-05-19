package com.ebt.domain.usecase.session

import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import javax.inject.Inject

class SaveLoggedInUserUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<SaveLoggedInUserUseCase.Params, Unit> {

    override suspend fun execute(params: Params) {
        chatRepository.saveLoggedInUser(params.nickname)
    }

    data class Params(val nickname: String)
}
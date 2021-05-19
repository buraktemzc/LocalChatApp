package com.ebt.domain.usecase.session

import com.ebt.domain.repository.ChatRepository
import com.ebt.domain.usecase.SingleUseCase
import javax.inject.Inject

class GetLoggedInUserUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<Unit, String> {

    override suspend fun execute(params: Unit): String {
        return chatRepository.getLoggedInUser()
    }
}
package com.ebt.data.mapper

import com.ebt.data.entity.ApiResponse
import com.ebt.domain.entity.MessageEntity
import com.ebt.domain.entity.UserEntity
import javax.inject.Inject

class MessagesMapper @Inject constructor() : Mapper<ApiResponse, List<MessageEntity>> {
    override fun mapFromEntity(type: ApiResponse?): List<MessageEntity> {
        return if (type?.messages == null) {
            emptyList()
        } else {
            type.messages.map {
                with(it) {
                    MessageEntity(
                        id,
                        text,
                        timestamp,
                        UserEntity(user.id, user.avatarURL, user.nickname)
                    )
                }
            }
        }
    }
}
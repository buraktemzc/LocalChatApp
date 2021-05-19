package com.ebt.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class MessageEntity(
    @PrimaryKey
    var id: String,
    var text: String,
    var timestamp: Long,
    var user: UserEntity
)
package com.ebt.domain.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user",
    indices = [Index(value = ["nickname"], unique = true)]
)
data class UserEntity(
    @PrimaryKey
    var id: String,
    var avatarURL: String?,
    var nickname: String
)
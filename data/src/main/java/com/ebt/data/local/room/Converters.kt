package com.ebt.data.local.room

import androidx.room.TypeConverter
import com.ebt.domain.entity.UserEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun convertJsonToUserEntity(messagesJson: String): UserEntity? {
        val typeToken = object : TypeToken<UserEntity?>() {}.type
        return Gson().fromJson(messagesJson, typeToken)
    }

    @TypeConverter
    fun convertMessageEntityToJson(userEntity: UserEntity): String {
        return Gson().toJson(userEntity)
    }
}
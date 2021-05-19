package com.ebt.data.entity


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("messages")
    val messages: List<Message>?
)
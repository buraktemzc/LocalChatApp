package com.ebt.data.remote.api

import com.ebt.data.entity.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("files/chatdata-example.json")
    suspend fun getMessages(): Response<ApiResponse>
}
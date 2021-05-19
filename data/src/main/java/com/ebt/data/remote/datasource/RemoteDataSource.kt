package com.ebt.data.remote.datasource

import com.ebt.data.entity.ApiResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getMessages(): Response<ApiResponse>
}
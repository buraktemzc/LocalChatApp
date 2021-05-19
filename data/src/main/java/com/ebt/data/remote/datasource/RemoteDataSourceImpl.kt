package com.ebt.data.remote.datasource

import com.ebt.data.entity.ApiResponse
import com.ebt.data.remote.api.ApiService
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun getMessages(): Response<ApiResponse> {
        return apiService.getMessages()
    }
}
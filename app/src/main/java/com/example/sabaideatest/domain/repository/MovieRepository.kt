package com.example.sabaideatest.domain.repository

import com.example.sabaideatest.domain.model.MovieItemEntity
import com.example.sabaideatest.util.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovie(
        search: String
    ): Flow<NetworkResponse<List<MovieItemEntity>>>
}
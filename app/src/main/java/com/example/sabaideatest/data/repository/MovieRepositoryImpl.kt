package com.example.sabaideatest.data.repository

import com.example.sabaideatest.data.endpoint.MovieEndPoint
import com.example.sabaideatest.data.mapper.toDomain
import com.example.sabaideatest.domain.model.MovieItemEntity
import com.example.sabaideatest.domain.repository.MovieRepository
import com.example.sabaideatest.util.NetworkResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class MovieRepositoryImpl @Inject constructor(
    private val endPoint: MovieEndPoint
) : MovieRepository {
    override suspend fun getMovie(search: String): Flow<NetworkResponse<List<MovieItemEntity>>> =
        flow {
            try {
                val response = endPoint.getMovie(search)
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let { body ->
                            emit(NetworkResponse.Success(body.listItem.toDomain()))
                        } ?: emit(NetworkResponse.Failure(Exception("داده ای یافت نشد")))
                    }
                    false -> {
                        emit(NetworkResponse.Failure(Exception(response.message())))
                    }
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Failure(e))
            }
        }
}
package com.example.sabaideatest.data.repository

import com.example.sabaideatest.domain.model.MovieItemEntity
import com.example.sabaideatest.domain.repository.MovieRepository
import com.example.sabaideatest.util.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMovieRepositoryImpl : MovieRepository {

    private val movieList = arrayListOf(
        MovieItemEntity(
            id = "1",
            thumbnail = "https://static.cdn.asset.filimo.com/flmt/mov_130962_52696-s.jpg",
            movieTitle = "چند میگیری گریه کنی 2",
            movieTitleEnglish = "How Much Will You Take to Cry 2",
        )
    )
    private var shouldReturnNetworkError = false


    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getMovie(search: String): Flow<NetworkResponse<List<MovieItemEntity>>> =
        flow {
            if (shouldReturnNetworkError)
                emit(NetworkResponse.Failure(Exception("Error, Movies couldn't get from api")))
            else
                emit(NetworkResponse.Success(movieList))
        }
}
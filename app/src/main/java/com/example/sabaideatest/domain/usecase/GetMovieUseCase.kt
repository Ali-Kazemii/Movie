package com.example.sabaideatest.domain.usecase

import com.example.sabaideatest.domain.model.MovieItemEntity
import com.example.sabaideatest.domain.model.MovieRequestDto
import com.example.sabaideatest.domain.repository.MovieRepository
import com.example.sabaideatest.util.FlowUseCase
import com.example.sabaideatest.util.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : FlowUseCase<MovieRequestDto, List<MovieItemEntity>>() {

    override suspend fun run(request: MovieRequestDto): Flow<NetworkResponse<List<MovieItemEntity>>> {
        return repository.getMovie(
            search = request.search
        )
    }
}
package com.example.sabaideatest.domain.usecase

import com.example.sabaideatest.data.repository.FakeMovieRepositoryImpl
import com.example.sabaideatest.util.NetworkResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieUseCaseTest {

    private lateinit var repository: FakeMovieRepositoryImpl

    @Before
    fun setUp() {
        repository = FakeMovieRepositoryImpl()
    }

    @Test
    fun `check get movies from api, return Failure`() = runBlocking{
        repository.setShouldReturnNetworkError(true)
        val items= repository.getMovie("")
        Truth.assertThat(items.first() is NetworkResponse.Failure).isTrue()
    }

    @Test
    fun `check get categories from api, return Success`() = runBlocking{
        repository.setShouldReturnNetworkError(false)
        val items= repository.getMovie("چند میگیری گریه کنی")
        Truth.assertThat(items.first() is NetworkResponse.Success).isTrue()
    }
}
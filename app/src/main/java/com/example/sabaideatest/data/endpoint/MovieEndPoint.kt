package com.example.sabaideatest.data.endpoint

import com.example.sabaideatest.domain.model.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieEndPoint {
    @Headers("jsonType:simple")
    @GET("movie/movie/list/tagid/1000300/text/{Query}/sug/on")
    suspend fun getMovie(
        @Path("Query") search: String
    ): Response<MovieResponseDto>
}
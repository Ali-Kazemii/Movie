package com.example.sabaideatest.data.mapper

import com.example.sabaideatest.domain.model.MovieDto
import com.example.sabaideatest.domain.model.MovieItemEntity

fun List<MovieDto>?.toDomain(): List<MovieItemEntity> {
    return if (this.isNullOrEmpty())
        listOf()
    else map {
        MovieItemEntity(
            id = it.movieId,
            thumbnail = it.pic?.thumbnail,
            movieTitle = it.movieTitle,
            movieTitleEnglish = it.movieTitleEnglish
        )
    }
}
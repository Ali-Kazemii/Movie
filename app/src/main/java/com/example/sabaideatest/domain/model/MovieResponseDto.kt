package com.example.sabaideatest.domain.model


import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("data")
    val listItem: List<MovieDto>?,
)

data class MovieDto(
    @SerializedName("movie_id")
    val movieId: String?,
    @SerializedName("movie_title")
    val movieTitle: String?,
    @SerializedName("movie_title_en")
    val movieTitleEnglish: String?,
    @SerializedName("pic")
    val pic: Thumbnail?
)

data class Thumbnail(
    @SerializedName("movie_img_s")
    val thumbnail :String?= null
)
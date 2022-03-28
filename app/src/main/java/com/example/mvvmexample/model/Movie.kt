package com.example.mvvmexample.model

import com.squareup.moshi.Json

data class Movie(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "poster_path")
    val posterUrl: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "release_date")
    val releaseDate: String
) {
    companion object Constants {
        const val posterUrlPrefix = "https://image.tmdb.org/t/p/w185"
    }
}
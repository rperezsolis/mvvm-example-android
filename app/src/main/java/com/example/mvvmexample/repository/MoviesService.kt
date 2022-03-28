package com.example.mvvmexample.repository

import com.example.mvvmexample.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("movie/popular")
    fun getMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>
}
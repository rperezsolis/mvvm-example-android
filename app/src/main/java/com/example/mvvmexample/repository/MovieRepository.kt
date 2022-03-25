package com.example.mvvmexample.repository

import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class MovieRepository(
    val moviesListener: (property: KProperty<*>, oldValue: List<Movie>, newValue: List<Movie>) -> Unit
) {
    object MovieAPI {
        const val apiKey = "7e6a9a3be4a61e50096bada46314ae9b"
        const val apiReadAccessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZTZhOWEzYmU0YTYxZTUwMDk2Ym" +
                "FkYTQ2MzE0YWU5YiIsInN1YiI6IjVkMzc1ZTgwYWI2ODQ5MDAwZjg2N2U3MyIsInNjb3BlcyI6WyJhcGlfc" +
                "mVhZCJdLCJ2ZXJzaW9uIjoxfQ.6xf8iIDBQz5zNTn7K-RZYP_z4Typ8gVI6THbCBVHPv4"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val movieServiceImpl: MovieService = retrofit.create(MovieService::class.java)

    var movies: List<Movie> by Delegates
        .observable(initialValue = mutableListOf()) { property, oldValue, newValue ->
            moviesListener.invoke(property, oldValue, newValue)
    }

    suspend fun getMovies() {
        withContext(Dispatchers.Default) {
            movies = movieServiceImpl
                .getMoviesAsync(apiKey = MovieAPI.apiKey).await().results
        }
    }
}

interface MovieService {
    @GET("movie/popular")
    fun getMoviesAsync(@Query("api_key") apiKey: String): Call<MovieResponse>
}
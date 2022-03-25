package com.example.mvvmexample.repository

import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.model.MoviesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.await
import kotlin.coroutines.resume

class MoviesRepository(
    private val moviesServiceImpl: MoviesService
) {
    object MovieAPI {
        const val apiKey = "7e6a9a3be4a61e50096bada46314ae9b"
        const val apiReadAccessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZTZhOWEzYmU0YTYxZTUwMDk2Ym" +
                "FkYTQ2MzE0YWU5YiIsInN1YiI6IjVkMzc1ZTgwYWI2ODQ5MDAwZjg2N2U3MyIsInNjb3BlcyI6WyJhcGlfc" +
                "mVhZCJdLCJ2ZXJzaW9uIjoxfQ.6xf8iIDBQz5zNTn7K-RZYP_z4Typ8gVI6THbCBVHPv4"
    }

    suspend fun getMovies() = suspendCancellableCoroutine<List<Movie>> { continuation ->
        CoroutineScope(Dispatchers.Default).launch {
            val moviesResponse: MoviesResponse = moviesServiceImpl.getMoviesAsync(
                apiKey = MovieAPI.apiKey
            ).await()
            continuation.resume(moviesResponse.results)
        }
    }
}
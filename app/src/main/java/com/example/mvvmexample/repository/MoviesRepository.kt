package com.example.mvvmexample.repository

import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.model.MoviesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MoviesRepository(
    private val _moviesServiceImpl: MoviesService
) {
    object MovieAPI {
        const val apiKey = "7e6a9a3be4a61e50096bada46314ae9b"
    }

    suspend fun getMovies(page: Int) = suspendCancellableCoroutine<List<Movie>?> { continuation ->
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val moviesResponse: MoviesResponse = _moviesServiceImpl.getMoviesAsync(
                    apiKey = MovieAPI.apiKey,
                    page = page
                ).await()
                continuation.resume(moviesResponse.results)
            } catch (exception: Exception) {
                continuation.resumeWithException(exception)
            }
        }
    }
}
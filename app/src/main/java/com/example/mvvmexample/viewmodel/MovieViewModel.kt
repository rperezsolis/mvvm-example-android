package com.example.mvvmexample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty

class MovieViewModel: ViewModel() {
    private val movieListener = { _: KProperty<*>, _: List<Movie>, newValue: List<Movie> ->
        _movies.value = newValue
    }
    private val movieRepository: MovieRepository = MovieRepository(moviesListener = movieListener)

    private val _movies = MutableStateFlow<List<Movie>>(mutableListOf())
    val movies: StateFlow<List<Movie>>
        get() = _movies

    fun getMovies() {
        CoroutineScope(Dispatchers.Default).async {
            movieRepository.getMovies()
        }
    }
}
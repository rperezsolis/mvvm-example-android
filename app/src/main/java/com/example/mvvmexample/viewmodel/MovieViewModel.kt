package com.example.mvvmexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class MovieViewModel: ViewModel() {
    private val movieRepository: MovieRepository = MovieRepository()

    private val _movies = MutableStateFlow<List<Movie>>(mutableListOf())
    val movies: StateFlow<List<Movie>>
        get() = _movies

    fun getMovies() {
        viewModelScope.launch {
            _movies.value = movieRepository.getMovies()
        }
    }
}
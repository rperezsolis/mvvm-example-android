package com.example.mvvmexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesRepository: MoviesRepository
): ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(mutableListOf())
    val movies: StateFlow<List<Movie>>
        get() = _movies

    fun getMovies() {
        viewModelScope.launch {
            _movies.value = moviesRepository.getMovies()
        }
    }
}
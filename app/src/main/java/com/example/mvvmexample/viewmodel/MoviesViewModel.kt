package com.example.mvvmexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val _moviesRepository: MoviesRepository
): ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(mutableListOf())
    val movies: StateFlow<List<Movie>>
        get() = _movies

    private var _page: Int = 1

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing

    private val _currentMoviesException = MutableStateFlow<Exception?>(null)
    val currentMoviesException: StateFlow<Exception?>
        get() = _currentMoviesException

    private val _currentMovie = MutableStateFlow<Movie?>(null)
    val currentMovie: StateFlow<Movie?>
        get() = _currentMovie

    fun getMovies(refreshing: Boolean = false) {
        viewModelScope.launch {
            try {
                _isRefreshing.value = true
                val movies = _moviesRepository.getMovies(page = _page)
                if (!movies.isNullOrEmpty()) {
                    if (refreshing) {
                        _movies.value = movies
                    } else {
                        _movies.value = _movies.value.plus(movies)
                    }
                    _page++
                }
            } catch (exception: Exception) {
                setCurrentMoviesException(exception)
            }
            _isRefreshing.value = false
        }
    }

    fun refreshMovies() {
        _page = 1
        getMovies(refreshing = true)
    }

    fun setCurrentMoviesException(exception: Exception?) {
        _currentMoviesException.value = exception
    }

    fun setCurrentMovie(movie: Movie) {
        _currentMovie.value = movie
    }
}
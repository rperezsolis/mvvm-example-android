package com.example.mvvmexample.ui.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.viewmodel.MovieViewModel

@Composable
fun MoviesScreen(
    movieViewModel: MovieViewModel
) {
    val movieList: List<Movie> by movieViewModel.movies.collectAsState()

    LazyColumn {
        items(movieList) { movie ->
            Text(text = movie.title)
        }
    }
}
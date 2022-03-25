package com.example.mvvmexample.ui.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.viewmodel.MoviesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier
) {
    //We use the getViewModel function to get the instance of our MoviesViewModel dependency
    val movieViewModel: MoviesViewModel = getViewModel()
    val movieList: List<Movie> by movieViewModel.movies.collectAsState()

    LazyColumn(
        modifier = modifier
    ) {
        items(movieList) { movie ->
            Text(text = movie.title)
        }
    }
}
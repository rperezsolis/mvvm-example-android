package com.example.mvvmexample.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.viewmodel.MoviesViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier
) {
    //We use the getViewModel function to get the instance of our MoviesViewModel dependency
    val movieViewModel: MoviesViewModel = getViewModel()
    val movieList: List<Movie> by movieViewModel.movies.collectAsState()

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(movieList) { movie ->
            MovieItem(
                movie = movie,
                modifier = Modifier.padding(all = 12.dp)
            )
        }
    }
}
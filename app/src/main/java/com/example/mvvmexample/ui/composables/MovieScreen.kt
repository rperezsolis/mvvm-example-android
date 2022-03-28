package com.example.mvvmexample.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmexample.extensions.shouldLoadMore
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.viewmodel.MoviesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier
) {
    //We use the getViewModel function to get the instance of our MoviesViewModel dependency
    val moviesViewModel: MoviesViewModel = getViewModel()
    val movieList: List<Movie> by moviesViewModel.movies.collectAsState()
    val isRefreshing: Boolean by moviesViewModel.isRefreshing.collectAsState()
    val currentMoviesException: Exception? by moviesViewModel.currentMoviesException.collectAsState()
    val lazyListState = rememberLazyListState()
    val firstVisibleIndex = remember {
        mutableStateOf(lazyListState.firstVisibleItemIndex)
    }

    Column {
        TopAppBar(
            backgroundColor = Color.White,
        ) {
            Text(
                text = "Android MVVM Example",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 8.dp),
            )
        }
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = {
                moviesViewModel.refreshMovies()
            }
        ) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = modifier,
                state = lazyListState
            ) {
                items(movieList) { movie ->
                    MovieItem(
                        movie = movie,
                        modifier = Modifier.padding(all = 12.dp)
                    )
                }
            }
        }
        if (currentMoviesException != null) {
            ErrorModal(exception = currentMoviesException!!) {
                moviesViewModel.currentMoviesException.value = null
            }
        }
    }

    if (lazyListState.shouldLoadMore(firstVisibleIndex)) {
        moviesViewModel.getMovies()
    }
}
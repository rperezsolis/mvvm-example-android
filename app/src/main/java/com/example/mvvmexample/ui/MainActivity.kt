package com.example.mvvmexample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmexample.ui.theme.MVVMExampleTheme
import com.example.mvvmexample.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getMovies()

        setContent {
            MVVMExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MoviesScreen(
                        movieViewModel = movieViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun MoviesScreen(
    movieViewModel: MovieViewModel
) {
    val movieList by movieViewModel.movies.collectAsState()

    LazyColumn {
        items(movieList) { movie ->
            Text(text = movie.title)
        }
    }
}
package com.example.mvvmexample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmexample.ui.composables.MovieDetailsRoute
import com.example.mvvmexample.ui.composables.MovieDetailsScreen
import com.example.mvvmexample.ui.composables.MoviesRoute
import com.example.mvvmexample.ui.composables.MoviesScreen
import com.example.mvvmexample.ui.theme.MVVMExampleTheme
import com.example.mvvmexample.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    //We inject the MoviesViewModel by using the koin's viewModel() function
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesViewModel.getMovies()

        setContent {
            MVVMExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = MoviesRoute.name) {
                        composable(MoviesRoute.name) {
                            MoviesScreen(
                                navController = navController
                            )
                        }
                        composable(MovieDetailsRoute.name) {
                            MovieDetailsScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
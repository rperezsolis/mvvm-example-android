package com.example.mvvmexample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
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
                    MoviesScreen()
                }
            }
        }
    }
}
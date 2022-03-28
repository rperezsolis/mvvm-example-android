package com.example.mvvmexample.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mvvmexample.R
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.viewmodel.MoviesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieDetailsScreen(
    navController: NavController
) {
    val moviesViewModel: MoviesViewModel = getViewModel()
    val movie by moviesViewModel.currentMovie.collectAsState()

    if (movie != null) {
        val posterUrl = "${Movie.Constants.posterUrlPrefix}${movie!!.posterUrl}"

        Column {
            AppTopAppBar(
                title = "Movie Details",
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                }
            )
            Column(
                modifier = Modifier.padding(all = 16.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(posterUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.text_picture_poster),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .defaultMinSize(minWidth = 150.dp)
                )
                Text(
                    text = movie!!.title,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = movie!!.overview,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Release date: ${movie!!.releaseDate}"
                )
            }
        }
    }
}

object MovieDetailsRoute {
    const val name = "MovieDetails/{movie}"
}
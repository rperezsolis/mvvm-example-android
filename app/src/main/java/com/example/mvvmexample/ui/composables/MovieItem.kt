package com.example.mvvmexample.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mvvmexample.R
import com.example.mvvmexample.model.Movie
import com.example.mvvmexample.viewmodel.MoviesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieItem(
    movie: Movie,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val moviesViewModel: MoviesViewModel = getViewModel()
    val posterUrl = "${Movie.Constants.posterUrlPrefix}${movie.posterUrl}"

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.clickable {
            moviesViewModel.setCurrentMovie(movie = movie)
            navController.navigate(MovieDetailsRoute.name)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(posterUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.text_picture_poster),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = movie.title,
                textAlign = TextAlign.Center
            )
        }
    }
}
package com.example.composemovieapp.presentation


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composemovieapp.domain.MovieViewModel
import com.example.composemovieapp.model.MoviesItem
import com.example.composemovieapp.ui.theme.gray
import com.example.composemovieapp.ui.theme.textColor

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    navController: NavController,
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val movieList by movieViewModel.getList.collectAsState()

        Column {
            BodyView(navController = navController, movieList = movieList)
        }
}


@Composable
fun BodyView(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieList: List<MoviesItem>
) {
    Box(
        modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color.White)
            .padding(top = 30.dp)

    ) {

        Column {

            movieListSection(movies = movieList, navController = navController)

        }

    }
}



@Composable
fun movieListSection(
    movies: List<MoviesItem>,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(movies.size) {
                MovieListItem(movie = movies[it], navController = navController)
            }
        }
    }

}


@Composable
fun MovieListItem(
    movie: MoviesItem,
    navController: NavController
) {

    //val image = rememberAsyncImagePainter(model = games.thumbnail)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Black)
            .clickable {
                navController.navigate("movie_details/${movie.id}")
            }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()

        ) {

            AsyncImage(
                model = movie.image.medium,
                contentDescription = null,
                modifier = Modifier
                    .height(115.dp)

            )
            
            Spacer(modifier = Modifier.width(60.dp))

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {

                Text(
                    text = movie.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier

                )

                Text(
                    text = movie.rating.toString(),
                    color = textColor,
                    modifier = Modifier

                )

                Text(
                    text = movie.genres.toString(),
                    color = textColor,
                    modifier = Modifier

                )
            }

        }

    }
}



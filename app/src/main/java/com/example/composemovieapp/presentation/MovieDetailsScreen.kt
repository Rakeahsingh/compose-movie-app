package com.example.composemovieapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composemovieapp.domain.MovieDetailsViewModel
import com.example.composemovieapp.ui.theme.gray
import com.example.composemovieapp.ui.theme.rating
import com.example.composemovieapp.ui.theme.textColor
import com.example.composemovieapp.ui.theme.textxColor


@Composable
fun movieDetailsScreen(
    navController: NavController,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
){

    val movie by movieDetailsViewModel.movie.collectAsState()

    Box(
        modifier = Modifier
            .background(gray)
            .fillMaxSize()
    ){
        Column {

           Icon(
               imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
               tint = Color.White,
               modifier = Modifier
                   .padding(10.dp)
                   .size(24.dp)
                   .clickable {
                       navController.popBackStack()
                   }
           )

            AsyncImage(
                model = movie?.image?.original,
                contentDescription = "movie image",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.Black)
            ){
               Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {



                       movieName()

                       movieLanguage()

                       movieSummary()

                       Summary()

                   }


                }

           }
      }

}

@Composable
fun movieName(
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
){
    val movie by movieDetailsViewModel.movie.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = movie?.name.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 30.dp)

        )

        Spacer(modifier = Modifier.width(15.dp))

        Icon(
            imageVector = Icons.Default.Star  , contentDescription = "Star",
            tint = rating
        )
        Icon(
            imageVector = Icons.Default.Star  , contentDescription = "Star",
            tint = rating
        )
        Icon(
            imageVector = Icons.Default.Star  , contentDescription = "Star",
            tint = rating
        )


    }
}

@Composable
fun movieLanguage(
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
){
    val movie by movieDetailsViewModel.movie.collectAsState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 5.dp)
    ) {
        Text(
            text = movie?.language.toString(),
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(textColor)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = movie?.genres.toString(),
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(textColor)
                .padding(8.dp)

        )

    }
}

@Composable
fun movieSummary(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 1.dp)
    ) {
        Text(
            text = "Movie Summary",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

    }
}


@Composable
fun Summary(
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
){
    val movie by movieDetailsViewModel.movie.collectAsState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 1.dp)
    ){
        Text(
            text = movie?.summary.toString(),
            color = textxColor,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(textColor)
                .padding(8.dp)

        )
    }
}



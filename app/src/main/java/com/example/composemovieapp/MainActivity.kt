package com.example.composemovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composemovieapp.presentation.HomeScreen
import com.example.composemovieapp.presentation.movieDetailsScreen
import com.example.composemovieapp.ui.theme.ComposeMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMovieAppTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = "home_screen"
                ){
                    composable("home_screen"){
                        HomeScreen(navController = navController)
                    }
                   composable("movie_details/{movieId}",
                       arguments = listOf(
                           navArgument("movieId"){
                               type = NavType.IntType
                           }
                       )
                   ) {
                       val movieName = remember {
                           it.arguments?.getInt("movieId")
                       }
                       movieDetailsScreen(navController = navController)
                   }
                }
            }

        }
    }
}


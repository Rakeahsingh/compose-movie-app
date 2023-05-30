package com.example.composemovieapp.data


import com.example.composemovieapp.model.MoviesItem
import com.example.composemovieapp.utils.Constants.END_POINT
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieApi {

    @GET(END_POINT)
    suspend fun getMovies(): List<MoviesItem>

    @GET(END_POINT + "/{id}")
    suspend fun getMovieById(
        @Path("id") id : Int
    ) : MoviesItem
}
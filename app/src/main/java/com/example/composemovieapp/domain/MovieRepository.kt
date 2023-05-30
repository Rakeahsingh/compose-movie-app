package com.example.composemovieapp.domain

import com.example.composemovieapp.Resource
import com.example.composemovieapp.data.MovieApi
import com.example.composemovieapp.model.MoviesItem
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieRepository @Inject constructor(
    private val api: MovieApi
) {

    suspend fun getMovies(): Resource<List<MoviesItem>>{
        val response = try {
            api.getMovies()
        }catch (e:Exception){
            return Resource.Error("An Unknown Error Occured")
        }
        return Resource.Success(response)
    }

    suspend fun getMovieById(id: Int): Resource<MoviesItem> {
        val response = try {
            api.getMovieById(id)
        }catch (e: Exception){
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}
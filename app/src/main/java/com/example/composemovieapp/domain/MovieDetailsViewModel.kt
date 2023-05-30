package com.example.composemovieapp.domain

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.composemovieapp.Resource
import com.example.composemovieapp.model.MoviesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val movieId = savedStateHandle.get<Int>("movieId") ?: 0

    private val _movie = MutableStateFlow<MoviesItem?>(null)
    val movie = _movie.asStateFlow()

    init {
        viewModelScope.launch {
            getMovie()
        }

        viewModelScope.launch {
            movie.collect{
                Log.v("MYTAG", it.toString())
            }
        }
    }

    suspend fun getMovie() {

        delay(500)
        val result = movieRepository.getMovieById(movieId)
        if (result is Resource.Success) {
            _movie.value = result.data!!
        }
    }

}
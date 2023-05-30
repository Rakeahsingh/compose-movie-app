package com.example.composemovieapp.domain


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovieapp.Resource
import com.example.composemovieapp.model.MoviesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private var _getList = MutableStateFlow<List<MoviesItem>>(emptyList())
    var getList : StateFlow<List<MoviesItem>> = _getList.asStateFlow()

    init {
        viewModelScope.launch {
            getMovies()
        }
    }

    suspend fun getMovies(): Resource<List<MoviesItem>> {
        val result = movieRepository.getMovies()
        if (result is Resource.Success) {
            _getList.value = result.data!!
        }
        return result
    }

}
package com.example.sabaideatest.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sabaideatest.domain.model.MovieItemEntity
import com.example.sabaideatest.domain.model.MovieRequestDto
import com.example.sabaideatest.domain.usecase.GetMovieUseCase
import com.example.sabaideatest.util.NetworkResponse
import com.example.sabaideatest.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    val failure = SingleLiveEvent<String>()
    var progress = SingleLiveEvent<Boolean>()
    private var _listMovie = MutableLiveData(listOf<MovieItemEntity>())
    val listMovie: LiveData<List<MovieItemEntity>> = _listMovie

    private fun showLoading() {
        progress.postValue(true)
    }

    private fun hideLoading() {
        progress.postValue(false)
    }

    fun getMovie(search: String) {
        showLoading()
        viewModelScope.launch {
            getMovieUseCase.run(
                MovieRequestDto(
                    search = search
                )
            ).collect {
                when (it) {
                    is NetworkResponse.Success -> {
                        _listMovie.postValue(it.data)
                    }
                    is NetworkResponse.Failure -> {
                        failure.postValue(it.e.message)
                    }
                }
                hideLoading()
            }
        }
    }
}
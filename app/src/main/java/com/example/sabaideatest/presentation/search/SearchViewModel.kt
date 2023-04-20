package com.example.sabaideatest.presentation.search

import androidx.lifecycle.ViewModel
import com.example.sabaideatest.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {


    val success = SingleLiveEvent<Boolean>()
    val failure = SingleLiveEvent<String>()
    var progress = SingleLiveEvent<Boolean>()

    fun showLoading() {
        progress.postValue(true)
    }

    fun hideLoading() {
        progress.postValue(false)
    }


}
package com.nguca.newsapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguca.newsapp.data.model.News
import com.nguca.newsapp.data.response.NewsResponse
import com.nguca.newsapp.domain.usecase.GetNewsUseCase
import com.nguca.newsapp.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    private val _state = MutableStateFlow<State<NewsResponse>>(State.Loading)
    val state = _state as StateFlow<State<NewsResponse>>

    private var job : Job?= null
    init {
            getNews()
    }
    fun getNews(text:String?=null, country:String?=null) {
        job?.cancel()
        job = viewModelScope.launch {
            _state.tryEmit(State.Loading)
            try {
                val result = getNewsUseCase.invoke("sp", text, country)
                _state.tryEmit(State.Success(result))
            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }
}
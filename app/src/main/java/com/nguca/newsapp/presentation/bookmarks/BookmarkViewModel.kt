package com.nguca.newsapp.presentation.bookmarks

import androidx.lifecycle.ViewModel
import com.nguca.newsapp.data.database.NewsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(database: NewsDatabase) : ViewModel() {

    private val newsDao = database.newsDao()

    fun getBookmarks() = newsDao.getNews()


}
package com.nguca.newsapp.domain.repository

import com.nguca.newsapp.data.response.NewsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getNews(
        language: String,
        text: String?,
        country: String?,
    ): Response<NewsResponse>

}
package com.nguca.newsapp.domain.usecase

import com.nguca.newsapp.data.response.NewsResponse
import com.nguca.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(
        language: String,
        text: String?,
        country: String?
    ): NewsResponse {
        val response = newsRepository.getNews(language, text, country)
        if (response.body() == null)
        {
            if(response.code() == 404)
                throw Exception("No News Found")
            else if(response.code() == 500)
                throw Exception("Server Error")
            else if(response.code()==401)
                throw Exception("Unauthorized")
            else if(response.code() == 400)
                throw Exception("Bad Request")
            else
            throw Exception("No News Found")
        }
        return newsRepository.getNews(language, text, country).body()!!

    }

}
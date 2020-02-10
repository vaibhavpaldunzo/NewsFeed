package com.example.newsfeed

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/v2/everything?q=bitcoin&from=2020-01-07&sortBy=publishedAt&apiKey=6d1a117aba67413fa70fb9b0721bf146

interface NewsApiService {

    @GET("v2/everything")
    fun fetchNews(@Query("q") q : String, @Query("apiKey") apiKey : String) : Call<NewsApiResponse>

}
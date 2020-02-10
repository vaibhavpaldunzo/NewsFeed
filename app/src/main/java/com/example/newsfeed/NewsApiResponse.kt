package com.example.newsfeed

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
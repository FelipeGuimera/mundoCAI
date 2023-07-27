package com.example.mundocai.data.model

import com.google.firebase.Timestamp

data class News(
    val image_news: String = "",
    val title_news: String = "",
    val description_news: String = "",
    val news_date: Timestamp? = null
)

data class NewsList(val results: List<News> = listOf())

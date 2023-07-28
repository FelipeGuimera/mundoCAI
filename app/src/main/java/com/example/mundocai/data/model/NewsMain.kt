package com.example.mundocai.data.model

import com.google.firebase.Timestamp

data class NewsMain(
    val image_mainNews: String = "",
    val title_mainNews: String = "",
    val description_mainNews: String = "",
    val date_mainNews: Timestamp? = null
)

data class NewsMainList(val results: List<NewsMain> = listOf())
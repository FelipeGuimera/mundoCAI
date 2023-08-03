package com.example.mundocai.data.model

data class History(
    val image_history: String = "",
    val cai_image: String = "",
    val rival_image: String = "",
    val title_history: String = "",
    val description_history: String = "",
)

data class HistoryList(val results: List<History> = listOf())
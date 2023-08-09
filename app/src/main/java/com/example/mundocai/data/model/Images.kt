package com.example.mundocai.data.model

data class Images(
    val image: String = "",
    val txt_image: String = "",
)

data class ImagesList(val results: List<Images> = listOf())
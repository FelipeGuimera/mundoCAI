package com.example.mundocai.data.model

data class Images(
    val image: String = "",
    val txt_image: String = "",
    val imageCarousel1 :String = "",
    val imageCarousel2 :String = "",
    val imageCarousel3 :String = "",
)

data class ImagesList(val results: List<Images> = listOf())
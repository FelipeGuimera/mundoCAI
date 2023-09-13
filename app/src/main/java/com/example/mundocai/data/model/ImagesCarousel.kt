package com.example.mundocai.data.model


data class ImagesCarousel(
    val imageUrl: String = "",

)

data class ImagesCarouselList(val results: List<ImagesCarousel> = listOf())
package com.example.mundocai.data.model


data class ImagesCarousel(
    val img: String = "",

)

data class ImagesCarouselList(val results: List<ImagesCarousel> = listOf())
package com.example.mundocai.domain


import com.example.mundocai.data.model.NewsList


interface HomeScreenRepo {
    suspend fun getLatestNews(): NewsList

    suspend fun getLatestNewsMain(): NewsList
}
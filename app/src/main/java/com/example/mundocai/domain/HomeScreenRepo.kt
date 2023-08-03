package com.example.mundocai.domain


import com.example.mundocai.data.model.HistoryList
import com.example.mundocai.data.model.MatchsList
import com.example.mundocai.data.model.NewsList


interface HomeScreenRepo {
    suspend fun getLatestNews(): NewsList

    suspend fun getLatestNewsMain(): NewsList

    suspend fun getLatestMatchs(): MatchsList

    suspend fun getLatestHistory(): HistoryList
}
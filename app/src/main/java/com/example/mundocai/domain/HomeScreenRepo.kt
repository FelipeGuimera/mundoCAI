package com.example.mundocai.domain


import com.example.mundocai.data.model.*


interface HomeScreenRepo {
    suspend fun getLatestNews(): NewsList

    suspend fun getLatestNewsMain(): NewsList

    suspend fun getLatestMatchs(): MatchsList

    suspend fun getLatestHistory(): HistoryList

    suspend fun getInvite(): InviteList

    suspend fun getImages(): ImagesList
}
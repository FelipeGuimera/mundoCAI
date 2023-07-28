package com.example.mundocai.domain

import com.example.mundocai.core.Resource
import com.example.mundocai.data.model.News
import com.example.mundocai.data.model.NewsMain

interface HomeScreenRepo {
    suspend fun getLatestNews(): Resource<List<News>>

    suspend fun getLatestNewsMain(): Resource<List<NewsMain>>
}
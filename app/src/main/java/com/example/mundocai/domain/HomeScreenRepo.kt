package com.example.mundocai.domain

import com.example.mundocai.core.Resource
import com.example.mundocai.data.model.News

interface HomeScreenRepo {
    suspend fun getLatestNews(): Resource<List<News>>
}
package com.example.mundocai.domain

import com.example.mundocai.core.Resource
import com.example.mundocai.data.model.News
import com.example.mundocai.data.model.NewsMain
import com.example.mundocai.data.remote.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestNews(): Resource<List<News>> = dataSource.getLatestNews()

    override suspend fun getLatestNewsMain(): Resource<List<NewsMain>> = dataSource.getLatestNewsMain()
}
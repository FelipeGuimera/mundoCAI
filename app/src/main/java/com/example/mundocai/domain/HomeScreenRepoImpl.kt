package com.example.mundocai.domain

import com.example.mundocai.data.model.MatchsList
import com.example.mundocai.data.model.NewsList
import com.example.mundocai.data.remote.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestNews(): NewsList = dataSource.getLatestNews()

    override suspend fun getLatestNewsMain(): NewsList = dataSource.getLatestNewsMain()

    override suspend fun getLatestMatchs(): MatchsList = dataSource.getLatestMatchs()
}
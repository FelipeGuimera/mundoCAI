package com.example.mundocai.domain.home

import com.example.mundocai.data.model.*
import com.example.mundocai.data.remote.home.HomeScreenDataSource
import com.example.mundocai.domain.home.HomeScreenRepo

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestNews(): NewsList = dataSource.getLatestNews()

    override suspend fun getLatestNewsMain(): NewsList = dataSource.getLatestNewsMain()

    override suspend fun getLatestMatchs(): MatchsList = dataSource.getLatestMatchs()

    override suspend fun getLatestHistory(): HistoryList = dataSource.getLatestHistory()

    override suspend fun getInvite(): InviteList = dataSource.getInvite()

    override suspend fun getImages(): ImagesList = dataSource.getImages()

}
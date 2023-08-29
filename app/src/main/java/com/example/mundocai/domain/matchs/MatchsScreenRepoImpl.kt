package com.example.mundocai.domain.matchs

import com.example.mundocai.data.model.MatchsList
import com.example.mundocai.data.remote.home.HomeScreenDataSource
import com.example.mundocai.data.remote.matchs.MatchsScreenDataSource
import com.example.mundocai.domain.home.HomeScreenRepo

class MatchsScreenRepoImpl (private val dataSource: MatchsScreenDataSource): MatchsScreenRepo {

    override suspend fun getLatestMatchs(): MatchsList = dataSource.getLatestMatchs()
}
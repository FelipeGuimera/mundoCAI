package com.example.mundocai.domain.ranking

import com.example.mundocai.data.model.MatchsList
import com.example.mundocai.data.model.UserList
import com.example.mundocai.data.remote.matchs.MatchsScreenDataSource
import com.example.mundocai.data.remote.ranking.RankingScreenDataSource
import com.example.mundocai.domain.matchs.MatchsScreenRepo

class RankingScreenRepoImpl (private val dataSource: RankingScreenDataSource): RankingScreenRepo {

    override suspend fun getRanking(): UserList = dataSource.getRanking(3)

    override suspend fun getPodium(): UserList = dataSource.getPodium()
}
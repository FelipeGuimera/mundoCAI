package com.example.mundocai.domain.ranking

import com.example.mundocai.data.model.MatchsList
import com.example.mundocai.data.model.UserList

interface RankingScreenRepo {

    suspend fun getRanking(): UserList

    suspend fun getPodium(): UserList
}
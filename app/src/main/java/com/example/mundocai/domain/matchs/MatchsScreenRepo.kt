package com.example.mundocai.domain.matchs

import com.example.mundocai.data.model.MatchsList

interface MatchsScreenRepo {

    suspend fun getLatestMatchs(): MatchsList
}
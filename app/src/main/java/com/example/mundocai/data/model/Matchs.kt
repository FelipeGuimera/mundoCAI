package com.example.mundocai.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Matchs(
    @ServerTimestamp
    var created_at: Date? = null,
    val local_team_image: String = "",
    val away_team_image: String = "",
    val game_day: String = "",
    val stadium_name: String = "",
    val l_or_v: String = "",
    val local_score: String = "",
    val away_score: String = "",
    )

data class MatchsList(val results: List<Matchs> = listOf())
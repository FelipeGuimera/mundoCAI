package com.example.mundocai.data.remote.matchs

import com.example.mundocai.data.model.Matchs
import com.example.mundocai.data.model.MatchsList
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class MatchsScreenDataSource {

    suspend fun getLatestMatchs(): MatchsList {
        val matchsList = mutableListOf<Matchs>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("matchs")
            .orderBy("created_at", Query.Direction.ASCENDING).get().await()

        for (post in querySnapshot.documents) {
            post.toObject(Matchs::class.java)?.let { fbMatchs ->
                fbMatchs.apply {
                    created_at = post.getTimestamp(
                        "created_at", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )?.toDate()
                    matchsList.add(fbMatchs)
                }
            }
        }
        return MatchsList(matchsList)
    }
}
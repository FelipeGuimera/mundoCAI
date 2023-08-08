package com.example.mundocai.data.remote

import com.example.mundocai.data.model.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestNews(): NewsList {
        val newsList = mutableListOf<News>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("news").get().await()
        for (post in querySnapshot.documents) {
            post.toObject(News::class.java)?.let { fbNews ->
                newsList.add(fbNews)
            }
        }
        return NewsList(newsList)
    }

    suspend fun getLatestNewsMain(): NewsList {
        val newsMainList = mutableListOf<News>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("newsMain").get().await()
        for (post in querySnapshot.documents) {
            post.toObject(News::class.java)?.let { fbNewsMain ->
                newsMainList.add(fbNewsMain)
            }
        }
        return NewsList(newsMainList)
    }

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

    suspend fun getLatestHistory(): HistoryList {
        val historyList = mutableListOf<History>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("history").get().await()
        for (post in querySnapshot.documents) {
            post.toObject(History::class.java)?.let { fbHistory ->
                historyList.add(fbHistory)
            }
        }
        return HistoryList(historyList)

    }

    suspend fun getInvite(): InviteList {
        val inviteList = mutableListOf<Invite>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("invite").get().await()
        for (post in querySnapshot.documents) {
            post.toObject(Invite::class.java)?.let { fbInvite->
                inviteList.add(fbInvite)
            }
        }
        return InviteList(inviteList)
    }

}
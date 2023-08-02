package com.example.mundocai.data.remote

import com.example.mundocai.data.model.Matchs
import com.example.mundocai.data.model.MatchsList
import com.example.mundocai.data.model.News
import com.example.mundocai.data.model.NewsList
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestNews(): NewsList {
        val newsList = mutableListOf<News>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("news").get().await()
        for(post in querySnapshot.documents){
            post.toObject(News::class.java)?.let { fbNews ->
                newsList.add(fbNews)
            }
        }
        return NewsList(newsList)
    }

    suspend fun getLatestNewsMain(): NewsList {
        val newsMainList = mutableListOf<News>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("newsMain").get().await()
        for(post in querySnapshot.documents){
            post.toObject(News::class.java)?.let { fbNewsMain ->
                newsMainList.add(fbNewsMain)
            }
        }
        return NewsList(newsMainList)
    }

    suspend fun getLatestMatchs(): MatchsList {
        val matchsList = mutableListOf<Matchs>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("matchs").get().await()

        for(post in querySnapshot.documents){
            post.toObject(Matchs::class.java)?.let { fbMatchs ->
                matchsList.add(fbMatchs)
            }
        }
        return MatchsList(matchsList)
    }

}
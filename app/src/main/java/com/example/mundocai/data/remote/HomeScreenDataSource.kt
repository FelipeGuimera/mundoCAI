package com.example.mundocai.data.remote

import com.example.mundocai.core.Resource
import com.example.mundocai.data.model.News
import com.example.mundocai.data.model.NewsMain
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestNews(): Resource<List<News>> {
        val newsList = mutableListOf<News>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("news").get().await()
        for(post in querySnapshot.documents){
            post.toObject(News::class.java)?.let { fbNews ->
                newsList.add(fbNews)
            }
        }
        return Resource.Success(newsList)
    }

    suspend fun getLatestNewsMain(): Resource<List<NewsMain>> {
        val newsMainList = mutableListOf<NewsMain>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("newsMain").get().await()
        for(post in querySnapshot.documents){
            post.toObject(NewsMain::class.java)?.let { fbNewsMain ->
                newsMainList.add(fbNewsMain)
            }
        }
        return Resource.Success(newsMainList)
    }


}
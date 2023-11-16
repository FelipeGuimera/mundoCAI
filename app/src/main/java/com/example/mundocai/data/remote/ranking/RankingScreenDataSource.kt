package com.example.mundocai.data.remote.ranking

import com.example.mundocai.data.model.Matchs
import com.example.mundocai.data.model.MatchsList
import com.example.mundocai.data.model.User
import com.example.mundocai.data.model.UserList
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class RankingScreenDataSource {


    suspend fun getRanking(index: Int): UserList {
        val userList = mutableListOf<User>()

        val querySnapshot = FirebaseFirestore.getInstance().collection("users")
            .orderBy("points", Query.Direction.DESCENDING)
            .get()
            .await()

        // Verificar si el índice es menor que el tamaño de la lista para obtener usuarios desde el índice 3
        if (index < querySnapshot.documents.size) {
            for (i in index until querySnapshot.documents.size) {
                val document = querySnapshot.documents[i]
                val user = document.toObject(User::class.java)
                user?.let {
                    userList.add(it)
                }
            }
        }

        return UserList(userList)
    }

    suspend fun getPodium(): UserList {
        val userList = mutableListOf<User>()

        val querySnapshot = FirebaseFirestore.getInstance().collection("users")
            .orderBy("points", Query.Direction.DESCENDING)
            .limit(3) // Limitar la consulta a los primeros tres usuarios
            .get()
            .await()

        for (document in querySnapshot.documents) {
            val user = document.toObject(User::class.java)
            user?.let {
                userList.add(it)
            }
        }

        return UserList(userList)
    }



}






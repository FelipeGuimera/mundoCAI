package com.example.mundocai.domain.auth

import com.google.firebase.auth.FirebaseUser

interface AuthRepo {
    suspend fun signIn(email: String, password: String): FirebaseUser?
    suspend fun signUp(email: String, password: String, username: String, profilePicture:String, points:Int): FirebaseUser?
    suspend fun signAnonymous(): FirebaseUser?
    suspend fun saveUsername(username: String)

}
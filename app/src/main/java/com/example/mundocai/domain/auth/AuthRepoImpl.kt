package com.example.mundocai.domain.auth

import com.example.mundocai.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser
import de.hdodenhof.circleimageview.CircleImageView

class AuthRepoImpl(private val dataSource: AuthDataSource) : AuthRepo {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        dataSource.signIn(email, password)

    override suspend fun signUp(email: String, password: String, username: String, profilePicture:String, points:Int): FirebaseUser? =
        dataSource.signUp(email, password, username, profilePicture, points)

    override suspend fun signAnonymous(): FirebaseUser? = dataSource.signAnonymous()

    override suspend fun saveUsername(username: String) = dataSource.saveUsername(username)


}
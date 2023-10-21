package com.example.mundocai.domain.auth

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseUser
import de.hdodenhof.circleimageview.CircleImageView

interface AuthRepo {
    suspend fun signIn(email: String, password: String): FirebaseUser?
    suspend fun signUp(email: String, password: String, username: String): FirebaseUser?
    suspend fun signAnonymous(): FirebaseUser?
    suspend fun saveUsername(username: String)
    suspend fun saveAvatar(imageBitmap: Bitmap?)
}
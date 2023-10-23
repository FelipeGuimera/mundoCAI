package com.example.mundocai.data.remote.auth

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.example.mundocai.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class AuthDataSource {

    suspend fun signIn(email: String, password: String): FirebaseUser? {
        val authResult =
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return authResult.user
    }

    suspend fun singUp(email: String, password: String, username: String): FirebaseUser? {
        val authResult =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        authResult.user?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("users").document(uid)
                .set(User(email, username)).await()
        }

        saveUsername(username)

        return authResult.user
    }

    suspend fun signAnonymous(): FirebaseUser? {
        return try {
            val authResult = FirebaseAuth.getInstance().signInAnonymously().await()
            authResult.user
        } catch (e: Exception) {
            Log.e("Auth", "Error en la autenticación anónima: ${e.message}")
            null // Devolver null en caso de error
        }
    }

    suspend fun saveUsername(username: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .build()
        user?.updateProfile(profileUpdates)?.await()
    }

    suspend fun saveAvatar(imageBitmap: Bitmap?) {
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null && imageBitmap != null) {
            val imageRef =
                FirebaseStorage.getInstance().reference.child("${user.email}/profile_picture")

            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            // Sube la imagen a Firebase Storage
            imageRef.putBytes(data).await()

            // Obtiene la URL de descarga de la imagen cargada
            val downloadUrl = imageRef.downloadUrl.await().toString()

            // Actualiza la foto de perfil en el perfil del usuario
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(downloadUrl))
                .build()

            user.updateProfile(profileUpdates)
        }

    }


}
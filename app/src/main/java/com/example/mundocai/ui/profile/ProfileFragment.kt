package com.example.mundocai.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.model.User
import com.example.mundocai.data.remote.auth.AuthDataSource
import com.example.mundocai.databinding.FragmentProfileBinding
import com.example.mundocai.databinding.FragmentQuizBinding
import com.example.mundocai.domain.auth.AuthRepoImpl
import com.example.mundocai.presentation.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModel.AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource()
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        // Verifica si el usuario ha iniciado sesión
        if (currentUser != null) {
            val username =
                FirebaseAuth.getInstance().currentUser?.displayName // Obtener el nombre del usuario
            val email = FirebaseAuth.getInstance().currentUser?.email

            binding.username.text = username ?: "Nombre de usuario no disponible"
            binding.email.text = email ?: "Email no disponible"

        } else {
            // Manejar el caso en el que el usuario no haya iniciado sesión
            binding.username.text = "Usuario no identificado"
        }

        val imageProfile = FirebaseAuth.getInstance().currentUser?.photoUrl

        if (imageProfile != null) {
            Glide.with(this).load(imageProfile).centerCrop().into(binding.avatarProfile)
        } else {
            // Mostrar una imagen de placeholder o un texto en lugar de la foto
            // Por ejemplo, puedes usar un recurso de imagen en tu proyecto o un recurso de texto.
            Glide.with(this).load(R.drawable.ricardobochini).centerCrop()
                .into(binding.avatarProfile)


        }


    }

    fun updatePhotoUrlInFirestore(photoUrl: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userReference = currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("users").document(uid)
        }

        userReference?.update("profilePicture", photoUrl)
    }
}




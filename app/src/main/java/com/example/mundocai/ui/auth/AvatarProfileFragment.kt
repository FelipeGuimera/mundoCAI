package com.example.mundocai.ui.auth

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.data.remote.auth.AuthDataSource
import com.example.mundocai.databinding.FragmentAvatarProfileBinding
import com.example.mundocai.domain.auth.AuthRepoImpl
import com.example.mundocai.presentation.auth.AuthViewModel
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await


class AvatarProfileFragment : Fragment(R.layout.fragment_avatar_profile) {

    private lateinit var binding: FragmentAvatarProfileBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModel.AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource()
            )
        )
    }

    private var storageReference: StorageReference? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAvatarProfileBinding.bind(view)

        val fragmentContext = requireContext() // Captura el contexto del fragmento

        // Define a function to set the storage reference
        fun setStorageReference(imageId: Int) {
            storageReference =
                FirebaseStorage.getInstance().getReference("avatar/$imageId.png")
        }

        binding.imgProfile1.setOnClickListener {
            setStorageReference(1)
        }

        binding.imgProfile2.setOnClickListener {
            setStorageReference(2)
        }

        binding.imgProfile3.setOnClickListener {
            setStorageReference(3)
        }

        binding.imgProfile4.setOnClickListener {
            setStorageReference(4)
        }

        binding.imgProfile5.setOnClickListener {
            setStorageReference(5)
        }

        binding.imgProfile6.setOnClickListener {
            setStorageReference(6)

        }

        binding.selectImage.setOnClickListener {
            val user = Firebase.auth.currentUser
            storageReference?.downloadUrl
                ?.addOnSuccessListener { uri ->
                    // Actualiza el perfil del usuario con la URL de la imagen
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setPhotoUri(uri)
                        .build()
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateProfileTask ->
                            if (updateProfileTask.isSuccessful) {
                                // Navega a la siguiente pantalla o realiza alguna otra acción
                                findNavController().navigate(R.id.action_avatarProfileFragment_to_homeFragment)

                            }
                        }
                }
        }


        binding.skip.setOnClickListener {
            // Navega a la siguiente pantalla o realiza alguna otra acción
            findNavController().navigate(R.id.action_avatarProfileFragment_to_homeFragment)
        }
    }
}






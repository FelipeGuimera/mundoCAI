package com.example.mundocai.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentAvatarProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView


class AvatarProfileFragment : Fragment(R.layout.fragment_avatar_profile) {

    private lateinit var binding: FragmentAvatarProfileBinding

    private var storageReference: StorageReference? = null
    private var selectedAvatar: CircleImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAvatarProfileBinding.bind(view)


        fun setStorageReference(imageId: Int) {
            storageReference =
                FirebaseStorage.getInstance().getReference("avatar/$imageId.png")
        }

        fun selectAvatar(imageView: CircleImageView) {
            // Primero, restaura el borde del avatar anterior (si hay uno)
            selectedAvatar?.borderColor = 0xFF000000.toInt()

            // Establece el nuevo avatar seleccionado y cambia su borde
            selectedAvatar = imageView
            selectedAvatar?.borderColor = 0xFFEC1C24.toInt()
        }


        binding.imgProfile1.setOnClickListener {
            setStorageReference(1)
            selectAvatar(binding.imgProfile1)
        }

        binding.imgProfile2.setOnClickListener {
            setStorageReference(2)
            selectAvatar(binding.imgProfile2)
        }

        binding.imgProfile3.setOnClickListener {
            setStorageReference(3)
            selectAvatar(binding.imgProfile3)
        }

        binding.imgProfile4.setOnClickListener {
            setStorageReference(4)
            selectAvatar(binding.imgProfile4)
        }

        binding.imgProfile5.setOnClickListener {
            setStorageReference(5)
            selectAvatar(binding.imgProfile5)
        }

        binding.imgProfile6.setOnClickListener {
            setStorageReference(6)
            selectAvatar(binding.imgProfile6)

        }

        binding.selectImage.setOnClickListener {
            // Deshabilitar el botón mientras se realizan las operaciones
            binding.selectImage.isEnabled = false

            val user = Firebase.auth.currentUser
            storageReference?.downloadUrl
                ?.addOnSuccessListener { uri ->
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setPhotoUri(uri)
                        .build()
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateProfileTask ->
                            if (updateProfileTask.isSuccessful) {
                                val currentUser = FirebaseAuth.getInstance().currentUser
                                val userReference = currentUser?.uid?.let { uid ->
                                    FirebaseFirestore.getInstance().collection("users").document(uid)
                                }
                                val imageProfile = FirebaseAuth.getInstance().currentUser?.photoUrl
                                userReference?.update("profilePicture", imageProfile)
                                findNavController().navigate(R.id.action_avatarProfileFragment_to_homeFragment)
                            }

                            // Habilitar el botón después de que se complete el proceso
                            binding.selectImage.isEnabled = true
                        }
                }
                ?.addOnFailureListener { exception ->
                    // Manejar cualquier error de descarga de URL aquí

                    // Habilitar el botón en caso de error
                    binding.selectImage.isEnabled = true
                }
        }


        binding.skip.setOnClickListener {
            // Navega a la siguiente pantalla o realiza alguna otra acción
            findNavController().navigate(R.id.action_avatarProfileFragment_to_homeFragment)
        }
    }



}






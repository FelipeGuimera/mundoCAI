package com.example.mundocai.ui.auth

import android.app.AlertDialog
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.remote.auth.AuthDataSource
import com.example.mundocai.databinding.FragmentAvatarProfileBinding
import com.example.mundocai.domain.auth.AuthRepoImpl
import com.example.mundocai.presentation.auth.AuthViewModel
import com.example.mundocai.presentation.auth.AuthViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream


class AvatarProfileFragment : Fragment(R.layout.fragment_avatar_profile) {

    private lateinit var binding: FragmentAvatarProfileBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory(
        AuthRepoImpl(
        AuthDataSource()
    )
    ) }

    private var bitmap: Bitmap? = null


    private var selectedAvatar: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAvatarProfileBinding.bind(view)


        val avatarImageViews = listOf(
            view.findViewById<CircleImageView>(R.id.img_profile1),
            view.findViewById<CircleImageView>(R.id.img_profile2),
            view.findViewById<CircleImageView>(R.id.img_profile3),
            view.findViewById<CircleImageView>(R.id.img_profile4),
            view.findViewById<CircleImageView>(R.id.img_profile5),
            view.findViewById<CircleImageView>(R.id.img_profile6)
        )

        // Configura los clics en las imágenes de avatar
        for ((index, avatarImageView) in avatarImageViews.withIndex()) {
            avatarImageView.setOnClickListener {
                // Desmarca el avatar previamente seleccionado
                selectedAvatar?.let { prevSelectedIndex ->
                    avatarImageViews[prevSelectedIndex].borderColor =
                        0xFF000000.toInt() // Restaura el color del borde
                }

                // Marca el avatar seleccionado
                avatarImageView.borderColor =
                    0xFFEC1C24.toInt() // Establece el color del borde en rojo
                selectedAvatar = index
            }
        }

        binding.selectImage.setOnClickListener {

            val alertDialog = AlertDialog.Builder(requireContext()).setTitle("Uploading photo...").create()
            bitmap?.let {
                    viewModel.updateUserProfile(imageBitmap = it).observe(viewLifecycleOwner) { result ->
                        when (result) {
                            is Resource.Loading -> {
                                alertDialog.show()
                            }
                            is Resource.Success -> {
                                alertDialog.dismiss()
                                findNavController().navigate(R.id.action_avatarProfileFragment_to_homeFragment)
                            }
                            is Resource.Failure -> {
                                alertDialog.dismiss()
                            }
                        }
                    }
            }
            }
        }

    }

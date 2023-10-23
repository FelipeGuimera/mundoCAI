package com.example.mundocai.ui.auth

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.data.remote.auth.AuthDataSource
import com.example.mundocai.databinding.FragmentAvatarProfileBinding
import com.example.mundocai.domain.auth.AuthRepoImpl
import com.example.mundocai.presentation.auth.AuthViewModel
import de.hdodenhof.circleimageview.CircleImageView


class AvatarProfileFragment : Fragment(R.layout.fragment_avatar_profile) {

    private lateinit var binding: FragmentAvatarProfileBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModel.AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource()
            )
        )
    }

    private var selectedAvatar: Bitmap? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAvatarProfileBinding.bind(view)


        binding.imgProfile1.setOnClickListener {
            selectedAvatar = BitmapFactory.decodeResource(resources, R.drawable.ricardobochini)
            binding.imgProfile1.setImageBitmap(selectedAvatar)

        }


        binding.imgProfile2.setOnClickListener {
            selectedAvatar = BitmapFactory.decodeResource(resources, R.drawable.ricardobochini)
            binding.imgProfile2.setImageBitmap(selectedAvatar)

        }

        binding.imgProfile3.setOnClickListener {
            selectedAvatar = BitmapFactory.decodeResource(resources, R.drawable.ricardobochini)
            binding.imgProfile3.setImageBitmap(selectedAvatar)

        }

        binding.imgProfile4.setOnClickListener {
            selectedAvatar = BitmapFactory.decodeResource(resources, R.drawable.ricardobochini)
            binding.imgProfile4.setImageBitmap(selectedAvatar)

        }

        binding.imgProfile5.setOnClickListener {
            selectedAvatar = BitmapFactory.decodeResource(resources, R.drawable.ricardobochini)
            binding.imgProfile5.setImageBitmap(selectedAvatar)

        }

        binding.imgProfile6.setOnClickListener {
            selectedAvatar = BitmapFactory.decodeResource(resources, R.drawable.ricardobochini)
            binding.imgProfile6.setImageBitmap(selectedAvatar)
        }


        binding.selectImage.setOnClickListener {
            if (selectedAvatar != null) {
                viewModel.saveAvatar(selectedAvatar)
                // Navega a la siguiente pantalla o realiza alguna otra acción
                findNavController().navigate(R.id.action_avatarProfileFragment_to_homeFragment)
            }

        }

        binding.skip.setOnClickListener {
            // Navega a la siguiente pantalla o realiza alguna otra acción
            findNavController().navigate(R.id.action_avatarProfileFragment_to_homeFragment)
        }



    }


}




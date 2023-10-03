package com.example.mundocai.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentAvatarProfileBinding


class AvatarProfileFragment : Fragment(R.layout.fragment_avatar_profile) {

    private lateinit var binding: FragmentAvatarProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAvatarProfileBinding.bind(view)

    }




}
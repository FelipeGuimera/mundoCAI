package com.example.mundocai.ui.matchs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentMatchsBinding
import com.example.mundocai.databinding.FragmentProfileBinding


class MatchsFragment : Fragment(R.layout.fragment_matchs) {

    private lateinit var binding: FragmentMatchsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchsBinding.bind(view)

    }
}
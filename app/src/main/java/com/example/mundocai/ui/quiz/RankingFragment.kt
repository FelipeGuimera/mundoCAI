package com.example.mundocai.ui.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentRankingBinding

class RankingFragment : Fragment(R.layout.fragment_ranking) {

    private lateinit var binding: FragmentRankingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRankingBinding.bind(view)


    }
}
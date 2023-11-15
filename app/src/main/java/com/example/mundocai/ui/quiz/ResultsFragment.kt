package com.example.mundocai.ui.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentResultsBinding
import com.example.mundocai.ui.details.HistoryDetailsFragmentArgs


class ResultsFragment : Fragment(R.layout.fragment_results) {

    private lateinit var binding: FragmentResultsBinding
    private val args by navArgs<ResultsFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultsBinding.bind(view)

        binding.correctAnswer.text = args.correctAnswers.toString()
        binding.points.text = args.points.toString()


        binding.goHome.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_homeFragment)
        }

        binding.rankingText.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_rankingFragment)
        }

    }
}
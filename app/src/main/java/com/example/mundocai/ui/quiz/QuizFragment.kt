package com.example.mundocai.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentQuizBinding


class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var binding: FragmentQuizBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuizBinding.bind(view)


        binding.playQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_questionsFragment)
        }

        binding.textRanking.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_rankingFragment)
        }


    }

}
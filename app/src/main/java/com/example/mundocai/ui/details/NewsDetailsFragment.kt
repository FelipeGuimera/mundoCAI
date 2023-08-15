package com.example.mundocai.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    private lateinit var binding: FragmentNewsDetailsBinding
    private val args by navArgs<NewsDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailsBinding.bind(view)

            binding.backButton.setOnClickListener {
            // Aquí navega al home fragment usando NavController
            findNavController().navigate(R.id.action_newsDetailsFragment_to_homeFragment)
        }

        Glide.with(requireContext()).load(args.imageNews).centerCrop().into(binding.newsImage)
        binding.title.text = args.titleNews
        binding.txtDescription.text = args.descriptionNews


    }
}
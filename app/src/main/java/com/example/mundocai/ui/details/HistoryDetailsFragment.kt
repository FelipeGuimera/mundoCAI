package com.example.mundocai.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentHistoryDetailsBinding

class HistoryDetailsFragment : Fragment(R.layout.fragment_history_details){
    private lateinit var binding: FragmentHistoryDetailsBinding
    private val args by navArgs<HistoryDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryDetailsBinding.bind(view)

        binding.backButton.setOnClickListener {
            // Aquí navega al home fragment usando NavController
            findNavController().navigate(R.id.action_historyDetailsFragment_to_homeFragment)
        }


        binding.txtDescriptionHistory.text = args.descriptionHistory
        Glide.with(requireContext()).load(args.imageHistory).centerCrop().into(binding.historyImage)
        binding.titleHistory.text = args.tittleHistory.uppercase()


    }
}
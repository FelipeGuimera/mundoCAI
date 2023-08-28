package com.example.mundocai.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mundocai.R
import com.example.mundocai.data.model.ImagesCarousel
import com.example.mundocai.databinding.FragmentHistoryDetailsBinding
import com.example.mundocai.databinding.FragmentImagesCarouselBinding

class ImagesCarouselFragment : Fragment(R.layout.fragment_images_carousel) {

    private lateinit var binding: FragmentImagesCarouselBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesCarouselBinding.bind(view)




    }
}
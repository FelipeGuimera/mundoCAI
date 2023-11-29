package com.example.mundocai.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mundocai.R
import com.example.mundocai.data.model.ImagesCarousel
import com.example.mundocai.databinding.FragmentHistoryDetailsBinding
import com.example.mundocai.databinding.FragmentImagesCarouselBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class ImagesCarouselFragment : Fragment(R.layout.fragment_images_carousel) {

    private lateinit var binding: FragmentImagesCarouselBinding
    private val list = mutableListOf<CarouselItem>()
    private val args by navArgs<ImagesCarouselFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesCarouselBinding.bind(view)


        binding.backHome.setOnClickListener {
            findNavController().navigate(R.id.action_imagesCarouselFragment_to_homeFragment)
        }

        list.add(CarouselItem(args.imageCarousel1))
        list.add(CarouselItem(args.imageCarousel2))
        list.add(CarouselItem(args.imageCarousel3))

        binding.carousel.addData(list)

        }
    }





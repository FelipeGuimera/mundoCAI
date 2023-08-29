package com.example.mundocai.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesCarouselBinding.bind(view)

        val carousel: ImageCarousel = binding.carousel
        carousel.registerLifecycle(viewLifecycleOwner)

        // Llamar a la función para cargar imágenes y configurar el carrusel
        loadCarouselImages()

    }

    private fun loadCarouselImages() {
        val imagesCarouselList = mutableListOf<CarouselItem>()

        FirebaseFirestore.getInstance().collection("imageCarousel")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val carouselItem = document.toObject(CarouselItem::class.java)
                    imagesCarouselList.add(carouselItem)
                }

                // Configurar el carrusel con los datos obtenidos
                binding.carousel.addData(imagesCarouselList)


            }
            .addOnFailureListener { exception ->
                Log.e("ImagesCarouselFragment", "Error loading carousel images", exception)
            }
    }
}




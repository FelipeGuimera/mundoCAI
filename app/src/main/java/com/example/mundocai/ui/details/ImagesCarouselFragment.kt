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
    private val list = mutableListOf<CarouselItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesCarouselBinding.bind(view)

        val carousel: ImageCarousel = binding.carousel
        list.add(CarouselItem("https://pbs.twimg.com/media/F2O5udrXYAI7QIW?format=jpg&name=large"))
        list.add(CarouselItem("https://pbs.twimg.com/media/F2PIjViXwAE4m3e?format=jpg&name=4096x4096"))
        list.add(CarouselItem("https://soydelrojo.com/wp-content/uploads/2023/07/Independiente-cerro-su-participacion-en-el-torneo.jpg"))

        carousel.addData(list)

    }

    private suspend fun loadCarouselImages(){
        val querySnapshot = FirebaseFirestore.getInstance().collection("images").document().collection("imagesCarousel").get().await()
        for (post in querySnapshot.documents){
            post.toObject(CarouselItem::class.java)?.let { fbImagesCarousel->
                list.add(fbImagesCarousel)
            }
        }
    }




}




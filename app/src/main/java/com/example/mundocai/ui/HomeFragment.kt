package com.example.mundocai.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.model.*
import com.example.mundocai.data.remote.home.HomeScreenDataSource
import com.example.mundocai.databinding.FragmentHomeBinding
import com.example.mundocai.domain.home.HomeScreenRepoImpl
import com.example.mundocai.presentation.HomeScreenViewModel
import com.example.mundocai.presentation.HomeScreenViewModelFactory
import com.example.mundocai.ui.home.adapter.*
import com.example.mundocai.ui.home.adapter.concat.*


class HomeFragment : Fragment(R.layout.fragment_home), MatchsHomeAdapters.OnMatchsClickListener{

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeScreenViewModel> { HomeScreenViewModelFactory(
        HomeScreenRepoImpl(
        HomeScreenDataSource()
    )
    )  }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchLatestNews().observe(viewLifecycleOwner, Observer{  result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0,MatchsConcatAdapter(MatchsHomeAdapters(result.data.t1.results, this@HomeFragment)))
                        addAdapter(1,NewsMainConcatAdapter(NewsMediumAdapter(result.data.t2.results, this@HomeFragment)))
                        addAdapter(2, NewsConcatAdapter(NewsSmallAdapter(result.data.t3.results, this@HomeFragment)))
                        addAdapter(3, HistoryConcatAdapter(HistoryHomeAdapter(result.data.t4.results, this@HomeFragment)))
                        addAdapter(4, InviteConcatAdapter(InviteHomeAdapter(result.data.t5.results, this@HomeFragment)))
                        addAdapter(5, ImagesConcatAdapter(ImagesHomeAdapter(result.data.t6.results, this@HomeFragment)))

                    }
                    binding.rvHome.adapter = concatAdapter
                }

                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

    fun OnNewsClick(news: News) {
        val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(
            news.description_news,
            news.image_news,
            news.title_news,
        )
        findNavController().navigate(action)
    }

    override fun OnMatchsClick(matchs: Matchs) {
        findNavController()
            .navigate(R.id.matchsFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.homeFragment, true)
                    .build()
            )
    }

    fun OnHistoryClick(history: History) {
        val action = HomeFragmentDirections.actionHomeFragmentToHistoryDetailsFragment(
            history.description_history,
            history.image_history,
            history.title_history,

        )
        findNavController().navigate(action)
    }

     fun OnInviteClick(invite: Invite) {
        findNavController()
            .navigate(R.id.quizFragment,
                null,
                NavOptions.Builder()
                    .setPopUpTo(R.id.homeFragment, true)
                    .build()
            )
    }


    fun OnImagesClick(images: Images) {
        val action = HomeFragmentDirections.actionHomeFragmentToImagesCarouselFragment(
            images.imageCarousel1,
            images.imageCarousel2,
            images.imageCarousel3,
        )
        findNavController().navigate(action)
    }

}

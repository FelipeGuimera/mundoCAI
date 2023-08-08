package com.example.mundocai.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.model.News
import com.example.mundocai.data.remote.HomeScreenDataSource
import com.example.mundocai.databinding.FragmentHomeBinding
import com.example.mundocai.domain.HomeScreenRepoImpl
import com.example.mundocai.presentation.HomeScreenViewModel
import com.example.mundocai.presentation.HomeScreenViewModelFactory
import com.example.mundocai.ui.home.adapter.*
import com.example.mundocai.ui.home.adapter.concat.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeScreenViewModel> { HomeScreenViewModelFactory(HomeScreenRepoImpl(
        HomeScreenDataSource()
    ))  }

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
                        addAdapter(0,MatchsConcatAdapter(MatchsHomeAdapters(result.data.t1.results)))
                        addAdapter(1,NewsMainConcatAdapter(NewsMediumAdapter(result.data.t2.results)))
                        addAdapter(2, NewsConcatAdapter(NewsSmallAdapter(result.data.t3.results)))
                        addAdapter(3, HistoryConcatAdapter(HistoryHomeAdapter(result.data.t4.results)))
                        addAdapter(4, InviteConcatAdapter(InviteHomeAdapter(result.data.t5.results)))

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


}

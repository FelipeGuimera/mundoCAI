package com.example.mundocai.ui.ranking

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.remote.ranking.RankingScreenDataSource
import com.example.mundocai.databinding.FragmentRankingBinding
import com.example.mundocai.domain.ranking.RankingScreenRepoImpl
import com.example.mundocai.presentation.ranking.RankingScreenViewModel
import com.example.mundocai.presentation.ranking.RankingScreenViewModelFactory
import com.example.mundocai.ui.matchs.adapter.MatchsAdapter
import com.example.mundocai.ui.matchs.adapter.concat.MatchsSectionConcatAdapter
import com.example.mundocai.ui.ranking.adapter.PodiumAdapter
import com.example.mundocai.ui.ranking.adapter.RankingAdapter
import com.example.mundocai.ui.ranking.adapter.concat.RankingConcatAdapter

class RankingFragment : Fragment(R.layout.fragment_ranking) {

    private lateinit var binding: FragmentRankingBinding
    private val viewModel by viewModels<RankingScreenViewModel> {
        RankingScreenViewModelFactory(
            RankingScreenRepoImpl(
                RankingScreenDataSource()
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRankingBinding.bind(view)


        concatAdapter = ConcatAdapter()



        viewModel.getAllRanking().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                    concatAdapter.apply {
                        addAdapter((PodiumAdapter(result.data.first.results)))
                        addAdapter(RankingAdapter(result.data.second.results))
                    }
                    binding.rvRanking.adapter = concatAdapter
                }
                is Resource.Failure -> {
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
package com.example.mundocai.ui.matchs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.mundocai.MainActivity
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.remote.home.HomeScreenDataSource
import com.example.mundocai.data.remote.matchs.MatchsScreenDataSource
import com.example.mundocai.databinding.FragmentMatchsBinding
import com.example.mundocai.databinding.FragmentProfileBinding
import com.example.mundocai.domain.home.HomeScreenRepoImpl
import com.example.mundocai.domain.matchs.MatchsScreenRepoImpl
import com.example.mundocai.presentation.HomeScreenViewModel
import com.example.mundocai.presentation.HomeScreenViewModelFactory
import com.example.mundocai.presentation.matchs.MatchsScreenViewModel
import com.example.mundocai.presentation.matchs.MatchsScreenViewModelFactory
import com.example.mundocai.ui.home.adapter.MatchsHomeAdapters
import com.example.mundocai.ui.home.adapter.concat.MatchsConcatAdapter
import com.example.mundocai.ui.matchs.adapter.MatchsAdapter
import com.example.mundocai.ui.matchs.adapter.concat.MatchsSectionConcatAdapter


class MatchsFragment : Fragment(R.layout.fragment_matchs) {

    private lateinit var binding: FragmentMatchsBinding
    private val viewModel by viewModels<MatchsScreenViewModel> {
        MatchsScreenViewModelFactory(
            MatchsScreenRepoImpl(
                MatchsScreenDataSource()
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchsBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchLatestMatchs().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(MatchsSectionConcatAdapter(MatchsAdapter(result.data.results)))
                    }
                    binding.rvMatchs.adapter = concatAdapter
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
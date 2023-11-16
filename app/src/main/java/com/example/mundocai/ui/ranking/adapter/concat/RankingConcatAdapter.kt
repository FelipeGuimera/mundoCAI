package com.example.mundocai.ui.ranking.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.FragmentRankingBinding
import com.example.mundocai.ui.ranking.adapter.RankingAdapter

class RankingConcatAdapter (private val rankingAdapter: RankingAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            FragmentRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(rankingAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: FragmentRankingBinding) :
        BaseConcatHolder<RankingAdapter>(binding.root) {
        override fun bind(adapter: RankingAdapter) {
            binding.rvRanking.adapter = adapter

        }
    }
}
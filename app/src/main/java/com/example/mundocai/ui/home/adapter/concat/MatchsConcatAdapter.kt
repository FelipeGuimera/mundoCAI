package com.example.mundocai.ui.home.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.MatchHomeRowBinding
import com.example.mundocai.ui.home.adapter.MatchsHomeAdapters
import com.example.mundocai.ui.home.adapter.NewsSmallAdapter

class MatchsConcatAdapter(private val matchsAdapter: MatchsHomeAdapters) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            MatchHomeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(matchsAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: MatchHomeRowBinding) :
        BaseConcatHolder<MatchsHomeAdapters>(binding.root) {
        override fun bind(adapter: MatchsHomeAdapters) {
            binding.rvMatchsHome.adapter = adapter
        }
    }
}
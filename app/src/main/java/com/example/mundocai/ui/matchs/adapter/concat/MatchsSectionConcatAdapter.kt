package com.example.mundocai.ui.matchs.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.FragmentMatchsBinding
import com.example.mundocai.databinding.MatchHomeRowBinding
import com.example.mundocai.ui.home.adapter.MatchsHomeAdapters
import com.example.mundocai.ui.matchs.adapter.MatchsAdapter

class MatchsSectionConcatAdapter (private val matchsAdapter: MatchsAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            FragmentMatchsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(matchsAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: FragmentMatchsBinding) :
        BaseConcatHolder<MatchsAdapter>(binding.root) {
        override fun bind(adapter: MatchsAdapter) {
            binding.rvMatchs.adapter = adapter
        }
    }
}
package com.example.mundocai.ui.home.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.NewsHomeRowBinding
import com.example.mundocai.ui.home.adapter.NewsSmallAdapter

class NewsConcatAdapter(private val newsAdapter: NewsSmallAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            NewsHomeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(newsAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: NewsHomeRowBinding) :
        BaseConcatHolder<NewsSmallAdapter>(binding.root) {
        override fun bind(adapter: NewsSmallAdapter) {
            binding.rvNews.adapter = adapter
        }
    }
}
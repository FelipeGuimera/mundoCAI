package com.example.mundocai.ui.home.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.NewsHomeRowBinding
import com.example.mundocai.ui.home.adapter.NewsAdapter
import com.example.mundocai.ui.home.adapter.NewsMainAdapter

class NewsConcatAdapter(private val newsAdapter: NewsAdapter) : RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = NewsHomeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(newsAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: NewsHomeRowBinding) : BaseConcatHolder<NewsAdapter>(binding.root) {
        override fun bind(adapter: NewsAdapter) {
            binding.rvNews.adapter = adapter
        }
    }
}
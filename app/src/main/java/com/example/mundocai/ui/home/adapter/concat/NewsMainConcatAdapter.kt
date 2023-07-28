package com.example.mundocai.ui.home.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.NewsMainHomeRowBinding
import com.example.mundocai.ui.home.adapter.NewsAdapter
import com.example.mundocai.ui.home.adapter.NewsMainAdapter

class NewsMainConcatAdapter (private val newsMainAdapter: NewsMainAdapter) : RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = NewsMainHomeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(newsMainAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: NewsMainHomeRowBinding) : BaseConcatHolder<NewsMainAdapter>(binding.root) {
        override fun bind(adapter: NewsMainAdapter) {
            binding.rvNewsMain.adapter = adapter
        }
    }
}
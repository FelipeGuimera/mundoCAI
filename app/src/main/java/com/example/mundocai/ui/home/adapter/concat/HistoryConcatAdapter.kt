package com.example.mundocai.ui.home.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.HistoryHomeRowBinding
import com.example.mundocai.ui.home.adapter.HistoryHomeAdapter

class HistoryConcatAdapter (private val historyAdapter: HistoryHomeAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            HistoryHomeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(historyAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: HistoryHomeRowBinding) :
        BaseConcatHolder<HistoryHomeAdapter>(binding.root) {
        override fun bind(adapter: HistoryHomeAdapter) {
            binding.rvHistory.adapter = adapter
        }
    }
}
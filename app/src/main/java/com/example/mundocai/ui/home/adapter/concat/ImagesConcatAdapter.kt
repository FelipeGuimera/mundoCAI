package com.example.mundocai.ui.home.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.ImagesHomeRowBinding
import com.example.mundocai.ui.home.adapter.ImagesHomeAdapter

class ImagesConcatAdapter (private val imageAdapter: ImagesHomeAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            ImagesHomeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(imageAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: ImagesHomeRowBinding) :
        BaseConcatHolder<ImagesHomeAdapter>(binding.root) {
        override fun bind(adapter: ImagesHomeAdapter) {
            binding.rvImgHome.adapter = adapter
        }
    }
}
package com.example.mundocai.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.History
import com.example.mundocai.databinding.HistoryItemBinding

class HistoryHomeAdapter (private val historyList: List<History>): RecyclerView.Adapter<BaseViewHolder<*>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = HistoryViewHolder(itemBinding, parent.context)

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is HistoryViewHolder->holder.bind(historyList[position])
        }
    }

    override fun getItemCount(): Int = historyList.size


    private inner class HistoryViewHolder(val binding: HistoryItemBinding, val context: Context): BaseViewHolder<History>(binding.root){
        override fun bind(item: History) {
            Glide.with(context).load(item.image_history).centerCrop().into(binding.historyImage)
            Glide.with(context).load(item.cai_image).centerCrop().into(binding.caiImage)
            Glide.with(context).load(item.rival_image).centerCrop().into(binding.rivalImage)
        }

    }


}
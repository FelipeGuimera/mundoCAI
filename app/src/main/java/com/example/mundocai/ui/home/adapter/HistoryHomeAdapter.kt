package com.example.mundocai.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.History
import com.example.mundocai.data.model.Invite
import com.example.mundocai.databinding.HistoryItemBinding
import com.example.mundocai.ui.HomeFragment

class HistoryHomeAdapter (private val historyList: List<History>, private val itemClickListener: HomeFragment): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnHistoryClickListener{
        fun OnHistoryClick(history: History)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = HistoryViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.OnHistoryClick(historyList[position])
        }

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
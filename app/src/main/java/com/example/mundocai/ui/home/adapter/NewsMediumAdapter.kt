package com.example.mundocai.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.News
import com.example.mundocai.databinding.NewsItemMainBinding


class NewsMediumAdapter (private val newsList: List<News>, private val itemClickListener: OnNewsClickListener): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnNewsClickListener{
        fun OnNewsClick(news: News)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = NewsItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = NewsViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.OnNewsClick(newsList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is NewsViewHolder->holder.bind(newsList[position])
        }
    }

    override fun getItemCount(): Int = newsList.size


    private inner class NewsViewHolder(val binding: NewsItemMainBinding, val context: Context): BaseViewHolder<News>(binding.root){
        override fun bind(item: News) {
            Glide.with(context).load(item.image_news).centerCrop().into(binding.newsImageMain)
            binding.newsMainTittle.text = item.title_news

        }

    }


}



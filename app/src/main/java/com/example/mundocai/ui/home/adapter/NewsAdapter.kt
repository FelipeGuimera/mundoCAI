package com.example.mundocai.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.News
import com.example.mundocai.databinding.NewsItemBinding

class NewsAdapter (private val newsList: List<News>): RecyclerView.Adapter<BaseViewHolder<*>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeScreenViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is HomeScreenViewHolder -> holder.bind(newsList[position])
        }
    }

    override fun getItemCount(): Int  = newsList.size

}

private class HomeScreenViewHolder(
    val binding: NewsItemBinding,
    val context: Context
): BaseViewHolder<News>(binding.root){
    override fun bind(item: News) {
        Glide.with(context).load(item.image_news).centerCrop().into(binding.newsImage)
        binding.newsTittle.text = item.title_news

    }
}

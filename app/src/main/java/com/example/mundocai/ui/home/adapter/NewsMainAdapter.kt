package com.example.mundocai.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.NewsMain
import com.example.mundocai.databinding.NewsItemMainBinding


class NewsMainAdapter (private val newsMainList: List<NewsMain>): RecyclerView.Adapter<BaseViewHolder<*>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemMainBinding = NewsItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsMainViewHolder(itemMainBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is NewsMainViewHolder -> holder.bind(newsMainList[position])
        }
    }

    override fun getItemCount(): Int  = newsMainList.size

}

private class NewsMainViewHolder(
    val binding: NewsItemMainBinding,
    val context: Context
): BaseViewHolder<NewsMain>(binding.root){
    override fun bind(item: NewsMain) {
        Glide.with(context).load(item.image_mainNews).centerCrop().into(binding.newsImageMain)
        binding.newsMainTittle.text = item.title_mainNews

    }
}
package com.example.mundocai.ui.ranking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.User
import com.example.mundocai.databinding.UserItemBinding

class RankingAdapter (private val rankingList: List<User>): RecyclerView.Adapter<BaseViewHolder<*>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = RankingViewHolder(itemBinding, parent.context)


        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is RankingViewHolder ->holder.bind(rankingList[position])
        }
    }

    override fun getItemCount(): Int = rankingList.size


    private inner class RankingViewHolder(val binding: UserItemBinding, val context: Context): BaseViewHolder<User>(binding.root){
        override fun bind(item: User) {

            Glide.with(context).load(item.profilePicture).centerCrop().into(binding.userImageView)
            binding.usernameTextView.text = item.username
            binding.scoreTextView.text = item.points.toString()
            binding.positionTextView.text = (position + 3).toString()


        }

    }


}
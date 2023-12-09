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


    override fun getItemCount(): Int {
        // Devuelve el mínimo entre el tamaño de la lista y 15
        return minOf(rankingList.size, 47)
    }

    private inner class RankingViewHolder(val binding: UserItemBinding, val context: Context) :
        BaseViewHolder<User>(binding.root) {
        override fun bind(item: User) {
            Glide.with(context).load(item.profilePicture).centerCrop().into(binding.userImageView)
            binding.usernameTextView.text = item.username
            binding.scoreTextView.text = item.points.toString()
            // La posición se muestra sumando 1 a la posición actual
            binding.positionTextView.text = (bindingAdapterPosition + 4).toString()
        }
    }
}



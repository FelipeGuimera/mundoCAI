package com.example.mundocai.ui.ranking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.User
import com.example.mundocai.databinding.PodioItemBinding
import com.example.mundocai.databinding.UserItemBinding

class PodiumAdapter(private val podiumList: List<User>) :
    RecyclerView.Adapter<PodiumAdapter.PodiumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodiumViewHolder {
        val itemBinding =
            PodioItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PodiumViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PodiumViewHolder, position: Int) {
        if (position == 0 && podiumList.size >= 3) {
            holder.bind(podiumList[0], podiumList[1], podiumList[2])
        }
    }

    override fun getItemCount(): Int {
        return if (podiumList.size >= 3) 1 else 0 // Mostrar solo un podio completo si hay al menos tres usuarios
    }

    inner class PodiumViewHolder(private val binding: PodioItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user1: User, user2: User, user3: User) {
            Glide.with(binding.root.context)
                .load(user1.profilePicture)
                .centerCrop()
                .into(binding.firstPositionsImage)
            binding.firtsPositionName.text = user1.username
            binding.firstPositionPoints.text = user1.points.toString()

            Glide.with(binding.root.context)
                .load(user2.profilePicture)
                .centerCrop()
                .into(binding.secondPositionImage)
            binding.secondPositionName.text = user2.username
            binding.secondPositionPoints.text = user2.points.toString()

            Glide.with(binding.root.context)
                .load(user3.profilePicture)
                .centerCrop()
                .into(binding.thirdPositionImage)
            binding.thirdPositionName.text = user3.username
            binding.thirdPositionPoints.text = user3.points.toString()
        }
    }
}



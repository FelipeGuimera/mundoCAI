package com.example.mundocai.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.Invite
import com.example.mundocai.databinding.MatchItemBinding
import com.example.mundocai.databinding.QuizItemHomeBinding

class InviteHomeAdapter (private val inviteList: List<Invite>): RecyclerView.Adapter<BaseViewHolder<*>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = QuizItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = InviteViewHolder(itemBinding, parent.context)

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is InviteViewHolder->holder.bind(inviteList[position])
        }
    }

    override fun getItemCount(): Int = inviteList.size


    private inner class InviteViewHolder(val binding: QuizItemHomeBinding, val context: Context): BaseViewHolder<Invite>(binding.root){
        override fun bind(item: Invite) {
            Glide.with(context).load(item.invite_img).centerCrop().into(binding.inviteImg)
            binding.inviteText.text = item.invite_txt

        }

    }


}
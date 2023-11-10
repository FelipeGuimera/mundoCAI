package com.example.mundocai.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.Matchs
import com.example.mundocai.data.model.News
import com.example.mundocai.databinding.MatchItemBinding
import com.example.mundocai.ui.HomeFragment

class MatchsHomeAdapters (private val matchsList: List<Matchs>, private val itemClickListener: OnMatchsClickListener): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnMatchsClickListener{
        fun OnMatchsClick(matchs: Matchs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MatchsViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.OnMatchsClick(matchsList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MatchsViewHolder->holder.bind(matchsList[position])
        }
    }

    override fun getItemCount(): Int = minOf(5, matchsList.size)


    private inner class MatchsViewHolder(val binding: MatchItemBinding, val context: Context): BaseViewHolder<Matchs>(binding.root){
        override fun bind(item: Matchs) {
            Glide.with(context).load(item.local_team_image).centerCrop().into(binding.localTeamImg)
            Glide.with(context).load(item.away_team_image).centerCrop().into(binding.awayTeamImg)
            binding.gameDay.text = item.game_day
            binding.stadium.text = item.stadium_name
            binding.localScore.text = item.local_score
            binding.lOrV.text = item.l_or_v
            binding.awayScore.text = item.away_score

        }

    }

}
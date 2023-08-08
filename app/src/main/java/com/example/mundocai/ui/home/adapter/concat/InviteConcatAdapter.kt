package com.example.mundocai.ui.home.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mundocai.core.BaseConcatHolder
import com.example.mundocai.databinding.QuizInviteRowBinding
import com.example.mundocai.ui.home.adapter.InviteHomeAdapter

class InviteConcatAdapter (private val inviteAdapter: InviteHomeAdapter) :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            QuizInviteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(inviteAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(private val binding: QuizInviteRowBinding) :
        BaseConcatHolder<InviteHomeAdapter>(binding.root) {
        override fun bind(adapter: InviteHomeAdapter) {
            binding.rvQuizInvite.adapter = adapter
        }
    }
}
package com.example.mundocai.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mundocai.core.BaseViewHolder
import com.example.mundocai.data.model.Images
import com.example.mundocai.data.model.Invite
import com.example.mundocai.databinding.ImagesItemBinding
import com.example.mundocai.ui.HomeFragment

class ImagesHomeAdapter (private val imageList: List<Images>, private val itemClickListener: HomeFragment): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnImagesClickListener {
        fun OnImagesClick(images: Images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ImagesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ImagesViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.OnImagesClick(imageList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ImagesViewHolder -> holder.bind(imageList[position])
        }
    }

    override fun getItemCount(): Int = imageList.size


    private inner class ImagesViewHolder(val binding: ImagesItemBinding, val context: Context) :
        BaseViewHolder<Images>(binding.root) {
        override fun bind(item: Images) {
            Glide.with(context).load(item.image).centerCrop().into(binding.img)
            binding.imgTitle.text = item.txt_image

        }

    }
}
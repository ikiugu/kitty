package com.ikiugu.kitty.ui.categories.display

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ikiugu.kitty.databinding.ImageItemBinding
import com.ikiugu.kitty.models.CategoryResult

/**
 * Created by Alfred Ikiugu on 30/08/2021
 */

class ImagesAdapter(private val listener: OnItemClickListener) :
    ListAdapter<CategoryResult, ImagesAdapter.ImagesViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding =
            ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val currentImage = getItem(position)
        holder.bind(currentImage)
    }

    inner class ImagesViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                favoriteButton.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val category = getItem(position)
                        listener.onItemClick(category)
                    }
                }
            }
        }

        fun bind(image: CategoryResult) {
            binding.apply {
                if (image.url.isNotEmpty()) {
                    Glide.with(imageView)
                        .load(image.url)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .centerCrop()
                        .into(imageView)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(category: CategoryResult)
    }

    class DiffCallback : DiffUtil.ItemCallback<CategoryResult>() {
        override fun areItemsTheSame(oldItem: CategoryResult, newItem: CategoryResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryResult, newItem: CategoryResult): Boolean {
            return oldItem == newItem
        }
    }
}
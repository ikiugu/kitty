package com.ikiugu.kitty.ui.breeds.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ikiugu.kitty.databinding.BreedListingItemBinding
import com.ikiugu.kitty.models.CatBreed

/**
 * Created by Alfred Ikiugu on 30/08/2021
 */

class BreedListingAdapter(private val listener: OnItemClickListener) :
    ListAdapter<CatBreed, BreedListingAdapter.BreedListingViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedListingViewHolder {
        val binding =
            BreedListingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedListingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedListingViewHolder, position: Int) {
        val currentCategory = getItem(position)
        holder.bind(currentCategory)
    }

    inner class BreedListingViewHolder(private val binding: BreedListingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val category = getItem(position)
                        listener.onItemClick(category)
                    }
                }
            }
        }

        fun bind(category: CatBreed) {
            binding.apply {
                breedName.text = category.name
                breedDescription.text = category.description
                if (category.image != null) {
                    Glide.with(breedImage)
                        .load(category.image.url)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .centerCrop()
                        .into(breedImage)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CatBreed>() {
        override fun areItemsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(breed: CatBreed)
    }
}
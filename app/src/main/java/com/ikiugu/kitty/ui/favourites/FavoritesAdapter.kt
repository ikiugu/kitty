package com.ikiugu.kitty.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ikiugu.kitty.databinding.FavouritesItemBinding
import com.ikiugu.kitty.models.favorites.FavoriteItem

/**
 * Created by Alfred Ikiugu on 31/08/2021
 */

class FavoritesAdapter :
    ListAdapter<FavoriteItem, FavoritesAdapter.FavoritesViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding =
            FavouritesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val currentNote = getItem(position)
        holder.bind(currentNote)
    }

    inner class FavoritesViewHolder(private val binding: FavouritesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            /*binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val note = getItem(position)
                        listener.onItemClick(note)
                    }
                }

                noteCompleteButton.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val note = getItem(position)
                        listener.onButtonClicked(note)
                    }
                }
            }*/
        }

        fun bind(favorite: FavoriteItem) {
            binding.apply {
                if (favorite.image.url.isNotEmpty()) {
                    Glide.with(favoritesImageView)
                        .load(favorite.image.url)
                        .centerCrop()
                        .into(favoritesImageView)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(note: FavoriteItem)
        fun onButtonClicked(note: FavoriteItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<FavoriteItem>() {
        override fun areItemsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
            return oldItem == newItem
        }
    }
}
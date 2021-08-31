package com.ikiugu.kitty.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ikiugu.kitty.databinding.CategoriesItemBinding
import com.ikiugu.kitty.models.Category

/**
 * Created by Alfred Ikiugu on 30/08/2021
 */

class CategoriesAdapter :
    ListAdapter<Category, CategoriesAdapter.CategoriesViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            CategoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val currentCategory = getItem(position)
        holder.bind(currentCategory)
    }

    inner class CategoriesViewHolder(private val binding: CategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                categoryText.text = category.name
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}
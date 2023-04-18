package com.example.clothingsuggester

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingsuggester.databinding.ImageItemBinding
import com.example.clothingsuggester.model.Weather

class AdapterClothes (private val clickListener: (item: Int) -> Unit) :
    ListAdapter<Int, AdapterClothes.ClothesViewHolder>(CategoriesItemCallback()) {

    class ClothesViewHolder(private var binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: (item: Int) -> Unit, item: Int) {
            binding.apply {
                weatherStatusImage.setImageResource(item)
                root.setOnClickListener { clickListener(item) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(layoutInflater, parent, false)
        return ClothesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) =
        holder.bind(clickListener, getItem(position))

}


class CategoriesItemCallback :
    DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}


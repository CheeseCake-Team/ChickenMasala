package com.cheesecake.chickenmasala.ui.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheesecake.chickenmasala.databinding.ItemTranslatedIngredientBinding

class IngredientAdapter() :
    ListAdapter<String, IngredientAdapter.IngredientViewHolder>(IngredientItemCallback) {

    companion object IngredientItemCallback :
        DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }


    class IngredientViewHolder private constructor(private var binding: ItemTranslatedIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.apply {
                textIngredient.text = item
            }
        }

        companion object {
            fun from(parent: ViewGroup): IngredientViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTranslatedIngredientBinding.inflate(layoutInflater, parent, false)
                return IngredientViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder =
        IngredientViewHolder.from(parent)

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) =
        holder.bind(getItem(position))
}


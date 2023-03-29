package com.cheesecake.chickenmasala.ui.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheesecake.chickenmasala.databinding.ItemTranslatedIngredientBinding

class IngredientAdapter :
    ListAdapter<String, IngredientAdapter.IngredientViewHolder>(MealAdapterDiffUtil()) {

    class IngredientViewHolder(private val binding: ItemTranslatedIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.apply {
                textIngredientItem.text = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTranslatedIngredientBinding.inflate(layoutInflater, parent, false)
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) =
        holder.bind(getItem(position))

}



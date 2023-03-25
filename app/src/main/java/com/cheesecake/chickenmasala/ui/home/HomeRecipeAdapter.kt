package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.databinding.ItemRecipesBinding
import com.cheesecake.chickenmasala.model.Meal

class HomeRecipeAdapter(private val clickListener: HomeRecipeListener) :
    ListAdapter<Meal, HomeRecipeAdapter.RecipeViewHolder>(RecipeItemCallback) {

    companion object RecipeItemCallback :
        DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }


    class RecipeViewHolder(private var binding: ItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: HomeRecipeListener, item: Meal) {
            binding.apply {
                Glide.with(itemView.context).load(item.imageUrl).into(recipeImage)
                cuisine.text = item.cuisine
                time.text = item.TotalTimeInMinutes.toString()
                recipeName.text = item.translatedRecipeName
            }.root.setOnClickListener { listener.onClick(item) }
        }

        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecipesBinding.inflate(layoutInflater, parent, false)
                return RecipeViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        holder.bind(clickListener, getItem(position))
}

class HomeRecipeListener(val clickListener: (item: Meal) -> Unit) {
    fun onClick(item: Meal) = clickListener(item)
}

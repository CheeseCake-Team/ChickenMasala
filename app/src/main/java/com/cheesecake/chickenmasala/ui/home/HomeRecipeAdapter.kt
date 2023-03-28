package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.databinding.ItemRecipesBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.meals.MealItemCallback

class HomeRecipeAdapter(private val clickListener: HomeRecipeListener) :
    ListAdapter<Meal, HomeRecipeAdapter.RecipeViewHolder>(MealItemCallback()) {

    class RecipeViewHolder private constructor(private var binding: ItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: HomeRecipeListener, item: Meal) {
            val timeMinutes = "${item.TotalTimeInMinutes} m"
            binding.apply {
                Glide.with(itemView.context).load(item.imageUrl).into(recipeImage)
                cuisine.text = item.cuisine
                time.text = timeMinutes
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

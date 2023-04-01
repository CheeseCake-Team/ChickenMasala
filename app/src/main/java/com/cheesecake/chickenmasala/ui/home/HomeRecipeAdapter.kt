package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ItemRecipesBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.meals.MealItemCallback

class HomeRecipeAdapter(private val clickListener: (item: Meal) -> Unit)  :
    ListAdapter<Meal, HomeRecipeAdapter.RecipeViewHolder>(MealItemCallback()) {

    class RecipeViewHolder(private var binding: ItemRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: (item: Meal) -> Unit, item: Meal) {
            binding.apply {
                Glide.with(itemView.context).load(item.imageUrl).into(imageViewRecipeImage)
                textViewCuisine.text = item.cuisine
                textViewTime.text = textViewTime.context.getString(
                    R.string.meal_time,
                    item.TotalTimeInMinutes
                )
                textViewRecipeName.text = item.translatedRecipeName
                root.setOnClickListener { clickListener(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipesBinding.inflate(layoutInflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        holder.bind(clickListener, getItem(position))

}

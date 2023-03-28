package com.cheesecake.chickenmasala.ui.meals

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.databinding.ItemMealCardBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.search.MealsListener
import com.cheesecake.chickenmasala.ui.search.SearchAdapter


class MealsAdapter(
    private val mealListener: MealListener,
    private val context: Context
) :     ListAdapter<Meal, SearchAdapter.MealsViewHolder>(SearchAdapter) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            binding.textViewMealLocation.text = currentMeal.cuisine
            binding.textViewMealName.text = currentMeal.translatedRecipeName
            binding.textViewMealTime.text = currentMeal.TotalTimeInMinutes.toString()
            Glide.with(context).load(currentMeal.imageUrl).into(binding.imageMealOnMealCard)
            binding.root.setOnClickListener { mealListener.onClick(currentMeal) }
        }
    }

    class MealsViewHolder(private var binding: ItemMealCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: MealsListener, item: Meal) {
            binding.apply {
                textViewMealLocation.text = item.cuisine
                textViewMealName.text = item.translatedRecipeName
                textViewMealTime.text = item.TotalTimeInMinutes.toString()
                Glide.with(itemView.context).load(item.imageUrl).into(imageMealOnMealCard)
                binding.root.setOnClickListener { listener.onClick(item) }
            }
        }

        companion object {
            fun from(parent: ViewGroup): MealsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMealCardBinding.inflate(layoutInflater, parent, false)
                return MealsViewHolder(binding)
            }
        }
    }


    companion object MealsItemCallback :
        DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }

    class MealsCategoryViewHolder(private var binding: ItemMealCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: MealsListener, item: Meal) {
            binding.apply {
                textViewMealLocation.text = item.cuisine
                textViewMealName.text = item.translatedRecipeName
                textViewMealTime.text = item.TotalTimeInMinutes.toString()
                Glide.with(itemView.context).load(item.imageUrl).into(imageMealOnMealCard)
                binding.root.setOnClickListener { listener.onClick(item) }
            }
        }

        companion object {
            fun from(parent: ViewGroup): MealsCategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMealCardBinding.inflate(layoutInflater, parent, false)
                return MealsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MealsAdapter.MealsCategoryViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchAdapter.MealsViewHolder, position: Int) =
        holder.bind(clickListener, getItem(position))
}


class MealsListener(val clickListener: (item: Meal) -> Unit) {
    fun onClick(item: Meal) = clickListener(item)
}
}

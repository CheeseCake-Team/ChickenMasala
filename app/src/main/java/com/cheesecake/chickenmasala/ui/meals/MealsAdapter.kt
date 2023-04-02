package com.cheesecake.chickenmasala.ui.meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ItemMealCardBinding
import com.cheesecake.chickenmasala.model.Meal


class MealsAdapter(
    private val mealsListener: (item: Meal) -> Unit,
) : ListAdapter<Meal, MealsAdapter.MealsCategoryViewHolder>(MealItemCallback()) {

    override fun onBindViewHolder(holder: MealsCategoryViewHolder, position: Int) =
        holder.bind(mealsListener, getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMealCardBinding.inflate(layoutInflater, parent, false)
        return MealsCategoryViewHolder(binding)
    }

    class MealsCategoryViewHolder(private var binding: ItemMealCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: (item: Meal) -> Unit, item: Meal) {
            binding.apply {
                textViewMealLocation.text = item.cuisine
                textViewMealName.text = item.translatedRecipeName
                textViewMealTime.text =
                    itemView.context.getString(R.string.meal_time, item.TotalTimeInMinutes)
                Glide.with(itemView.context).load(item.imageUrl).into(imageMealOnMealCard)
                binding.root.setOnClickListener { listener(item) }
            }
        }
    }

}

class MealItemCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}

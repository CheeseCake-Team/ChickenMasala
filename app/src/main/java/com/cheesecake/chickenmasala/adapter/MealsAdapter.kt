package com.cheesecake.chickenmasala.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ItemMealCardBinding
import com.cheesecake.chickenmasala.model.Meal

class MealsAdapter(
    private val mealsList: List<Meal>,
    private val mealListener: MealListener,
    private val context: Context
) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_meal_card,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentMeal = mealsList[position]
        holder.apply {
            binding.tvLocationOfMeal.text = currentMeal.cuisine
            binding.tvNameOfMeal.text = currentMeal.translatedRecipeName
            binding.tvTimeToMakeMeal.text = currentMeal.TotalTimeInMinutes.toString()
            Glide.with(context).load(currentMeal.imageUrl).into(binding.imageCardOfMeal)
            binding.root.setOnClickListener{mealListener.onClick(currentMeal)}
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        var binding = ItemMealCardBinding.bind(itemView)
    }

    interface  MealListener{
        fun onClick(meal : Meal)
    }

}



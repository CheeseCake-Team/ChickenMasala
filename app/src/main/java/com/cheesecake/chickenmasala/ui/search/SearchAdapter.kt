package com.cheesecake.chickenmasala.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.databinding.ItemMealCardBinding
import com.cheesecake.chickenmasala.model.Meal

class SearchAdapter(private val clickListener: MealsListener) :
    ListAdapter<Meal, SearchAdapter.MealsViewHolder>(MealsItemCallback) {

    companion object MealsItemCallback :
        DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder =
        MealsViewHolder.from(parent)

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) =
        holder.bind(clickListener, getItem(position))
}


class MealsListener(val clickListener: (item: Meal) -> Unit) {
    fun onClick(item: Meal) = clickListener(item)
}
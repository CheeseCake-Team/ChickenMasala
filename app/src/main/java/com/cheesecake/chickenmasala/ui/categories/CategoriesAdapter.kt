package com.cheesecake.chickenmasala.ui.categories

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ItemCategoryBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.MealCourse


class CategoriesAdapter(private val clickListener: CategoriesListener) :
    ListAdapter<MealCourse, CategoriesAdapter.CategoriesViewHolder>(CategoriesItemCallback) {

    companion object CategoriesItemCallback :
        DiffUtil.ItemCallback<MealCourse>() {
        override fun areItemsTheSame(oldItem: MealCourse, newItem: MealCourse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MealCourse, newItem: MealCourse): Boolean {
            return oldItem == newItem
        }
    }


    class CategoriesViewHolder private constructor(private var binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: CategoriesListener, item: MealCourse) {
            binding.apply {
                textViewTextAddress.text = item.name
                cardImgHolder.setImageResource(item.imageResourceId)
            }.root.setOnClickListener { listener.onClick(item) }
        }

        companion object {
            fun from(parent: ViewGroup): CategoriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
                return CategoriesViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder =
        CategoriesViewHolder.from(parent)

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) =
        holder.bind(clickListener, getItem(position))
}


class CategoriesListener(val clickListener: (item: MealCourse) -> Unit) {
    fun onClick(item: MealCourse) = clickListener(item)
}
package com.cheesecake.chickenmasala.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheesecake.chickenmasala.databinding.ItemCategoryBinding
import com.cheesecake.chickenmasala.model.MealCourse

class CategoriesAdapter(private val clickListener: (item: MealCourse) -> Unit) :
    ListAdapter<MealCourse, CategoriesAdapter.CategoriesViewHolder>(CategoriesItemCallback()) {

    class CategoriesViewHolder(private var binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: (item: MealCourse) -> Unit, item: MealCourse) {
            binding.apply {
                textViewTextAddress.text = item.courseName
                cardImgHolder.setImageResource(item.imageResourceId)
                root.setOnClickListener { clickListener(item) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) =
        holder.bind(clickListener, getItem(position))

}


class CategoriesItemCallback :
    DiffUtil.ItemCallback<MealCourse>() {
    override fun areItemsTheSame(oldItem: MealCourse, newItem: MealCourse): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: MealCourse, newItem: MealCourse): Boolean {
        return oldItem == newItem
    }
}




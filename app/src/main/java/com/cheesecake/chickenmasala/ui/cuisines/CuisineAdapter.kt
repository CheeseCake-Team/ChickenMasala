package com.cheesecake.chickenmasala.ui.cuisines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ItemCategoryBinding
import com.cheesecake.chickenmasala.ui.meal.StringDiffUtil

class CuisineAdapter(private val clickListener: (item: String) -> Unit) :
    ListAdapter<String, CuisineAdapter.CuisineViewHolder>(StringDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return CuisineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CuisineViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class CuisineViewHolder(private var binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: (item: String) -> Unit, item: String) {
            binding.apply {
                textViewTextAddress.text = item
                cardImgHolder.setImageResource(getRandomImageResource())
                root.setOnClickListener { clickListener(item) }
            }
        }

        private fun getRandomImageResource(): Int {
            val images = arrayOf(
                R.drawable.soup_category,
                R.drawable.spicy_category,
                R.drawable.food_image_six,
                R.drawable.vegetables_category,
                R.drawable.masala_category,
                R.drawable.breakfast_category,
                R.drawable.cake_category,
            )
            return images.random()
        }
    }
}






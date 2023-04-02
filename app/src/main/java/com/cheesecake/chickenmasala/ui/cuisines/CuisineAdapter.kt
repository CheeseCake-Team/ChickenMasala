package com.cheesecake.chickenmasala.ui.cuisines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.databinding.ItemCategoryBinding
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
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
                Glide.with(itemView.context).load(getRandomImageResource(item)).into(cardImgHolder)

                root.setOnClickListener { clickListener(item) }
            }
        }

        private fun getRandomImageResource(string: String) =
            RecipesInteractor().getRandomCuisineImage(string)

    }
}






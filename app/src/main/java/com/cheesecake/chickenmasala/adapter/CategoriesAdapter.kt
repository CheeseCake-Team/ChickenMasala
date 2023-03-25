package com.cheesecake.chickenmasala.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ItemCategoryBinding
import com.cheesecake.chickenmasala.model.Meal

class CategoriesAdapter(
    private val categoriesList: List<Meal>,
    private val categoriesListener: CategoriesListener,
    private val context: Context
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentCategory = categoriesList[position]
        holder.apply {
            binding.textCardAddress.text = currentCategory.cuisine
            Glide.with(context).load(currentCategory.imageUrl)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        // Set the loaded image as the background of the CardView
                        binding.cardImg.background = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
            binding.root.setOnClickListener { categoriesListener.onClick(currentCategory) }
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        var binding = ItemCategoryBinding.bind(itemView)
    }

    interface CategoriesListener {
        fun onClick(Category: Meal)
    }

}
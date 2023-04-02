package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cheesecake.chickenmasala.databinding.ImageSliderBinding
import com.cheesecake.chickenmasala.databinding.ItemRecommendationBinding
import com.cheesecake.chickenmasala.model.Advice
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.Recommendation

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_RECOMMENDATION = 1

class HomeAdapter(
    private val clickListener: (item: Meal) -> Unit,
    private val myDataList: List<*>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> AdviceViewHolder(
                ImageSliderBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            ITEM_VIEW_TYPE_RECOMMENDATION -> RecommendationViewHolder(
                ItemRecommendationBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            else -> throw java.lang.ClassCastException("Unknown view type: $viewType")
        }
    }

    override fun getItemCount() = myDataList.size

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_VIEW_TYPE_HEADER
            else -> ITEM_VIEW_TYPE_RECOMMENDATION
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AdviceViewHolder -> {
                val advices = myDataList[0] as List<Advice>
                holder.bind(advices)
            }
            is RecommendationViewHolder -> {
                val recommendations = myDataList[position] as Recommendation
                holder.bind(clickListener, recommendations)
            }
        }
    }

    private class RecommendationViewHolder(private var binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: (item: Meal) -> Unit, item: Recommendation) {
            val homeRecipeAdapter = HomeRecipeAdapter(clickListener)
            homeRecipeAdapter.submitList(item.listOfRecipes)
            binding.apply {
                textRecipes.text = root.context.getString(item.title)
                recyclerViewRecipes.adapter = homeRecipeAdapter
            }
        }
    }

    private class AdviceViewHolder(private var binding: ImageSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: List<Advice>) {
            binding.apply {
                val advicesAdapter = AdviceImageSliderAdapter()
                advicesAdapter.submitList(items)
                binding.adviceImageSlider.adapter = advicesAdapter
            }
        }
    }
}
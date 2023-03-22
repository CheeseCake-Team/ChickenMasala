package com.cheesecake.chickenmasala.ui

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.FragmentMealBinding
import com.cheesecake.chickenmasala.databinding.ItemRecipeMealBinding
import com.cheesecake.chickenmasala.model.Meal

class MealFragment(private val meal: Meal) : BaseFragment<FragmentMealBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealBinding =
        FragmentMealBinding::inflate

    override fun onStart() {
        super.onStart()
        fillLinearLayoutWithCard()
    }

    private fun fillLinearLayoutWithCard() {
        for (recipe in 0..20) {
            val itemCuisineBinding = ItemRecipeMealBinding.inflate(layoutInflater)
            binding.itemRecipeHolder.addView(itemCuisineBinding.root)
        }
    }
}
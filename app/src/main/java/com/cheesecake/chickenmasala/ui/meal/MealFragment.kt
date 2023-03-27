package com.cheesecake.chickenmasala.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.databinding.FragmentMealBinding
import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.categories.CategoriesFragment

class MealFragment() : BaseFragment<FragmentMealBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealBinding =
        FragmentMealBinding::inflate

    private val ingredientAdapter = IngredientAdapter()
    private val instructionAdapter = InstructionAdapter()
    private lateinit var meal: Meal


    override fun onStart() {
        super.onStart()
        meal = arguments?.getParcelable(Constants.Keys.category)!!
        ingredientAdapter.submitList(meal.translatedIngredients)
        instructionAdapter.submitList(meal.translatedInstructions)

        binding.apply {
            recyclerviewMeal.adapter = ingredientAdapter
            Glide.with(this@MealFragment).load(meal.imageUrl).into(imageMealHolder)
            textMealTime.text = meal.TotalTimeInMinutes.toString()
            textMealCount.text = meal.ingredientCount.toString()
            textMealName.text = meal.translatedRecipeName
        }

        addCallBacks()
    }

    private fun addCallBacks() {
        binding.buttonIngredient.setOnClickListener {
            binding.recyclerviewMeal.adapter = ingredientAdapter
        }

        binding.buttonProcedure.setOnClickListener {
            binding.recyclerviewMeal.adapter = instructionAdapter
        }
    }

    companion object {
        fun createFragment(meal: Meal) = MealFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Keys.category, meal)
            }
        }
    }
}
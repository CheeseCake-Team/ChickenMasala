package com.cheesecake.chickenmasala.ui.meal

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentMealBinding
import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment

class MealFragment() : BaseFragment<FragmentMealBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealBinding =
        FragmentMealBinding::inflate

    private val ingredientAdapter = IngredientAdapter()
    private val instructionAdapter = InstructionsAdapter()
    private lateinit var meal: Meal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        meal = arguments?.getParcelable(Constants.Keys.category)!!
        initViews()
        setUpAdapters()
        addCallBacks()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpAdapters() {
        ingredientAdapter.submitList(meal.translatedIngredients)
        instructionAdapter.submitList(meal.translatedInstructions)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding.apply {
            recyclerviewMeal.adapter = ingredientAdapter
            textMealTime.text = textMealTime.context.getString(meal.TotalTimeInMinutes,R.string.text_meal_m)
            textMealCount.text = textMealCount.context.getString(meal.ingredientCount,R.string.text_meal_ingredients)
            textMealName.text = meal.translatedRecipeName
            Glide.with(requireContext()).load(meal.imageUrl)
                .error(R.drawable.ic_baseline_error_outline_24).into(imageMeal)

            buttonIngredient.performClick()
        }
    }

    private fun addCallBacks() {
        binding.apply {
            buttonIngredient.setOnClickListener {
                recyclerviewMeal.adapter = ingredientAdapter
            }
            buttonInstructions.setOnClickListener {
                recyclerviewMeal.adapter = instructionAdapter
            }

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
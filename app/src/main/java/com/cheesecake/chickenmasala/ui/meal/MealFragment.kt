package com.cheesecake.chickenmasala.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentMealBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment

private const val ARGUMENT_MEAL = "meal"

class MealFragment : BaseFragment<FragmentMealBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealBinding =
        FragmentMealBinding::inflate

    private val ingredientAdapter = IngredientAdapter()
    private val instructionAdapter = InstructionsAdapter()
    private lateinit var meal: Meal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        meal = arguments?.getParcelable(ARGUMENT_MEAL)!!
        initViews()
        setUpAdapters()
        addCallBacks()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {

        binding.apply {
            recyclerviewMeal.adapter = ingredientAdapter
            textMealTime.text =
                requireContext().getString(R.string.meal_time, meal.TotalTimeInMinutes)
            textMealName.text = meal.translatedRecipeName
            Glide.with(requireContext()).load(meal.imageUrl)
                .error(R.drawable.ic_baseline_error_outline_24).into(imageMeal)

            buttonIngredient.performClick()
        }
    }

    private fun setUpAdapters() {
        ingredientAdapter.submitList(meal.translatedIngredients)
        instructionAdapter.submitList(meal.translatedInstructions)
    }


    private fun addCallBacks() {
        binding.apply {
            buttonIngredient.setOnClickListener {
                recyclerviewMeal.adapter = ingredientAdapter
                textMealCount.text =
                    requireContext().getString(R.string.meal_ingredient_count, meal.ingredientCount)
            }
            buttonInstructions.setOnClickListener {
                recyclerviewMeal.adapter = instructionAdapter
                textMealCount.text = requireContext().getString(R.string.meal_steps_count, meal.translatedInstructions.size)
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(meal: Meal) = MealFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_MEAL, meal)
            }
        }
    }
}
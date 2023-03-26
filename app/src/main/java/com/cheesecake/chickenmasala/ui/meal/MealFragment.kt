package com.cheesecake.chickenmasala.ui.meal

import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.databinding.FragmentMealBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment

class MealFragment(private val meal: Meal) : BaseFragment<FragmentMealBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealBinding =
        FragmentMealBinding::inflate

    private val ingredientAdapter = IngredientAdapter()
    private val instructionAdapter = InstructionAdapter()

    override fun onStart() {
        super.onStart()

        ingredientAdapter.submitList(meal.translatedIngredients)
        instructionAdapter.submitList(meal.translatedInstructions)

        binding.apply {
            recyclerViewIngredientProcedure.adapter = ingredientAdapter
            Glide.with(this@MealFragment).load(meal.imageUrl).into(mealImage)
            mealTime.text = meal.TotalTimeInMinutes.toString()
            mealCount.text = meal.ingredientCount.toString()
            mealName.text = meal.translatedRecipeName
        }

        addCallBacks()
    }

    private fun addCallBacks() {
        binding.ingredientButton.setOnClickListener {
            binding.recyclerViewIngredientProcedure.adapter = ingredientAdapter
        }

        binding.ProcedureButton.setOnClickListener {
            binding.recyclerViewIngredientProcedure.adapter = instructionAdapter
        }
    }
}
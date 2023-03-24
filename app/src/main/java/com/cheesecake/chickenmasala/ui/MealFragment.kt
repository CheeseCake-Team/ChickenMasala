package com.cheesecake.chickenmasala.ui

import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.databinding.FragmentMealBinding
import com.cheesecake.chickenmasala.databinding.ItemTranslatedIngredientBinding
import com.cheesecake.chickenmasala.databinding.ItemTranslatedInstructionBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.databinding.ItemRecipeMealBinding

class MealFragment(private val meal: Meal) : BaseFragment<FragmentMealBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealBinding =
        FragmentMealBinding::inflate

    override fun onStart() {
        super.onStart()
        fillMealData()
        addCallBack()
    }

    private fun addCallBack() {
        binding.ingredientButton.setOnClickListener {
            binding.itemRecipeHolder.removeAllViews()
            fillTranslatedIngredients(meal.translatedIngredients)
        }

        binding.ProcedureButton.setOnClickListener {
            binding.itemRecipeHolder.removeAllViews()
            fillTranslatedInstructions(meal.translatedInstructions)
        }
    }

    private fun fillMealData() {
        Glide.with(this).load(meal.imageUrl).into(binding.mealImage)
        binding.mealName.text = meal.translatedRecipeName
        binding.mealCount.text = meal.ingredientCount.toString()
        binding.mealTime.text = meal.TotalTimeInMinutes.toString()
        fillTranslatedIngredients(meal.translatedIngredients)
    }

    private fun fillTranslatedIngredients(translatedIngredients: List<String>) {
        translatedIngredients.forEach {
            val itemRecipeMealBinding = ItemTranslatedIngredientBinding.inflate(layoutInflater)
            itemRecipeMealBinding.textMeal.text = it
            binding.itemRecipeHolder.addView(itemRecipeMealBinding.root)
        }
    }

    private fun fillTranslatedInstructions(translatedInstructions: List<String>) {
        translatedInstructions.first().drop(1)
        translatedInstructions.last().dropLast(1)
        for (i in translatedInstructions.indices) {
            val itemRecipeMealBinding = ItemTranslatedInstructionBinding.inflate(layoutInflater)
            val instructionNumber = "Step ${i + 1}"
            itemRecipeMealBinding.instructionNumber.text = instructionNumber
            itemRecipeMealBinding.instruction.text = translatedInstructions[i]
            binding.itemRecipeHolder.addView(itemRecipeMealBinding.root)
        }
        fillLinearLayoutWithCard()
    }

    private fun fillLinearLayoutWithCard() {
        for (recipe in 0..20) {
            val itemCuisineBinding = ItemRecipeMealBinding.inflate(layoutInflater)
            binding.itemRecipeHolder.addView(itemCuisineBinding.root)
        }
    }
}
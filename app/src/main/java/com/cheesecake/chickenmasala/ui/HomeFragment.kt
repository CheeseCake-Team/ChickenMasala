package com.cheesecake.chickenmasala.ui

import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.databinding.ItemRecipesBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.Recipes

class HomeFragment(private val recipes: Recipes) : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onStart() {
        super.onStart()
        fillFastRecipes(20)
        fillRecipesOfToday(20)
    }

    private fun fillFastRecipes(limit: Int) {
        val fastRecipes = recipes.getFastMeals(limit)
        fastRecipes.forEach {
            loadRecipeItems(it, binding.linearCardFast)
        }
    }


    private fun fillRecipesOfToday(limit: Int) {
        val recipesOfToday = recipes.getRandomMeals(limit)
        recipesOfToday.forEach {
            loadRecipeItems(it, binding.recipesOfToday)
        }
    }

    private fun loadRecipeItems(meal: Meal, linearCard: LinearLayout) {
        val itemRecipesBinding = ItemRecipesBinding.inflate(layoutInflater)

        Glide.with(this).load(meal.imageUrl).into(itemRecipesBinding.recipeImage)

        itemRecipesBinding.cuisine.text = meal.cuisine
        itemRecipesBinding.time.text = meal.TotalTimeInMinutes.toString()
        itemRecipesBinding.recipeName.text = meal.translatedRecipeName

        itemRecipesBinding.root.setOnClickListener {
            loadMealFragment(meal)
        }

        linearCard.addView(itemRecipesBinding.root)
    }

    private fun loadMealFragment(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, MealFragment(meal))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.databinding.ItemRecipesBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment

class HomeFragment(private val recipes: RecipesManager) : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onStart() {
        super.onStart()
        fillFastRecipes()
        fillRecipesOfToday()
    }

    private fun fillFastRecipes() {
        val fastRecipes = recipes.getFastMeals()
        fastRecipes.forEach {
            loadRecipeItems(it, binding.linearCardFast)
        }
    }


    private fun fillRecipesOfToday() {
        val recipesOfToday = recipes.getRandomMeals()
        recipesOfToday.forEach {
            loadRecipeItems(it, binding.recipesOfToday)
        }
    }

    private fun loadRecipeItems(meal: Meal, linearCard: LinearLayout) {
        val itemRecipesBinding = ItemRecipesBinding.inflate(layoutInflater)

        Glide.with(this).load(meal.imageUrl).into(itemRecipesBinding.imageSmallCardRecipe)

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
        transaction.replace(R.id.fragment_container, MealFragment.createFragment(meal))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
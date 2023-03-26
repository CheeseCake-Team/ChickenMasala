package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onStart() {
        super.onStart()
        val fastRecipeAdapter =
            HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) }).apply {
                submitList(RecipesManager.getFastMeals())
            }
        val recipesOfTodayAdapter =
            HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) }).apply {
                submitList(RecipesManager.getRandomMeals())
            }
        val lowIngredientsFoodAdapter =
            HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) }).apply {
                submitList(RecipesManager.getLowIngredientsMeals())
            }
        binding.apply {
            recyclerViewFastRecipes.adapter = fastRecipeAdapter
            recyclerViewRecipesOfToday.adapter = recipesOfTodayAdapter
            recyclerViewLowIngredientsFood.adapter = lowIngredientsFoodAdapter
        }
    }


    private fun loadMealFragment(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, MealFragment(meal))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
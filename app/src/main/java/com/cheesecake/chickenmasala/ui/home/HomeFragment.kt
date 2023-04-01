package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onStart() {
        super.onStart()
        setupViews()
    }

    private fun setupViews() {
        val adviceAdapter = AdviceImageSliderAdapter().apply {
            submitList(AdviceFactory(requireContext()).prepareFoodAdviceList)
        }

        val fastRecipeAdapter = HomeRecipeAdapter {
                loadFragment(MealFragment.newInstance(it))
            }.apply {
                submitList(RecipesManager.getFastMeals())
            }

        val recipesOfTodayAdapter = HomeRecipeAdapter {
                loadFragment(MealFragment.newInstance(it))
            }.apply {
                submitList(RecipesManager.getRandomMeals())
            }

        val lowIngredientsFoodAdapter = HomeRecipeAdapter {
                loadFragment(MealFragment.newInstance(it))
            }.apply {
                submitList(RecipesManager.getLessIngredientMeals())
            }

        binding.apply {
            recyclerViewFastRecipes.adapter = fastRecipeAdapter
            recyclerViewRecipesOfToday.adapter = recipesOfTodayAdapter
            recyclerViewLowIngredientsFood.adapter = lowIngredientsFoodAdapter
            adviceImageSlider.adapter = adviceAdapter
        }
    }


}
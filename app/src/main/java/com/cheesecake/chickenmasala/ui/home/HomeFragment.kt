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
        val fastRecipeAdapter = HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) })
        val recipesOfTodayAdapter = HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) })
        binding.recyclerViewFastRecipes.adapter = fastRecipeAdapter
        binding.recyclerViewRecipesOfToday.adapter = recipesOfTodayAdapter

        fastRecipeAdapter.submitList(recipes.getFastMeals())
        recipesOfTodayAdapter.submitList(recipes.getRandomMeals())
    }

    private fun loadMealFragment(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, MealFragment(meal))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
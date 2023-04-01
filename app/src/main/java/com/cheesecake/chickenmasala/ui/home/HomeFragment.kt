package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
import com.cheesecake.chickenmasala.model.Meal
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
        val recipesInteractor = RecipesInteractor()
        val fastRecipeAdapter =
            HomeRecipeAdapter(::loadMealFragment).apply {
                submitList(recipesInteractor.getFastMeals())
            }
        val recipesOfTodayAdapter =
            HomeRecipeAdapter(::loadMealFragment).apply {
                submitList(recipesInteractor.getRandomMeals())
            }
        val lowIngredientsFoodAdapter =
            HomeRecipeAdapter(::loadMealFragment).apply {
                submitList(recipesInteractor.getLessIngredientMeals())
            }
        binding.apply {
            recyclerViewFastRecipes.adapter = fastRecipeAdapter
            recyclerViewRecipesOfToday.adapter = recipesOfTodayAdapter
            recyclerViewLowIngredientsFood.adapter = lowIngredientsFoodAdapter
            adviceImageSlider.adapter = adviceAdapter
        }
    }

    private fun loadMealFragment(meal: Meal) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealFragment.newInstance(meal))
            addToBackStack(null)
            commit()
        }
    }


}
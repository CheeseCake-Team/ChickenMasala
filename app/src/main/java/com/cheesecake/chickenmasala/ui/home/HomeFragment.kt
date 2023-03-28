package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.model.Advice
import com.cheesecake.chickenmasala.model.Meal
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
            submitList(prepareFoodAdviceList())
        }

        val fastRecipeAdapter =
            HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) }).apply {
                submitList(RecipesManager.getFastMeals())
            }
        val recipesOfTodayAdapter =
            HomeRecipeAdapter(HomeRecipeListener { loadMealFragment(it) }).apply {
                submitList(RecipesManager.getRandomMeals())
            }
        val lowIngredientsFoodAdapter =
            HomeRecipeAdapter(HomeRecipeListener(::loadMealFragment)).apply {
                submitList(RecipesManager.getLessIngredientMeals())
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

    private fun prepareFoodAdviceList() = mutableListOf(
        Advice(
            requireContext().getString(R.string.eat_fruits_veggies_title),
            requireContext().getString(R.string.eat_fruits_veggies_body),
            R.drawable.fruits_image
        ),

        Advice(
            requireContext().getString(R.string.limit_processed_foods_title),
            requireContext().getString(R.string.limit_processed_foods_body),
            R.drawable.food_image_six
        ),

        Advice(
            requireContext().getString(R.string.choose_lean_protein_title),
            requireContext().getString(R.string.choose_lean_protein_body),
            R.drawable.food_image_thirteen
        ),

        Advice(
            requireContext().getString(R.string.reduce_added_sugar_title),
            requireContext().getString(R.string.reduce_added_sugar_body), R.drawable.suger_image
        ),

        Advice(
            requireContext().getString(R.string.stay_hydrated_title),
            requireContext().getString(R.string.stay_hydrated_body), R.drawable.water_image
        ),

        Advice(
            requireContext().getString(R.string.choose_healthy_fats_title),
            requireContext().getString(R.string.choose_healthy_fats_body),
            R.drawable.food_image_ten
        ),

        Advice(
            requireContext().getString(R.string.eat_breakfast_title),
            requireContext().getString(R.string.eat_breakfast_body), R.drawable.food_image_nine
        )
    )
}
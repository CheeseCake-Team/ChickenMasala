package com.cheesecake.chickenmasala.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.MealCourse
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment

class MealsFragment :
    BaseFragment<FragmentMealsBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate
    private lateinit var mealCourse: MealCourse

    private lateinit var mealsAdapter: MealsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealCourse = arguments?.getParcelable(Constants.Keys.category)!!
        val meals =
            RecipesManager.indianFoodSearch.searchAndFilter(course = mealCourse)
                .getSearchedMeals()
        installViews(meals)
    }

    private fun installViews(
        meals: List<Meal>,
    ) {
        mealsAdapter = MealsAdapter(MealsListener {
            loadMealFragment(it)
        }).apply {
            submitList(meals)
        }
        binding.recyclerMeals.adapter = mealsAdapter
    }

    private fun loadMealFragment(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.fragment_container, MealFragment.newInstance(meal)).commit()
    }

    companion object {
        fun createFragment(mealCourse: MealCourse) = MealsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Keys.category, mealCourse)
            }
        }
    }

}

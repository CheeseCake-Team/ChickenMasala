package com.cheesecake.chickenmasala.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.MealCourse
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment

private const val ARG_MEAL_COURSE = "meal_course"

class MealsFragment :
    BaseFragment<FragmentMealsBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate

    private lateinit var mealCourse: MealCourse

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealCourse = arguments?.getParcelable(ARG_MEAL_COURSE)!!
        setupViews()
    }

    private fun setupViews() {
        val meals =
            RecipesManager.indianFoodSearch.searchAndFilter(course = mealCourse).getSearchedMeals()

        val mealsAdapter = MealsAdapter { loadMealFragment(it) }
        mealsAdapter.submitList(meals)
        binding.recyclerMeals.adapter = mealsAdapter
    }

    private fun loadMealFragment(meal: Meal) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealFragment.newInstance(meal))
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(mealCourse: MealCourse) = MealsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_MEAL_COURSE, mealCourse)
            }
        }
    }

}

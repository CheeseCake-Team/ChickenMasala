package com.cheesecake.chickenmasala.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.datasource.Constants
import com.cheesecake.chickenmasala.model.MealCourse
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment



class MealsFragment :
    BaseFragment<FragmentMealsBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate

    private lateinit var mealCourse: MealCourse

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealCourse = arguments?.getParcelable(Constants.Keys.ARGUMENT)!!
        setupViews()
    }

    private fun setupViews() {
        val meals =
            RecipesManager.indianFoodSearch.searchAndFilter(course = mealCourse).getSearchedMeals()

        val mealsAdapter = MealsAdapter { loadFragment(MealFragment.newInstance(it)) }
        mealsAdapter.submitList(meals)
        binding.recyclerMeals.adapter = mealsAdapter
    }


    companion object {
        @JvmStatic
        fun newInstance(mealCourse: MealCourse) = MealsFragment().apply {
            createArgument(mealCourse,this)
            }
        }
    }



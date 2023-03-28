package com.cheesecake.chickenmasala.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.model.MealCourse
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.search.SearchFragment

private const val ARG_FOOD_COURSE = "meal_course"

class MealsFragment() : BaseFragment<FragmentMealsBinding>() {


    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate


    private lateinit var mealsAdapter: MealsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        installViews()
    }


    private fun installViews() {
        //mealsAdapter = MealsAdapter()
        binding.recyclerMeals.adapter = mealsAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(mealCource: MealCourse) = SearchFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_FOOD_COURSE, mealCource)
            }
        }
    }

}




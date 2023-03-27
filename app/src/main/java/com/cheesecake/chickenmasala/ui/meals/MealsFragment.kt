package com.cheesecake.chickenmasala.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.model.*
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment
class MealsFragment :
    BaseFragment<FragmentMealsBinding>(), MealsAdapter.MealListener {

    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate
    private lateinit var mealCourse: MealCourse

    private lateinit var mealsAdapter: MealsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealCourse = arguments?.getParcelable(Constants.MEALS)!!
        val meals =
            RecipesManager.indianFoodSearch.filterMealsByCourse(mealCourse)
                .getSearchedMeals()
        installViews(meals, this, requireContext())
    }


    private fun installViews(
        meal: List<Meal>,
        mealListener: MealsAdapter.MealListener,
        context: Context
    ) {
        mealsAdapter = MealsAdapter(meal, mealListener, context)
        binding.recyclerMeals.adapter = mealsAdapter
    }


    override fun onClick(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.add(R.id.fragment_container, MealFragment.createFragment(meal)).commit()
    }
    companion object{
        fun createFragment(mealCourse: MealCourse)=MealsFragment().apply {
            arguments=Bundle().apply {
                putParcelable(Constants.MEALS,mealCourse)
            }
        }
    }

    }




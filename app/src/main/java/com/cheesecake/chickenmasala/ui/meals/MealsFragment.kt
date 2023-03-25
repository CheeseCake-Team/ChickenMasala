package com.cheesecake.chickenmasala.ui.meals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.adapter.MealsAdapter
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment
class MealsFragment(private val meals: List<Meal>) :
    BaseFragment<FragmentMealsBinding>(), MealsAdapter.MealListener {


    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate


    private lateinit var mealsAdapter: MealsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        transaction.add(R.id.fragment_container, MealFragment(meal)).commit()
    }

    }




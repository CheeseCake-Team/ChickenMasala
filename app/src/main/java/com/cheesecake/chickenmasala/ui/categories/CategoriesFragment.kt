package com.cheesecake.chickenmasala.ui.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentCategoriesBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment
import com.cheesecake.chickenmasala.ui.meals.MealsFragment

class CategoriesFragment(private val category: List<Meal>) :
    BaseFragment<FragmentCategoriesBinding>(), CategoriesAdapter.CategoriesListener {
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding =
        FragmentCategoriesBinding::inflate

    private lateinit var categoryAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        installViews(category, this, requireContext())
    }

    private fun installViews(
        categories: List<Meal>,
        categoryListener: CategoriesAdapter.CategoriesListener,
        context: Context
    ) {
        categoryAdapter = CategoriesAdapter(categories, categoryListener, context)
        binding.recyclerCategoriesHolder.adapter = categoryAdapter
    }


    fun loadMealsFragment(category: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.fragment_container,
            //MealsFragment(RecipesManager.indianFoodSearch.filterMealsByCourse(category))).commit()
    }


}
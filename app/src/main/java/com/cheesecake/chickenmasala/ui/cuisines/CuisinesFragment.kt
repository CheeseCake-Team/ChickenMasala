package com.cheesecake.chickenmasala.ui.cuisines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentCategoriesBinding
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
import com.cheesecake.chickenmasala.model.MealCourse
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meals.MealsFragment

class CuisinesFragment :
    BaseFragment<FragmentCategoriesBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding =
        FragmentCategoriesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val recipesInteractor = RecipesInteractor()
        val cuisines = recipesInteractor.cuisines
        val cuisineAdapter = CuisineAdapter { loadMealFragment(it) }
        cuisineAdapter.submitList(cuisines)
        binding.recyclerCategoriesHolder.adapter = cuisineAdapter
    }

    private fun loadMealFragment(string: String) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealsFragment.newInstance(string,2))
            addToBackStack(null)
            commit()
        }
    }

}
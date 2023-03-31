package com.cheesecake.chickenmasala.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.databinding.FragmentCategoriesBinding
import com.cheesecake.chickenmasala.model.MealCourse
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meals.MealsFragment

class CategoriesFragment :
    BaseFragment<FragmentCategoriesBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding =
        FragmentCategoriesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val categories = MealCourse.values().toList()
        val categoryAdapter =
            CategoriesAdapter { loadFragment(MealsFragment.newInstance(it)) }.apply {
                submitList(categories)
            }
        binding.recyclerCategoriesHolder.adapter = categoryAdapter
    }


}
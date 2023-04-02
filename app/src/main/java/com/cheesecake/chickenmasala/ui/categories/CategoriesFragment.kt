package com.cheesecake.chickenmasala.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentCategoriesBinding
import com.cheesecake.chickenmasala.model.MealCourse
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meals.MealsFragment

class CategoriesFragment :
    BaseFragment<FragmentCategoriesBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding =
        FragmentCategoriesBinding::inflate

    override fun hasBackButtonOrNot() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val categories = MealCourse.values().toList()
        val categoryAdapter = CategoriesAdapter { loadMealFragment(it) }
        categoryAdapter.submitList(categories)
        binding.recyclerCategoriesHolder.adapter = categoryAdapter
    }

    private fun loadMealFragment(sting: String) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealsFragment.newInstance(sting,1))
            addToBackStack(null)
            commit()
        }
    }

}
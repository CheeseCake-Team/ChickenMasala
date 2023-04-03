package com.cheesecake.chickenmasala.ui.cuisines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentCategoriesBinding
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Constants.Keys.CUISINES_TYPE
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meals.MealsFragment

class CuisinesFragment :
    BaseFragment<FragmentCategoriesBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding =
        FragmentCategoriesBinding::inflate

    override fun hasBackButtonOrNot() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        this.returnStatue(binding.recyclerCategoriesHolder, Constants.Keys.CUISINES)

    }

    override fun setActionBarTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.cuisine)

    }

    private fun setupViews() {
        val recipesInteractor = RecipesInteractor()
        val cuisines = recipesInteractor.cuisines
        val cuisineAdapter = CuisineAdapter { loadMealFragment(it) }
        cuisineAdapter.submitList(cuisines)
        binding.recyclerCategoriesHolder.adapter = cuisineAdapter
    }

    private fun loadMealFragment(content: String) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealsFragment.newInstance(content, CUISINES_TYPE))
            addToBackStack(null)
            commit()
        }
    }

    override fun onPause() {
        super.onPause()
        this.saveStatue(binding.recyclerCategoriesHolder, Constants.Keys.CUISINES)
    }

}
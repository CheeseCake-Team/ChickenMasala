package com.cheesecake.chickenmasala.ui.home

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentHomeBinding
import com.cheesecake.chickenmasala.interactor.AdviceInteractor
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun hasBackButtonOrNot() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun setActionBarTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.home)

    }

    override fun onStart() {
        super.onStart()
        setupViews()
    }

    private fun setupViews() {
        val recipesInteractor = RecipesInteractor()
        val homeList = listOf(
            AdviceInteractor().prepareFoodAdviceList,
            recipesInteractor.getRandomMealsRecommendation(),
            recipesInteractor.getFastestMealsRecommendation(),
            recipesInteractor.getLessIngredientRecommendation()
        )
        val homeAdapter = HomeAdapter(::loadMealFragment, homeList)
        binding.apply {
            recyclerViewHome.adapter = homeAdapter
        }
    }

    private fun loadMealFragment(meal: Meal) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealFragment.newInstance(meal))
            addToBackStack(null)
            commit()
        }
    }

}
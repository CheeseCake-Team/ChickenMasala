package com.cheesecake.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentCuisineBinding
import com.cheesecake.chickenmasala.databinding.ItemCuisineBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.Recipes


class CuisineFragment(private val recipes: Recipes) : BaseFragment<FragmentCuisineBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCuisineBinding =
        FragmentCuisineBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillCuisines()
    }

    private fun fillCuisines(){
        recipes.cuisines.forEach {cuisine ->
            val itemCuisineBinding = ItemCuisineBinding.inflate(layoutInflater)
             itemCuisineBinding.cuisineCountry.text = cuisine

            itemCuisineBinding.root.setOnClickListener { loadMealsFragment(recipes.getCuisineRecipe(cuisine)) }

            binding.cuisineContainer.addView(itemCuisineBinding.root)
        }
    }
    private fun loadMealsFragment(meals: List<Meal>) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, MealsFragment(meals))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
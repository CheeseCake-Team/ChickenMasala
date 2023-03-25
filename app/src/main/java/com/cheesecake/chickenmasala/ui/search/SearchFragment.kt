package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.databinding.ChipsInjectBinding
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.google.android.material.chip.Chip

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate


    private var recipesManager: RecipesManager? = null
    private lateinit var searchBarInputs: MutableList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipesManager = arguments?.getParcelable(Constants.MAIN_ACTIVITY_RECIPES)
    }


    override fun onStart() {
        super.onStart()
        val x = collectAllIngredients()
        x.forEach {

        }
    }


    private fun collectAllIngredients(): MutableList<String> {
        searchBarInputs = mutableListOf<String>()
        binding.searchAutoCompleteTextView.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val x = binding.searchAutoCompleteTextView.text.toString() + "\n"
                searchBarInputs.add(x)
                val chip = ChipsInjectBinding.inflate(layoutInflater)
                chip.customChip.text = x
                binding.result.addView(chip.root)
                binding.searchAutoCompleteTextView.setText("")

                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        return searchBarInputs
    }

    private fun injectChip(word:String){

    }

    private fun searchAllMealsByIngredients(): List<Meal>? {
        return recipesManager?.getIndianFoodSearch?.searchByIngredients(searchBarInputs)
            ?.getSearchedMeals()

    }


    companion object {
        fun createFragment(recipesManager: RecipesManager): SearchFragment {
            val bundle = Bundle()
            bundle.putParcelable(Constants.MAIN_ACTIVITY_RECIPES, recipesManager)
            val fragment = SearchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
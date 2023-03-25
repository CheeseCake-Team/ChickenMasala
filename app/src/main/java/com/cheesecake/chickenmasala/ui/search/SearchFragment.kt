package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate


    private var recipesManager:RecipesManager? = null
    private lateinit var searchBarInputs:MutableList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipesManager = arguments?.getParcelable(Constants.MAIN_ACTIVITY_RECIPES)
    }


    override fun onStart() {
        super.onStart()

    }

    

    private fun collectAllIngredients(): MutableList<String>{
        searchBarInputs = mutableListOf<String>()
        binding.searchAutoCompleteTextView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val newEntry = binding.searchAutoCompleteTextView.text.toString()
                searchBarInputs.add(newEntry)
                binding.searchAutoCompleteTextView.setText("")
                true
            } else {
                false
            }
        }
        return searchBarInputs
    }

    private fun searchAllMealsByIngredients(): List<Meal>? {
        return recipesManager?.getIndianFoodSearch?.searchByIngredients(searchBarInputs)?.getSearchedMeals()

    }



    companion object{
        fun createFragment(recipesManager: RecipesManager):SearchFragment{
            val bundle = Bundle()
            bundle.putParcelable(Constants.MAIN_ACTIVITY_RECIPES,recipesManager)
            val fragment = SearchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
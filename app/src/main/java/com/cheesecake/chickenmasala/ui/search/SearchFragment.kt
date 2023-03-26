package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import com.cheesecake.chickenmasala.databinding.ChipsInjectBinding
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    private var recipesManager: RecipesManager? = null
    private lateinit var searchBarInputs: MutableList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipesManager = arguments?.getParcelable(Constants.MAIN_ACTIVITY_RECIPES)
        setupAutoCompleteTextView()
    }

    private fun setupAutoCompleteTextView() {
        val suggestions: List<String> = recipesManager?.indianIngredients?.distinct()?.sorted() ?: emptyList<String>()

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            suggestions
        )
        binding.searchAutoCompleteTextView.setAdapter(adapter)
    }


    override fun onStart() {
        super.onStart()
        collectAllIngredients()
    }

    private fun collectAllIngredients(): MutableList<String> {
        searchBarInputs = mutableListOf<String>()
        binding.searchAutoCompleteTextView.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val inputText = binding.searchAutoCompleteTextView.text.toString().trim()
                if (inputText.isNotEmpty()) {
                    searchBarInputs.add(inputText)
                    createChip(inputText)
                    binding.searchAutoCompleteTextView.setText("")
                    return@setOnKeyListener true
                }
            }
            return@setOnKeyListener false
        }
        return searchBarInputs
    }

    private fun createChip(text: String) {
        val chip = ChipsInjectBinding.inflate(layoutInflater)
        chip.customChip.text = text
        binding.result.addView(chip.root)

        // Add a click listener to remove the chip and the corresponding ingredient from the search bar inputs.
        chip.customChip.setOnCloseIconClickListener {
            searchBarInputs.remove(text)
            binding.result.removeView(chip.root)
        }
    }

    private fun searchAllMealsByIngredients(): List<Meal>? {
        return recipesManager?.getIndianFoodSearch?.searchByIngredients(searchBarInputs)
            ?.getSearchedMeals()
    }

    companion object {
        fun createFragment(recipesManager: RecipesManager) = SearchFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.MAIN_ACTIVITY_RECIPES, recipesManager)
            }
        }
    }
}
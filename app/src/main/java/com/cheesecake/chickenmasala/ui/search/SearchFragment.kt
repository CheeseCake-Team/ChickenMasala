package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ChipsInjectBinding
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.datasource.Constants
import com.cheesecake.chickenmasala.model.IndianFoodSearch
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment
import com.cheesecake.chickenmasala.ui.meals.MealsAdapter


class SearchFragment : BaseFragment<FragmentSearchBinding>(), BottomSheetListener {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    private val searchBarInputs = mutableListOf<String>()

    private lateinit var mealsAdapter: MealsAdapter
    private lateinit var foodSearch: IndianFoodSearch
    private lateinit var searchNameIngredient: IndianFoodSearch
    private lateinit var adapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodSearch = arguments?.getParcelable(Constants.Keys.ARGUMENT)!!
        installViews()
        addCallBacks()


    }

    private fun installViews() {
        mealsAdapter = MealsAdapter {  loadFragment(MealFragment.newInstance(it)) }
        binding.recyclerMeals.adapter = mealsAdapter
        setupAutoComplete()
    }

    private fun setupAutoComplete() {
        val allSuggestions = if (foodSearch.isSearchByName) RecipesManager.indianRecipesName
        else RecipesManager.indianIngredients
        adapter = StringArrayAdapter(allSuggestions, requireContext(), searchBarInputs)
        binding.searchAutoCompleteTextView.setAdapter(adapter)
    }

    private fun addCallBacks() {

        binding.apply {
            searchAutoCompleteTextView.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        binding.searchAutoCompleteTextView.hint = ""
                    } else {
                        binding.searchAutoCompleteTextView.hint = getString(R.string.search)
                    }
                }

            filterButton.setOnClickListener {
                showBottomSheet()
            }

            searchAutoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
                adapter.getItem(position)?.let { selectedItem ->
                    searchNameIngredient = if (!foodSearch.isSearchByName) {
                        searchBarInputs.add(selectedItem)
                        createChip(selectedItem)
                        foodSearch.searchAndFilter(ingredients = searchBarInputs)
                    } else {
                        foodSearch.searchAndFilter(name = selectedItem)
                    }
                    mealsAdapter.submitList(searchNameIngredient.getSearchedMeals())
                    binding.searchAutoCompleteTextView.setText("")
                }
            }
        }
    }

    private fun createChip(text: String) {
        ChipsInjectBinding.inflate(layoutInflater).apply {
            customChip.text = text
            customChip.setOnClickListener {
                searchBarInputs.remove(text)
                adapter.notifyDataSetChanged()
                binding.chipGroupHolder.removeView(root)
                foodSearch = foodSearch.searchAndFilter(ingredients = searchBarInputs)
                mealsAdapter.submitList(
                    foodSearch.getSearchedMeals()
                )
            }
            binding.chipGroupHolder.addView(root)
        }
    }

    private fun showBottomSheet() {
        if (!::searchNameIngredient.isInitialized)
            searchNameIngredient = foodSearch
        val bottomSheetFragment = FilterBottomSheet.newInstance(searchNameIngredient)
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    companion object {
        @JvmStatic
        fun newInstance(indianFoodSearch: IndianFoodSearch) = SearchFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.Keys.ARGUMENT, indianFoodSearch)
            }
        }
    }

    override fun onBottomSheetDataSelected(searchIndianFood: IndianFoodSearch) {
        foodSearch = searchIndianFood
        setupAutoComplete()
        mealsAdapter.submitList(
            foodSearch.getSearchedMeals()
        )
    }
}

interface BottomSheetListener {
    fun onBottomSheetDataSelected(searchIndianFood: IndianFoodSearch)
}


package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ChipsInjectBinding
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.model.IndianFoodSearch
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment

private const val ARG_INDIAN_FOOD_SEARCH = "indian_food"

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    private val searchBarInputs = mutableListOf<String>()

    private lateinit var mealsAdapter: SearchAdapter
    private lateinit var indianFoodSearch: IndianFoodSearch
    private lateinit var adapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        indianFoodSearch = arguments?.getParcelable(ARG_INDIAN_FOOD_SEARCH)!!

        installViews()
        addCallBacks()
    }

    private fun installViews() {
        mealsAdapter = SearchAdapter(MealsListener { loadMealFragment(it) })
        binding.recyclerMeals.adapter = mealsAdapter

        val allSuggestions = if (indianFoodSearch.isSearchByName) RecipesManager.indianRecipesName
        else RecipesManager.indianIngredients
        adapter = StringArrayAdapter(allSuggestions, requireContext(), searchBarInputs)
        binding.searchAutoCompleteTextView.setAdapter(adapter)
    }

    private fun addCallBacks() {
        binding.searchAutoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            adapter.getItem(position)?.let { selectedItem ->
                searchBarInputs.add(selectedItem)
                if (!indianFoodSearch.isSearchByName) {
                    createChip(selectedItem)
                    mealsAdapter.submitList(
                        indianFoodSearch.searchByIngredients(searchBarInputs).getSearchedMeals()
                    )
                } else {
                    mealsAdapter.submitList(
                        indianFoodSearch.searchByName(selectedItem).getSearchedMeals()
                    )
                }

                binding.searchAutoCompleteTextView.setText("")
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
                mealsAdapter.submitList(
                    indianFoodSearch.searchByIngredients(searchBarInputs).getSearchedMeals()
                )
            }
            binding.chipGroupHolder.addView(root)
        }
    }

    private fun loadMealFragment(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.fragment_container, MealFragment.createFragment(meal)).commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(indianFoodSearch: IndianFoodSearch) = SearchFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_INDIAN_FOOD_SEARCH, indianFoodSearch)
            }
        }
    }
}


package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ChipsInjectBinding
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.RecipesManager
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate
    private lateinit var searchBarInputs: MutableList<String>
    private lateinit var mealsAdapter: SearchAdapter
    private lateinit var suggestions: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        suggestions = RecipesManager.indianIngredients.toMutableList()
        searchBarInputs = mutableListOf()
        setupAutoCompleteTextView()
        installViews()
    }

    private fun installViews() {
        mealsAdapter = SearchAdapter(MealsListener { loadMealFragment(it) })
        mealsAdapter.submitList(RecipesManager.indianFoodSearch.getSearchedMeals())
        binding.recyclerMeals.adapter = mealsAdapter
    }


    private fun setupAutoCompleteTextView() {
        val allSuggestions: List<String> = RecipesManager.indianIngredients

        adapter = StringArrayAdapter(allSuggestions, requireContext(), searchBarInputs)

        binding.searchAutoCompleteTextView.setAdapter(adapter)
        setupListeners()
    }

    private fun setupListeners() {
        binding.searchAutoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = adapter.getItem(position) ?: return@setOnItemClickListener
            searchBarInputs.add(selectedItem)
            createChip(selectedItem)
            mealsAdapter.submitList(searchAllMealsByIngredients(searchBarInputs))
            binding.searchAutoCompleteTextView.setText("")
        }
    }

    private fun createChip(text: String) {
        val chipLayout = ChipsInjectBinding.inflate(layoutInflater)
        chipLayout.customChip.text = text
        binding.chipGroupHolder.addView(chipLayout.root)
        chipLayout.customChip.setOnClickListener {
            searchBarInputs.remove(text)
            adapter.notifyDataSetChanged()
            binding.chipGroupHolder.removeView(chipLayout.root)
            mealsAdapter.submitList(searchAllMealsByIngredients(searchBarInputs))
        }
    }

    private fun searchAllMealsByIngredients(ingredients: List<String>): List<Meal> {
        return RecipesManager.indianFoodSearch.searchByIngredients(ingredients).getSearchedMeals()
    }

    private fun loadMealFragment(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.add(R.id.fragment_container, MealFragment.createFragment(meal)).commit()
    }
}


package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ChipsInjectBinding
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
import com.cheesecake.chickenmasala.interactor.SearchAndFilterInteractor
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment
import com.cheesecake.chickenmasala.ui.meals.MealsAdapter
import kotlin.math.log


class SearchFragment : BaseFragment<FragmentSearchBinding>(), BottomSheetListener {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate
    val LOG_TAG = "search fragment"
    private lateinit var searchResult: List<Meal>
    private val searchBarInputs = mutableListOf<String>()
    private lateinit var mealsAdapter: MealsAdapter
    private lateinit var foodSearch: SearchAndFilterInteractor
    private val recipesInteractor: RecipesInteractor = RecipesInteractor()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodSearch = SearchAndFilterInteractor(recipesInteractor.getMeals())

        installViews()
        addCallBacks()
    }

    private fun installViews() {
        mealsAdapter = MealsAdapter { loadMealFragment(it) }
        binding.recyclerMeals.adapter = mealsAdapter
        setupAutoComplete()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun setupAutoComplete() {
        val allSuggestions =
            if (SearchAndFilterInteractor.isSearchByName) recipesInteractor.indianRecipesName
            else recipesInteractor.indianIngredients
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
                    searchResult = if (!SearchAndFilterInteractor.isSearchByName) {
                        searchBarInputs.add(selectedItem)
                        createChip(selectedItem)
                        foodSearch.searchByIngredients(ingredients = searchBarInputs)
                            .getSearchedMeals()
                    } else {
                        foodSearch.searchByName(name = selectedItem).getSearchedMeals()
                    }
                    if (searchResult.isEmpty()) {
                        Toast.makeText(context, "No result", Toast.LENGTH_SHORT).show()
                    }

                    mealsAdapter.submitList(searchResult)
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
                searchResult =
                    foodSearch.searchByIngredients(ingredients = searchBarInputs).getSearchedMeals()
                mealsAdapter.submitList(searchResult)
                if (searchResult.isEmpty()) {
                    binding.recyclerMeals.visibility = View.INVISIBLE
                    Toast.makeText(context, "No result", Toast.LENGTH_SHORT).show()
                }
            }
            binding.chipGroupHolder.addView(root)
        }
    }

    private fun loadMealFragment(meal: Meal) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealFragment.newInstance(meal))
            addToBackStack(null)
            commit()
        }
    }

    private fun showBottomSheet() {
        if (!::searchResult.isInitialized)
            searchResult = emptyList()
        val bottomSheetFragment =
            FilterBottomSheet.newInstance(SearchAndFilterInteractor(searchResult))
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }


    override fun onBottomSheetDataSelected(searchResult: List<Meal>) {
        setupAutoComplete()
        if (searchResult.isEmpty()) {
            binding.recyclerMeals.visibility = View.INVISIBLE
            Toast.makeText(context, "No result", Toast.LENGTH_SHORT).show()

        }
        mealsAdapter.submitList(searchResult)
    }
}

interface BottomSheetListener {
    fun onBottomSheetDataSelected(searchResult: List<Meal>)
}


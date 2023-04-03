package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.ChipsInjectBinding
import com.cheesecake.chickenmasala.databinding.FragmentSearchBinding
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
import com.cheesecake.chickenmasala.interactor.SearchAndFilterInteractor
import com.cheesecake.chickenmasala.model.Constants.Keys.SEARCH_BAR_INPUTS_STATE_KEY
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment
import com.cheesecake.chickenmasala.ui.meals.MealsAdapter


class SearchFragment : BaseFragment<FragmentSearchBinding>(), BottomSheetListener {
    override val bindingInflater: (LayoutInflater) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    private lateinit var searchResult: List<Meal>
    private var searchBarInputs = mutableListOf<String>()
    private lateinit var mealsAdapter: MealsAdapter
    private val recipesInteractor: RecipesInteractor = RecipesInteractor()
    private var foodSearch = SearchAndFilterInteractor(recipesInteractor.getMeals())
    private lateinit var adapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBarInputs = savedInstanceState?.getStringArrayList(SEARCH_BAR_INPUTS_STATE_KEY) ?: mutableListOf()

        foodSearch = SearchAndFilterInteractor(recipesInteractor.getMeals())

        installViews()
        addCallBacks()
    }

    private fun installViews() {
        mealsAdapter = MealsAdapter { loadMealFragment(it) }
        binding.recyclerMeals.adapter = mealsAdapter
        setupAutoComplete()
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
                    if (!SearchAndFilterInteractor.isSearchByName) {
                        searchBarInputs.add(selectedItem)
                        createChip(selectedItem)
                    } else {
                        searchByName(selectedItem)
                    }
                }
            }
        }
    }

    private fun createChip(text: String) {
        ChipsInjectBinding.inflate(layoutInflater).apply {
            customChip.text = text
            searchByIngredients()
            customChip.setOnClickListener {
                searchBarInputs.remove(text)
                binding.chipGroupHolder.removeView(root)
                searchByIngredients()
            }
            binding.chipGroupHolder.addView(root)
        }
    }

    private fun searchByIngredients() {
        searchResult =
            foodSearch.searchByIngredients(ingredients = searchBarInputs).getSearchedMeals()
        mealsAdapter.submitList(searchResult)
        binding.searchAutoCompleteTextView.setText("")
        binding.recyclerMeals.visibility =
            if (searchResult.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    private fun searchByName(name: String) {
        searchResult = foodSearch.searchByName(name = name).getSearchedMeals()
        mealsAdapter.submitList(searchResult)
        binding.searchAutoCompleteTextView.setText("")
        binding.recyclerMeals.visibility =
            if (searchResult.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    private fun loadMealFragment(meal: Meal) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealFragment.newInstance(meal))
            addToBackStack(null)
            commit()
        }
    }

    private fun showBottomSheet() {
        if (!::searchResult.isInitialized) searchResult = emptyList()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(SEARCH_BAR_INPUTS_STATE_KEY, ArrayList(searchBarInputs))
    }

    override fun hasBackButtonOrNot() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun setActionBarTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.search)

    }

}

interface BottomSheetListener {
    fun onBottomSheetDataSelected(searchResult: List<Meal>)
}


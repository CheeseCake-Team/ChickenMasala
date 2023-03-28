package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FilterBottomSheetBarBinding
import com.cheesecake.chickenmasala.model.IndianFoodSearch
import com.cheesecake.chickenmasala.model.MealCourse
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
private const val ARG_INDIAN_FOOD_SEARCH = "indian_food"

class FilterBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: FilterBottomSheetBarBinding
    private lateinit var searchIndianFood: IndianFoodSearch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FilterBottomSheetBarBinding.inflate(inflater, container, false)
        searchIndianFood = arguments?.getParcelable(ARG_INDIAN_FOOD_SEARCH)!!

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        setupViews()
        addCallbacks()
        return binding.root
    }

    private fun setupViews() {
        binding.apply {
            if (searchIndianFood.isSearchByName) chipName.isChecked = true
            else chipIngredient.isChecked = true

            when (searchIndianFood.selectedTimeRange) {
                5..10 -> chip510min.isChecked = true

                10..15 -> chip1015min.isChecked = true

                15..30 -> chip1530min.isChecked = true
            }
            when (searchIndianFood.selectedMealCourse) {
                MealCourse.SOUPS -> chipSoups.isChecked = true
                MealCourse.SPICY -> chipSpicy.isChecked = true
                MealCourse.SALADS -> chipSalads.isChecked = true
                MealCourse.VEGETABLES -> chipVegetables.isChecked = true
                MealCourse.APPETIZER -> chipAppetizer.isChecked = true
                MealCourse.BREAKFAST -> chipBreakfast.isChecked = true
                MealCourse.DRINKS -> chipDrink.isChecked = true
                else -> {
                    chipSoups.isChecked = false
                    chipSpicy.isChecked = false
                    chipSalads.isChecked = false
                    chipVegetables.isChecked = false
                    chipAppetizer.isChecked = false
                    chipBreakfast.isChecked = false
                    chipDrink.isChecked = false
                }
            }

        }
    }


    private fun addCallbacks() {
        binding.buttonFilter.setOnClickListener {
            binding.apply {
                when (chipGroupSearch.checkedChipId) {
                    R.id.chip_ingredient -> searchIndianFood.isSearchByName = false
                    R.id.chip_name -> searchIndianFood.isSearchByName = true
                }

                val time: IntRange? = when (chipGroupTime.checkedChipId) {
                    R.id.chip_5_10min -> {
                        searchIndianFood.selectedTimeRange = 5..10
                        5..10
                    }

                    R.id.chip_10_15min -> {
                        searchIndianFood.selectedTimeRange = 10..15
                        10..15
                    }

                    R.id.chip_15_30min -> {
                        searchIndianFood.selectedTimeRange = 15..30
                        15..30
                    }
                    else -> null
                }

                val course: MealCourse? = when (chipGroupCategory.checkedChipId) {
                    R.id.chip_salads -> {
                        searchIndianFood.selectedMealCourse = MealCourse.SALADS
                        MealCourse.SALADS
                    }
                    R.id.chip_breakfast -> {
                        searchIndianFood.selectedMealCourse = MealCourse.BREAKFAST
                        MealCourse.BREAKFAST
                    }
                    R.id.chip_soups -> {
                        searchIndianFood.selectedMealCourse = MealCourse.SOUPS
                        MealCourse.SOUPS
                    }
                    R.id.chip_appetizer -> {
                        searchIndianFood.selectedMealCourse = MealCourse.APPETIZER
                        MealCourse.APPETIZER
                    }
                    R.id.chip_vegetables -> {
                        searchIndianFood.selectedMealCourse = MealCourse.VEGETABLES
                        MealCourse.VEGETABLES
                    }
                    R.id.chip_drink -> {
                        searchIndianFood.selectedMealCourse = MealCourse.DRINKS
                        MealCourse.DRINKS
                    }
                    R.id.chip_spicy -> {
                        searchIndianFood.selectedMealCourse = MealCourse.SPICY
                        MealCourse.SPICY
                    }


                    else -> null
                }
                searchIndianFood =
                    searchIndianFood.searchAndFilter(timeRange = time, course = course).apply {
                        selectedMealCourse = searchIndianFood.selectedMealCourse
                        selectedTimeRange = searchIndianFood.selectedTimeRange
                        isSearchByName = searchIndianFood.isSearchByName
                    }


            }
            val listener = parentFragment as? SearchFragment
            listener?.onBottomSheetDataSelected(searchIndianFood)
            dismiss()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(searchIndianFood: IndianFoodSearch) = FilterBottomSheet().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_INDIAN_FOOD_SEARCH, searchIndianFood)
            }
        }
    }
}
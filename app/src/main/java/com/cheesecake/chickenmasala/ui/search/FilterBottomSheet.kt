package com.cheesecake.chickenmasala.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FilterBottomSheetBarBinding
import com.cheesecake.chickenmasala.interactor.SearchAndFilterInteractor
import com.cheesecake.chickenmasala.model.MealCourse
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val ARGUMENT_INDIAN_FOOD_SEARCH = "indian_food"

class FilterBottomSheet : BottomSheetDialogFragment() {

    private var time: IntRange? = null
    private var course: MealCourse? = null
    private lateinit var searchIndianFood: SearchAndFilterInteractor
    lateinit var binding: FilterBottomSheetBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FilterBottomSheetBarBinding.inflate(inflater, container, false)
        searchIndianFood = arguments?.getParcelable(ARGUMENT_INDIAN_FOOD_SEARCH)!!

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        setupViews()
        addCallbacks()
        return binding.root
    }

    private fun setupViews() {
        binding.apply {
            if (SearchAndFilterInteractor.isSearchByName) chipName.isChecked = true
            else chipIngredient.isChecked = true

            when (SearchAndFilterInteractor.selectedTimeRange) {
                5..10 -> chip510min.isChecked = true

                10..15 -> chip1015min.isChecked = true

                15..30 -> chip1530min.isChecked = true
            }
            when (SearchAndFilterInteractor.selectedMealCourse) {
                MealCourse.SOUPS -> chipSoups.isChecked = true
                MealCourse.SPICY -> chipSpicy.isChecked = true
                MealCourse.RICE -> chipRice.isChecked = true
                MealCourse.VEGETABLES -> chipVegetables.isChecked = true
                MealCourse.MASALA -> chipMasala.isChecked = true
                MealCourse.BREAKFAST -> chipBreakfast.isChecked = true
                MealCourse.CAKES -> chipCake.isChecked = true
                MealCourse.BAKED -> chipBaked.isChecked = true
                else -> {
                    chipSoups.isChecked = false
                    chipSpicy.isChecked = false
                    chipRice.isChecked = false
                    chipVegetables.isChecked = false
                    chipMasala.isChecked = false
                    chipBreakfast.isChecked = false
                    chipCake.isChecked = false
                    chipBaked.isChecked = false
                }
            }

        }
    }


    private fun addCallbacks() {
        binding.buttonFilter.setOnClickListener {

            binding.apply {
                when (chipGroupSearch.checkedChipId) {
                    R.id.chip_ingredient -> SearchAndFilterInteractor.isSearchByName = false
                    R.id.chip_name -> SearchAndFilterInteractor.isSearchByName = true
                }

                time = when (chipGroupTime.checkedChipId) {
                    R.id.chip_5_10min -> {
                        SearchAndFilterInteractor.selectedTimeRange = 5..10
                        5..10
                    }

                    R.id.chip_10_15min -> {
                        SearchAndFilterInteractor.selectedTimeRange = 10..15
                        10..15
                    }

                    R.id.chip_15_30min -> {
                        SearchAndFilterInteractor.selectedTimeRange = 15..30
                        15..30
                    }
                    else -> {
                        SearchAndFilterInteractor.selectedTimeRange = null
                        null
                    }
                }

                course = when (chipGroupCategory.checkedChipId) {
                    R.id.chip_baked -> {
                        SearchAndFilterInteractor.selectedMealCourse = MealCourse.BAKED
                        MealCourse.BAKED
                    }
                    R.id.chip_breakfast -> {
                        SearchAndFilterInteractor.selectedMealCourse = MealCourse.BREAKFAST
                        MealCourse.BREAKFAST
                    }
                    R.id.chip_soups -> {
                        SearchAndFilterInteractor.selectedMealCourse = MealCourse.SOUPS
                        MealCourse.SOUPS
                    }
                    R.id.chip_masala -> {
                        SearchAndFilterInteractor.selectedMealCourse = MealCourse.MASALA
                        MealCourse.MASALA
                    }
                    R.id.chip_vegetables -> {
                        SearchAndFilterInteractor.selectedMealCourse = MealCourse.VEGETABLES
                        MealCourse.VEGETABLES
                    }
                    R.id.chip_cake -> {
                        SearchAndFilterInteractor.selectedMealCourse = MealCourse.CAKES
                        MealCourse.CAKES
                    }
                    R.id.chip_spicy -> {
                        SearchAndFilterInteractor.selectedMealCourse = MealCourse.SPICY
                        MealCourse.SPICY
                    }
                    R.id.chip_rice -> {
                        SearchAndFilterInteractor.selectedMealCourse = MealCourse.RICE
                        MealCourse.RICE
                    }
                    else -> {
                        SearchAndFilterInteractor.selectedMealCourse = null
                        null
                    }
                }

            }
            val searchResult =
                searchIndianFood.filterMealsByCourseAndTimeRange(timeRange = time, course = course)
                    .getSearchedMeals()
            val listener = parentFragment as? SearchFragment
            listener?.onBottomSheetDataSelected(searchResult)
            dismiss()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(searchIndianFood: SearchAndFilterInteractor) = FilterBottomSheet().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_INDIAN_FOOD_SEARCH, searchIndianFood)
            }
        }
    }
}
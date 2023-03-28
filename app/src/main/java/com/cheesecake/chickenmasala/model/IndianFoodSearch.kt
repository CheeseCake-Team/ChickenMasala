package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class IndianFoodSearch(private val meals: List<Meal>) : Parcelable {
    @IgnoredOnParcel
    private var searchedMeals = meals

    @IgnoredOnParcel
    var selectedTimeRange: IntRange? = null

    @IgnoredOnParcel
    var selectedMealCourse: MealCourse? = null
    fun searchAndFilter(
        name: String = "",
        course: MealCourse? = null,
        timeRange: IntRange? = null,
        ingredients: List<String> = emptyList()
    ) = IndianFoodSearch(meals).apply {
        searchedMeals = meals
            .let { if (name.isNotEmpty()) it.filter { it.translatedRecipeName.contains(name) } else it }
            .let { if (course != null) it.filter { it.course == course } else it }
            .let { if (timeRange != null) it.filter { it.TotalTimeInMinutes in timeRange } else it }
            .let {
                if (ingredients.isNotEmpty()) it.filter {
                    it.cleanedIngredients.containsAll(
                        ingredients
                    )
                } else it
            }
    }

    fun getSearchedMeals(): List<Meal> = searchedMeals

    @IgnoredOnParcel
    var isSearchByName = false
}
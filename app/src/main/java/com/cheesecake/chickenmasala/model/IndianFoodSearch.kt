package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class IndianFoodSearch(private val meals: List<Meal>) : Parcelable {

    @IgnoredOnParcel
    private var searchedMeals = meals

    fun searchByName(name: String) = IndianFoodSearch(searchedMeals).apply {
        searchedMeals = searchedMeals.filter { it.translatedRecipeName.contains(name) }
    }

    fun filterMealsByCourse(course: MealCourse) = IndianFoodSearch(searchedMeals).apply {
        searchedMeals = searchedMeals.filter { it.course == course }
    }

    fun filterMealsByRangeTime(range: IntRange) = IndianFoodSearch(searchedMeals).apply {
        searchedMeals = searchedMeals.filter { it.TotalTimeInMinutes in range }
    }

    fun searchByIngredients(ingredients: List<String>) = IndianFoodSearch(searchedMeals).apply {
        searchedMeals = if (ingredients.isNotEmpty()) {
            searchedMeals.filter { it.cleanedIngredients.containsAll(ingredients) }
        } else {
            emptyList()
        }

    }

    fun getSearchedMeals(): List<Meal> = searchedMeals


    @IgnoredOnParcel
    var isSearchByName = true

}
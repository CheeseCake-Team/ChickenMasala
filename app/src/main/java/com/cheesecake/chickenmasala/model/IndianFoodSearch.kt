package com.cheesecake.chickenmasala.model

class IndianFoodSearch(private val meals: List<Meal>) {

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
}
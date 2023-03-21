package com.cheesecake.chickenmasala.model

class IndianFoodSearch(private val meals: List<Meal>) {

    private var searchedMeals = meals

    fun searchByName(name: String) = IndianFoodSearch(searchedMeals).apply {
        searchedMeals = searchedMeals.filter { it.translatedRecipeName.contains(name) }
    }


    fun filterMealsByCourse(course: MealCourse) = IndianFoodSearch(searchedMeals).apply {
        searchedMeals = searchedMeals.filter { it.course == course }
    }


    fun filterMealsByRangeTime(time: RangeTimeInMinutes) = IndianFoodSearch(searchedMeals).apply {
        searchedMeals = searchedMeals.filter { it.TotalTimeInMinutes >= time.from && it.TotalTimeInMinutes <= time.to }
    }

    fun searchByIngredients(ingredients: List<String>) = IndianFoodSearch(searchedMeals).apply {
        searchedMeals =
            searchedMeals.filter { it.translatedIngredients.containsAll(ingredients) }
    }


    fun getAllMeals(): List<Meal> = meals
}
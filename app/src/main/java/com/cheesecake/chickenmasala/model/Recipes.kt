package com.cheesecake.chickenmasala.model

class Recipes(val meals: List<Meal>) {
    private val indianMeals = meals.filter { it.cuisine == "Indian" }

    val indianFood = IndianFoodSearch(indianMeals)
    val cuisines = meals.map { it.cuisine }.distinct()

    fun getCuisineRecipe(cuisine: String): List<Meal> {
        return meals.filter { it.cuisine == cuisine }
    }

    fun getRandomMeals(limit: Int): List<Meal> = indianMeals.shuffled().take(limit)

    fun getFastMeals(limit: Int): List<Meal> = indianMeals.sortedBy { it.TotalTimeInMinutes }.take(limit)
}
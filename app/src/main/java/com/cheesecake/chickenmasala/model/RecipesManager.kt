package com.cheesecake.chickenmasala.model

object RecipesManager {
    private lateinit var meals: List<Meal>
    private lateinit var indianMeals: List<Meal>
    private lateinit var indianMealsForToday: List<Meal>

    fun initialize(meals: List<Meal>) {
        this.meals = meals
        this.indianMeals = meals.filter { it.cuisine == "Indian" }
        this.indianMealsForToday = indianMeals.shuffled().take(10)
    }


    val indianIngredients: List<String>
        get() = indianMeals.map { it.cleanedIngredients }.flatten().distinct().sorted()

    val indianFoodSearch: IndianFoodSearch
        get() = IndianFoodSearch(indianMeals)

    fun getRandomMeals(): List<Meal> = indianMealsForToday

    fun getFastMeals(): List<Meal> = indianMeals.sortedBy { it.TotalTimeInMinutes }.take(10)
}
package com.cheesecake.chickenmasala.model

object RecipesManager {
    private lateinit var meals: List<Meal>
    private lateinit var indianMealsForToday: List<Meal>

    fun initialize(meals: List<Meal>) {
        this.meals = meals
        this.indianMealsForToday = meals.shuffled().take(10)
    }

    val indianRecipesName: List<String>
        get() = meals.map { it.translatedRecipeName }

    val indianIngredients: List<String>
        get() = meals.map { it.cleanedIngredients }.flatten().distinct().sorted()

    val indianFoodSearch: IndianFoodSearch
        get() = IndianFoodSearch(meals)

    fun getRandomMeals(): List<Meal> = indianMealsForToday

    fun getFastMeals(): List<Meal> = meals.sortedBy { it.TotalTimeInMinutes }.take(10)

    fun getLessIngredientMeals() = meals.sortedBy { it.ingredientCount }.take(10)
}

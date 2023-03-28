package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
object RecipesManager: Parcelable {
    private lateinit var meals: List<Meal>
    private lateinit var indianMeals: List<Meal>
    private lateinit var indianMealsForToday: List<Meal>

    fun initialize(meals: List<Meal>) {
        this.meals = meals
        this.indianMeals = meals.filter { it.cuisine == "Indian" }
        this.indianMealsForToday = indianMeals.shuffled().take(10)
    }

    val indianRecipesName: List<String>
        get() = indianMeals.map { it.translatedRecipeName }

    val indianIngredients: List<String>
        get() = indianMeals.map { it.cleanedIngredients }.flatten().distinct().sorted()

    val indianFoodSearch: IndianFoodSearch
        get() = IndianFoodSearch(indianMeals)

    fun getRandomMeals(): List<Meal> = indianMealsForToday

    fun getFastMeals(): List<Meal> = indianMeals.sortedBy { it.TotalTimeInMinutes }.take(10)

    fun getLessIngredientMeals() = indianMeals.sortedBy { it.ingredientCount }.take(10)
}

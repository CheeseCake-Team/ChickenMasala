package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RecipesManager(private val meals: List<Meal>): Parcelable {
    private val indianMeals = meals.filter { it.cuisine == "Indian" }
    private val indianMealsForToday = indianMeals.shuffled().take(10)


    val indianIngredients = indianMeals.map { it.cleanedIngredients }.flatten().distinct()

    val getIndianFoodSearch = IndianFoodSearch(indianMeals)

    fun getCuisines() = meals.map { it.cuisine }.distinct()

    fun getCuisineRecipes(cuisine: String): List<Meal> {
        return meals.filter { it.cuisine == cuisine }
    }

    fun getRandomMeals() = indianMealsForToday

    fun getFastMeals() = indianMeals.sortedBy { it.TotalTimeInMinutes }.take(10)

    fun getLessIngredientMeals() = indianMeals.sortedBy { it.ingredientCount }.take(10)
}
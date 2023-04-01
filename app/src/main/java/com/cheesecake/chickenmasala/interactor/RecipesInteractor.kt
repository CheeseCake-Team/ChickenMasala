package com.cheesecake.chickenmasala.interactor

import com.cheesecake.chickenmasala.model.Meal

class RecipesInteractor {
    companion object {
        private var meals: List<Meal>? = null
        private var indianMealsForToday: List<Meal>? = null

        fun initialize(meals: List<Meal>) {
            if (RecipesInteractor.meals == null && indianMealsForToday == null) {
                RecipesInteractor.meals = meals
                indianMealsForToday = meals.shuffled().take(10)
            }
        }
    }


    val indianRecipesName: List<String>
        get() = meals?.map { it.translatedRecipeName }!!

    val indianIngredients: List<String>
        get() = meals?.map { it.cleanedIngredients }?.flatten()?.distinct()?.sorted()!!

    val cuisines: List<String>
        get() = meals?.map { it.cuisine }?.distinct()?.sorted()!!

    fun getCuisineRecipes(cuisineName: String) = meals?.filter { it.cuisine == cuisineName }!!

    fun getRandomMeals(): List<Meal> = indianMealsForToday!!

    fun getFastMeals(): List<Meal> = meals?.sortedBy { it.TotalTimeInMinutes }?.take(10)!!

    fun getLessIngredientMeals() = meals?.sortedBy { it.ingredientCount }?.take(10)

    fun getMeals() = meals!!
}

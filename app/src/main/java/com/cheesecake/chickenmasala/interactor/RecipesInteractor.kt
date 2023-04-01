package com.cheesecake.chickenmasala.interactor

import com.cheesecake.chickenmasala.model.Meal

class RecipesInteractor {
    companion object {
        private var meals: List<Meal>? = null
        private var indianMeals: List<Meal>? = null
        private var indianMealsForToday: List<Meal>? = null

        fun initialize(meals: List<Meal>) {
            if (RecipesInteractor.meals == null && indianMealsForToday == null) {
                RecipesInteractor.meals = meals
                indianMeals = meals.filter { it.cuisine == "Indian" }
                indianMealsForToday = indianMeals!!.shuffled().take(20)
            }
        }
    }


    val indianRecipesName: List<String>
        get() = indianMeals?.map { it.translatedRecipeName }!!

    val indianIngredients: List<String>
        get() = indianMeals?.map { it.cleanedIngredients }?.flatten()?.distinct()?.sorted()!!

    val cuisines: List<String>
        get() = meals?.map { it.cuisine }?.distinct()?.sorted()!!

    fun getCuisineRecipes(cuisineName: String) = meals?.filter { it.cuisine == cuisineName }!!

    fun getRandomCuisineImage(cuisineName: String) =
        meals?.filter { it.cuisine == cuisineName }?.random()?.imageUrl!!

    fun getRandomMeals(): List<Meal> = indianMealsForToday!!

    fun getFastMeals(): List<Meal> = indianMeals?.sortedBy { it.TotalTimeInMinutes }?.take(20)!!

    fun getLessIngredientMeals() = indianMeals?.sortedBy { it.ingredientCount }?.take(20)

    fun getMeals() = indianMeals!!
}

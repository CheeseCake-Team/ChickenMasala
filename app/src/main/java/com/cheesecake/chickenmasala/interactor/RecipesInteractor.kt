package com.cheesecake.chickenmasala.interactor

import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.Recommendation

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

    private fun getRandomMeals(): List<Meal> = indianMealsForToday!!

    private fun getFastMeals(): List<Meal> = indianMeals?.sortedBy { it.TotalTimeInMinutes }?.take(20)!!

    private fun getLessIngredientMeals() = indianMeals?.sortedBy { it.ingredientCount }?.take(20)

    fun getMeals() = indianMeals!!

    fun getRandomMealsRecommendation() = Recommendation(R.string.recipes_of_the_day,
        getRandomMeals())

    fun getFastestMealsRecommendation() = Recommendation(R.string.fastest_recipes,
        getFastMeals())

    fun getLessIngredientRecommendation() = Recommendation(R.string.low_ingredient_recipes,
        getLessIngredientMeals()!!)
}

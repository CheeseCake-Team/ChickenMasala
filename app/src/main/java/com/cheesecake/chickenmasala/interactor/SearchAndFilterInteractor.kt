package com.cheesecake.chickenmasala.interactor

import android.os.Parcelable
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.MealCourse
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class SearchAndFilterInteractor(private val meals: List<Meal>) : Parcelable {

    @IgnoredOnParcel
    private var searchedMeals = emptyList<Meal>()

    companion object {
        var selectedTimeRange: IntRange? = null

        var selectedMealCourse: MealCourse? = null

        var isSearchByName = false
    }

//    fun searchAndFilter(
//        name: String = "",
//        course: MealCourse? = null,
//        timeRange: IntRange? = null,
//        ingredients: List<String> = emptyList()
//    ) = SearchAndFilterInteractor(meals).apply {
//        searchedMeals = meals
//            .let { if (name.isNotEmpty()) it.filter { it.translatedRecipeName.contains(name) } else it }
//            .let { if (course != null) it.filter { it.course == course } else it }
//            .let { if (timeRange != null) it.filter { it.TotalTimeInMinutes in timeRange } else it }
//            .let { filteredMeals ->
//                if (ingredients.isNotEmpty()) {
//                    val ingredientSet = ingredients.toSet()
//                    filteredMeals.filter {
//                        it.cleanedIngredients.toSet().containsAll(ingredientSet)
//                    }
//                } else {
//                    emptyList()
//                }
//            }
//    }

    fun searchByName(name: String) = SearchAndFilterInteractor(meals).apply {
        searchedMeals = searchedMeals.filter { it.translatedRecipeName.contains(name) }
    }

    fun searchByIngredients(ingredients: List<String>) =
        SearchAndFilterInteractor(meals).apply {
            searchedMeals = meals.let { filteredMeals ->
                if (ingredients.isNotEmpty()) {
                    val ingredientSet = ingredients.toSet()
                    filteredMeals.filter {
                        it.cleanedIngredients.toSet().containsAll(ingredientSet)
                    }
                } else {
                    emptyList()
                }
            }
        }

    fun filterMealsByCourseAndTimeRange(course: MealCourse?, timeRange: IntRange? = null) =
        SearchAndFilterInteractor(meals).apply {
            searchedMeals = meals
                .let { if (course != null) it.filter { it.course == course } else it }
                .let { if (timeRange != null) it.filter { it.TotalTimeInMinutes in timeRange } else it }
        }


    fun getSearchedMeals(): List<Meal> = searchedMeals

}

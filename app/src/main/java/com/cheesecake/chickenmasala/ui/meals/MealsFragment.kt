package com.cheesecake.chickenmasala.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.interactor.RecipesInteractor
import com.cheesecake.chickenmasala.interactor.SearchAndFilterInteractor
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.MealCourse
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meal.MealFragment
import java.util.*
import kotlin.properties.Delegates

private const val ARG_MEAL_COURSE = "meal_course"

class MealsFragment :
    BaseFragment<FragmentMealsBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate

    private lateinit var string: String
    private var type by Delegates.notNull<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        string = arguments?.getString(ARG_MEAL_COURSE)!!
        type = arguments?.getInt("type")!!
        setupViews()
    }

    private fun setupViews() {
        val recipesInteractor = RecipesInteractor()
        val indianFoodSearch = SearchAndFilterInteractor(recipesInteractor.getMeals())
        val meals = if (type == 1) {
            val mealCourse = MealCourse.valueOf(string.uppercase(Locale.ROOT))
            indianFoodSearch.filterMealsByCourseAndTimeRange(course = mealCourse).getSearchedMeals()
        } else {
            recipesInteractor.getCuisineRecipes(string)
        }

        val mealsAdapter = MealsAdapter { loadMealFragment(it) }
        mealsAdapter.submitList(meals)
        binding.recyclerMeals.adapter = mealsAdapter
    }

    private fun loadMealFragment(meal: Meal) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, MealFragment.newInstance(meal))
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(string: String, type: Int) = MealsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_MEAL_COURSE, string)
                putInt("type", type)
            }
        }
    }

}

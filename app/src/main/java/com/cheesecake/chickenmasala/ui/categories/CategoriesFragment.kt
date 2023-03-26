package com.cheesecake.chickenmasala.ui.categories

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentCategoriesBinding
import com.cheesecake.chickenmasala.ui.base.BaseFragment
import com.cheesecake.chickenmasala.ui.meals.MealsFragment
import kotlinx.android.parcel.Parcelize

class CategoriesFragment :
    BaseFragment<FragmentCategoriesBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding =
        FragmentCategoriesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = MealCourse.values()

        installViews(category)
    }

    private fun installViews(categories: Array<MealCourse>) {
        val categoryAdapter = CategoriesAdapter(CategoriesListener { loadMealFragment(it) })
        categoryAdapter.submitList(categories.toList())
        binding.recyclerCategories.adapter = categoryAdapter
    }

    private fun loadMealFragment(mealCourse: com.cheesecake.chickenmasala.model.MealCourse) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, MealsFragment.createFragment(mealCourse))
        transaction.addToBackStack(null)
        transaction.commit()
    }
    @Parcelize
    enum class MealCourse( val CourseName: String, val imageResourceId: Int) : Parcelable {
        SOUP("Soup", R.drawable.cooking_image), APPETIZER(
            "Soup",
            R.drawable.cooking_image
        ),
        SPICY("Soup", R.drawable.cooking_image)
    }
}
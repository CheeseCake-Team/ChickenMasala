package com.cheesecake.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.databinding.FragmentMealsBinding
import com.cheesecake.chickenmasala.databinding.ItemMealCardBinding
import com.cheesecake.chickenmasala.model.Meal
import com.squareup.picasso.Picasso
import kotlin.time.Duration.Companion.minutes

class MealsFragment(private val meals: List<Meal>) : BaseFragment<FragmentMealsBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentMealsBinding =
        FragmentMealsBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillLinerLayoutWithCardItem()
    }

    private fun fillLinerLayoutWithCardItem() {
        val cardItemView = ItemMealCardBinding.inflate(layoutInflater)

        meals.forEach {meal ->
            cardItemView.tvNameOfMeal.text = meal.translatedRecipeName
            cardItemView.tvTimeToMakeMeal.text = meal.TotalTimeInMinutes.toString()
            cardItemView.tvLocationOfMeal.text = meal.cuisine

            Glide.with(this).load(meal.imageUrl).into(cardItemView.imageCardOfMeal)

            cardItemView.root.setOnClickListener {
                loadMealFragment(meal)
            }

        }


        binding.linear.addView(cardItemView.root)

    }

    private fun loadMealFragment(meal: Meal) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, MealFragment(meal))
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
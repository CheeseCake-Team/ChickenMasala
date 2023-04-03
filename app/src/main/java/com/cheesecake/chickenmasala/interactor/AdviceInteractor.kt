package com.cheesecake.chickenmasala.interactor

import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.model.Advice

class AdviceInteractor() {
    val prepareFoodAdviceList = prepareFoodAdviceList()
    private fun prepareFoodAdviceList() = mutableListOf(
        Advice(
            R.string.eat_fruits_veggies_title,
            R.string.eat_fruits_veggies_body,
            R.drawable.fruits_image
        ),

        Advice(
            R.string.limit_processed_foods_title,
            R.string.limit_processed_foods_body,
            R.drawable.food_image_six
        ),

        Advice(
            R.string.choose_lean_protein_title,
            R.string.choose_lean_protein_body,
            R.drawable.food_image_thirteen
        ),

        Advice(
            R.string.reduce_added_sugar_title,
            R.string.reduce_added_sugar_body,
            R.drawable.suger_image
        ),

        Advice(
            R.string.stay_hydrated_title,
            R.string.stay_hydrated_body,
            R.drawable.water_image
        ),

        Advice(
            R.string.choose_healthy_fats_title,
            R.string.choose_healthy_fats_body,
            R.drawable.food_image_ten
        ),

        Advice(
            R.string.eat_breakfast_title,
            R.string.eat_breakfast_body,
            R.drawable.food_image_nine
        )
    )
}
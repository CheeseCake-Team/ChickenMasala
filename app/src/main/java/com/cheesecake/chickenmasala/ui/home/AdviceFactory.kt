package com.cheesecake.chickenmasala.ui.home

import android.content.Context
import com.cheesecake.chickenmasala.R
import com.cheesecake.chickenmasala.model.Advice

class AdviceFactory(val context: Context) {
    val prepareFoodAdviceList = prepareFoodAdviceList()
    private fun prepareFoodAdviceList() = mutableListOf(
        Advice(
            context.getString(R.string.eat_fruits_veggies_title),
            context.getString(R.string.eat_fruits_veggies_body),
            R.drawable.fruits_image
        ),

        Advice(
            context.getString(R.string.limit_processed_foods_title),
            context.getString(R.string.limit_processed_foods_body),
            R.drawable.food_image_six
        ),

        Advice(
            context.getString(R.string.choose_lean_protein_title),
            context.getString(R.string.choose_lean_protein_body),
            R.drawable.food_image_thirteen
        ),

        Advice(
            context.getString(R.string.reduce_added_sugar_title),
            context.getString(R.string.reduce_added_sugar_body), R.drawable.suger_image
        ),

        Advice(
            context.getString(R.string.stay_hydrated_title),
            context.getString(R.string.stay_hydrated_body), R.drawable.water_image
        ),

        Advice(
            context.getString(R.string.choose_healthy_fats_title),
            context.getString(R.string.choose_healthy_fats_body),
            R.drawable.food_image_ten
        ),

        Advice(
            context.getString(R.string.eat_breakfast_title),
            context.getString(R.string.eat_breakfast_body), R.drawable.food_image_nine
        )
    )
}
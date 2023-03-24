package com.cheesecake.chickenmasala.datasource

import com.cheesecake.chickenmasala.model.Meal


interface MealsDataSource {
    fun getAllMealsData(): List<Meal>
}
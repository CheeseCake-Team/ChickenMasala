package com.cheesecake.chickenmasala.dataSource

import com.cheesecake.chickenmasala.model.Meal


interface MealsDataSource {
    fun getAllMealsData(): List<Meal>
}
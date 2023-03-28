package com.cheesecake.chickenmasala.datasource

import com.cheesecake.chickenmasala.model.Meal
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class CsvDataSource(private val parser: CsvParser, private val fileReader: InputStream) :
    MealsDataSource {
    override fun getAllMealsData(): List<Meal> {
        val buffer = BufferedReader(InputStreamReader(fileReader))
        val list = buffer.readText().split(",")
        buffer.close()

        val modifiedList = list.mapIndexed { index, element ->
            if (index % 8 == 0) element.split("\n") else listOf(element)
        }.flatten()

        return modifiedList.drop(9).chunked(9).map { parser.parseMeal(it) }
    }
}
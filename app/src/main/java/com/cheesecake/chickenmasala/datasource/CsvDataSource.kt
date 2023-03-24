package com.cheesecake.chickenmasala.datasource

import com.cheesecake.chickenmasala.model.Meal
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class CsvDataSource(private val parser: CsvParser, private val fileReader: InputStream) :
    MealsDataSource {

    override fun getAllMealsData(): List<Meal> {

        val buffer = BufferedReader(InputStreamReader(fileReader))
        buffer.readLine()
        var line: String?
        while (buffer.readLine().also { line = it } != null) {
            parser.parseTotalColumns(line!!)
        }
        buffer.close()

        return parser.getMeals()
    }
}
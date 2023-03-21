package com.cheesecake.chickenmasala.model

enum class RangeTimeInMinutes(val from: Int, val to: Int) {
    FIVE_TO_TEN(5, 10), TEN_TO_FIFTEEN(10, 15), FIFTEEN_TO_THIRTY(15, 30), THIRTY_TO_HOUR(30, 60)
}
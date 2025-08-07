package io.github.gyeyangcat.meal

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main()  {
    try {
        val lunch = getMyLunch("20240902")
        println(lunch)
        val currentDate = LocalDate.of(2024, 9, 2)
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
        createImg(lunch, formattedDate)
    } catch (ex: IllegalStateException) {
        println(ex)
        println("Seems like meal not found!")
    }
}
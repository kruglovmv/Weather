package com.example.weather.model

import java.util.concurrent.locks.Condition


data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 15,
    val feelsLike: Int = 0,
    val windSpeed:Byte = 1,
    val windDir:String = "n",
    val condition:String = "cloudy "
) {
    fun toString(temperature: Int): String {
        if(temperature>0) return "+$temperature"
        else if(temperature<0) return "-$temperature"
        else return "$temperature"
    }
}

fun getDefaultCity() = City("Москва", 55.755826, 37.617299900000035)

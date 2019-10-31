package ru.ifmo.ibragimov.weatherretrofit

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

data class Metric(@Json(name = "Value") val value: Double)
data class Temperature(
    @Json(name = "Minimum") val minimum: Metric,
    @Json(name = "Maximum") val maximum: Metric
)

data class Day(
    @Json(name = "Icon") val icon: Int,
    @Json(name = "IconPhrase") val iconPhrase: String
)

data class DailyForecast(
    @Json(name = "Date") val date: Date,
    @Json(name = "EpochDate") val epochDate: Long,
    @Json(name = "Temperature") val temperature: Temperature,
    @Json(name = "Day") val day: Day
) {
    fun getTemperature(): Int {
        return ((temperature.maximum.value + temperature.minimum.value) / 2).toInt()
    }
}

data class Weather(@Json(name = "DailyForecasts") val dailyForecasts: List<DailyForecast>)

interface WeatherApi {
    @GET("/forecasts/v1/daily/5day/295212")
    fun getWeatherForecast(
        @Query("apikey") apiKey: String, @Query("language") language: String, @Query(
            "metric"
        ) metric: Boolean
    ): Call<Weather>
}

fun createWeatherApi(): WeatherApi {
    val client = OkHttpClient()
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()
    val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://dataservice.accuweather.com/")
        .build()
    return retrofit.create(WeatherApi::class.java)
}
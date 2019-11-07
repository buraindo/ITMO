package ru.ifmo.ibragimov.weatherretrofitroom.api

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.parcel.Parcelize
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

@Parcelize
data class Metric(@Json(name = "Value") val value: Double) : Parcelable

@Parcelize
data class Temperature(
    @Json(name = "Minimum") val minimum: Metric,
    @Json(name = "Maximum") val maximum: Metric
): Parcelable

@Parcelize
data class Day(
    @Json(name = "Icon") val icon: Int,
    @Json(name = "IconPhrase") val iconPhrase: String
) : Parcelable

@Parcelize
data class DailyForecast(
    @Json(name = "Date") val date: Date,
    @Json(name = "EpochDate") val epochDate: Long,
    @Json(name = "Temperature") val temperature: Temperature,
    @Json(name = "Day") val day: Day
) : Parcelable {
    fun getTemperature(): Int {
        return ((temperature.maximum.value + temperature.minimum.value) / 2).toInt()
    }
}

@Parcelize
data class Weather(@Json(name = "DailyForecasts") val dailyForecasts: List<DailyForecast>) :
    Parcelable

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
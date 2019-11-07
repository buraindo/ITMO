package ru.ifmo.ibragimov.weatherretrofitroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.ifmo.ibragimov.weatherretrofitroom.domain.DailyForecast
import ru.ifmo.ibragimov.weatherretrofitroom.domain.Weather

@Dao
abstract class WeatherDao {
    @Query("select count(*) from weather")
    abstract fun count(): Long

    @Query("select * from weather order by id desc limit 1")
    abstract fun getLastWeather(): Weather?

    @Query("select * from dailyforecast where weather_id = :id")
    abstract fun getForecastByWeatherId(id: Long): List<DailyForecast>

    @Insert
    abstract fun addForecast(forecast: DailyForecast)

    @Insert
    abstract fun addWeather(weather: Weather)

    fun addWeatherAndForecast(weather: Weather) {
        addWeather(weather)
        val id = getLastWeather()!!.id
        for (forecast in weather.dailyForecasts) {
            forecast.weatherId = id
            addForecast(forecast)
        }
    }

    fun getLast(): Weather? {
        val weather = getLastWeather() ?: return null
        val dailyForecasts = getForecastByWeatherId(weather.id)
        weather.dailyForecasts = dailyForecasts
        return weather
    }
}
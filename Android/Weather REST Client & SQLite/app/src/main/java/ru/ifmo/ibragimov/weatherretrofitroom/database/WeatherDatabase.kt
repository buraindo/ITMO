package ru.ifmo.ibragimov.weatherretrofitroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ifmo.ibragimov.weatherretrofitroom.dao.WeatherDao
import ru.ifmo.ibragimov.weatherretrofitroom.domain.DailyForecast
import ru.ifmo.ibragimov.weatherretrofitroom.domain.Weather

@Database(version = 2, entities = [Weather::class, DailyForecast::class])
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}
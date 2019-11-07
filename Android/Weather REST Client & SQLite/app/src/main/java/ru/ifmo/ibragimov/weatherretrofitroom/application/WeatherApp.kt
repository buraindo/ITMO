package ru.ifmo.ibragimov.weatherretrofitroom.application

import android.app.Application
import ru.ifmo.ibragimov.weatherretrofitroom.api.WeatherApi
import ru.ifmo.ibragimov.weatherretrofitroom.api.createWeatherApi

class WeatherApp : Application() {
    lateinit var weatherApi: WeatherApi
        private set

    override fun onCreate() {
        super.onCreate()
        val api = createWeatherApi()
        weatherApi = api
        app = this
    }

    companion object {
        lateinit var app: WeatherApp
            private set
    }
}
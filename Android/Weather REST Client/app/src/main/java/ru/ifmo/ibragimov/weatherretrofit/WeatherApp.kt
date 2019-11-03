package ru.ifmo.ibragimov.weatherretrofit

import android.app.Application

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
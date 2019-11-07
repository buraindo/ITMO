package ru.ifmo.ibragimov.weatherretrofitroom.service

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.room.Room
import ru.ifmo.ibragimov.weatherretrofitroom.MainActivity.Companion.TYPE_ADD
import ru.ifmo.ibragimov.weatherretrofitroom.MainActivity.Companion.TYPE_FETCH
import ru.ifmo.ibragimov.weatherretrofitroom.R
import ru.ifmo.ibragimov.weatherretrofitroom.api.Weather
import ru.ifmo.ibragimov.weatherretrofitroom.converter.from
import ru.ifmo.ibragimov.weatherretrofitroom.database.WeatherDatabase

class DatabaseIntentService :
    IntentService(DatabaseIntentService::class.simpleName) {

    private lateinit var weatherDatabase: WeatherDatabase

    override fun onCreate() {
        super.onCreate()
        weatherDatabase =
            Room.databaseBuilder(applicationContext, WeatherDatabase::class.java, "weather-db")
                .fallbackToDestructiveMigration()
                .build()
    }

    override fun onHandleIntent(intent: Intent?) {
        val response = Intent(getString(R.string.action))
        when (intent?.getIntExtra("type", -1)) {
            TYPE_ADD -> addToDatabase(intent.getParcelableExtra("weather")!!)
            TYPE_FETCH -> response.putExtra("result", fetchFromDatabase())
        }
        response.putExtra("type", intent?.getIntExtra("type", -1))
        LocalBroadcastManager.getInstance(this).sendBroadcast(response)
    }

    private fun addToDatabase(weather: Weather) {
        weatherDatabase.weatherDao.addWeatherAndForecast(
            ru.ifmo.ibragimov.weatherretrofitroom.converter.to(
                weather
            )
        )
    }

    private fun fetchFromDatabase(): Weather? {
        val last = weatherDatabase.weatherDao.getLast() ?: return null
        return from(last)
    }
}
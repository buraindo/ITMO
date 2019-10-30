package ru.ifmo.ibragimov.weatherretrofit

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private const val LOG_TAG = "Weather API"

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences =
            applicationContext.getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE)
        setTheme(
            if (preferences.getBoolean(
                    getString(R.string.dark),
                    false
                )
            ) R.style.DarkTheme else R.style.LightTheme
        )
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        run()
    }

    private var call: Call<Weather>? = null

    private fun run() {
        call = WeatherApp.app.weatherApi.getWeatherForecast()
        call?.enqueue(object : Callback<Weather> {
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.e(LOG_TAG, "Failed with", t)
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                val weather = response.body()!!
                val forecast = weather.dailyForecasts
                temperature.text =
                    getString(R.string.temperature).format(forecast[0].getTemperature())
                status.text = forecast[0].day.iconPhrase
                big.setImageDrawable(
                    getDrawable(
                        resources.getIdentifier(
                            "ic_${forecast[0].day.icon}", "drawable",
                            applicationContext.packageName
                        )
                    )
                )
                for ((i, day) in forecast.withIndex()) {
                    findViewById<TextView>(
                        resources.getIdentifier(
                            "day${i}", "id",
                            applicationContext.packageName
                        )
                    ).text = SimpleDateFormat("EE", Locale("ru")).format(day.date)
                    findViewById<ImageView>(
                        resources.getIdentifier(
                            "img${i}", "id",
                            applicationContext.packageName
                        )
                    ).setImageDrawable(
                        getDrawable(
                            resources.getIdentifier(
                                "ic_${day.day.icon}", "drawable",
                                applicationContext.packageName
                            )
                        )
                    )
                    findViewById<TextView>(
                        resources.getIdentifier(
                            "temp${i}", "id",
                            applicationContext.packageName
                        )
                    ).text = getString(R.string.temperature).format(day.getTemperature())
                }
            }
        })
    }

    fun toggle(item: MenuItem) {
        if (item.itemId == R.id.mode) {
            val editor = preferences.edit()
            editor.putBoolean(
                getString(R.string.dark),
                !preferences.getBoolean(getString(R.string.dark), false)
            )
            editor.apply()
            recreate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        call?.cancel()
        call = null
    }
}

package ru.ifmo.ibragimov.weatherretrofit

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather.*
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
        if (savedInstanceState == null) {
            run()
        } else innerSwitcher.showNext()
        setupRefresh()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("temperature", temperature.text)
        outState.putCharSequence("status", status.text)
        outState.putInt("big", big.tag as Int)
        outState.putCharSequenceArrayList(
            "daysOfWeek", arrayListOf(
                day0.text,
                day1.text,
                day2.text,
                day3.text,
                day4.text
            )
        )
        outState.putIntegerArrayList(
            "icons", arrayListOf(
                img0.tag as Int,
                img1.tag as Int,
                img2.tag as Int,
                img3.tag as Int,
                img4.tag as Int
            )
        )
        outState.putCharSequenceArrayList(
            "temperatures", arrayListOf(
                temp0.text,
                temp1.text,
                temp2.text,
                temp3.text,
                temp4.text
            )
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        temperature.text = savedInstanceState.getCharSequence("temperature")
        status.text = savedInstanceState.getCharSequence("status")
        big.setImageDrawable(
            getDrawable(
                resources.getIdentifier(
                    "ic_${savedInstanceState.getInt("big")}", "drawable",
                    applicationContext.packageName
                )
            )
        )
        big.tag = savedInstanceState.getInt("big")
        for ((i, day) in savedInstanceState.getCharSequenceArrayList("daysOfWeek")!!.withIndex()) {
            findViewById<TextView>(
                resources.getIdentifier(
                    "day${i}", "id",
                    applicationContext.packageName
                )
            ).text = day
        }
        for ((i, icon) in savedInstanceState.getIntegerArrayList("icons")!!.withIndex()) {
            findViewById<ImageView>(
                resources.getIdentifier(
                    "img${i}", "id",
                    applicationContext.packageName
                )
            ).apply {
                setImageDrawable(
                    getDrawable(
                        resources.getIdentifier(
                            "ic_${icon}", "drawable",
                            applicationContext.packageName
                        )
                    )
                )
                tag = icon
            }
        }
        for ((i, temp) in savedInstanceState.getCharSequenceArrayList("temperatures")!!.withIndex()) {
            findViewById<TextView>(
                resources.getIdentifier(
                    "temp${i}", "id",
                    applicationContext.packageName
                )
            ).text = temp
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        call?.cancel()
        call = null
    }

    private var call: Call<Weather>? = null

    private fun run() {
        call = WeatherApp.app.weatherApi.getWeatherForecast(
            BuildConfig.API_KEY,
            "ru-RU",
            true
        )
        call?.enqueue(object : Callback<Weather> {
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.e(LOG_TAG, "Failed with", t)
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                val weather = response.body()
                if (innerSwitcher.currentView == progressBar) {
                    innerSwitcher.showNext()
                }
                if (weather == null) {
                    switcher.showNext()
                    return
                }
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
                big.tag = forecast[0].day.icon
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
                    ).apply {
                        setImageDrawable(
                            getDrawable(
                                resources.getIdentifier(
                                    "ic_${day.day.icon}", "drawable",
                                    applicationContext.packageName
                                )
                            )
                        )
                        tag = day.day.icon
                    }
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

    private fun setupRefresh() {
        refresh.setOnRefreshListener {
            run()
            Handler().postDelayed({ refresh.isRefreshing = false }, 500)
        }
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
}

package ru.ifmo.ibragimov.weatherretrofitroom

import android.content.*
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.ifmo.ibragimov.weatherretrofitroom.api.Weather
import ru.ifmo.ibragimov.weatherretrofitroom.application.WeatherApp
import ru.ifmo.ibragimov.weatherretrofitroom.service.DatabaseIntentService
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private lateinit var weatherReceiver: BroadcastReceiver

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
        setReceiver()
        supportActionBar?.hide()
        if (savedInstanceState == null) {
            run()
        } else switcher.showNext()
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
                Log.e("WEATHER_API", "Failed with $t")
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                val weather = response.body()
                if (switcher.currentView == progressBar) {
                    switcher.showNext()
                }
                if (weather == null) {
                    return
                }
                addToDatabase(weather)
                setWeatherView(weather)
            }
        })
    }

    private fun setWeatherView(weather: Weather) {
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

    private fun setupRefresh() {
        refresh.setOnRefreshListener {
            run()
            Handler().postDelayed({ refresh.isRefreshing = false }, 500)
        }
    }

    private fun setReceiver() {
        weatherReceiver = WeatherReceiver()
        val intentFilter = IntentFilter().apply {
            addAction(getString(R.string.action))
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(weatherReceiver, intentFilter)
        fetchFromDatabase()
    }

    private fun addToDatabase(weather: Weather) {
        val intent = Intent().apply {
            setClass(applicationContext, DatabaseIntentService::class.java)
            putExtra("weather", weather)
            putExtra("type", TYPE_ADD)
        }
        startService(intent)
    }

    private fun fetchFromDatabase() {
        val intent = Intent().apply {
            setClass(applicationContext, DatabaseIntentService::class.java)
            putExtra("type", TYPE_FETCH)
        }
        startService(intent)
    }

    private inner class WeatherReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.getIntExtra("type", -1) == TYPE_FETCH) {
                if (switcher.currentView == progressBar) {
                    Handler().postDelayed({ switcher.showNext() }, 500)
                }
                setWeatherView(intent.getParcelableExtra("result")!!)
            }
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

    companion object {
        const val TYPE_ADD = 0
        const val TYPE_FETCH = 1
    }
}

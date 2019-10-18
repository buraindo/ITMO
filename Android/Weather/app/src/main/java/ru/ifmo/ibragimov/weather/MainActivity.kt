package ru.ifmo.ibragimov.weather

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
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
    }

    fun toggle(item: MenuItem) {
        val editor = preferences.edit()
        editor.putBoolean(
            getString(R.string.dark),
            !preferences.getBoolean(getString(R.string.dark), false)
        )
        editor.apply()
        recreate()
    }

}
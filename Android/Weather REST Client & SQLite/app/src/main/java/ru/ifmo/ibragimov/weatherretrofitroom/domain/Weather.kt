package ru.ifmo.ibragimov.weatherretrofitroom.domain

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @Ignore var dailyForecasts: List<DailyForecast>
) {
    constructor() : this(0, emptyList())
}

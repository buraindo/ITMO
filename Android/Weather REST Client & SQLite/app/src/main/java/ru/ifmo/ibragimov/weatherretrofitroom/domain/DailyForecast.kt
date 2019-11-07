package ru.ifmo.ibragimov.weatherretrofitroom.domain

import androidx.room.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = Weather::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("weather_id")
    )]
)
data class DailyForecast(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "weather_id") var weatherId: Long,
    @ColumnInfo(name = "epoch_date") val epochDate: Long,
    @Embedded val temperature: Temperature,
    @Embedded val day: Day
)
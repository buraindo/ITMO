package ru.ifmo.ibragimov.weatherretrofitroom.domain

import androidx.room.ColumnInfo

data class Day(
    @ColumnInfo(name = "icon") val icon: Int,
    @ColumnInfo(name = "icon_phrase") val iconPhrase: String
)
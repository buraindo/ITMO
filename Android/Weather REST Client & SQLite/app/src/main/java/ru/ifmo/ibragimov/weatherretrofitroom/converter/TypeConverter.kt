package ru.ifmo.ibragimov.weatherretrofitroom.converter

import ru.ifmo.ibragimov.weatherretrofitroom.api.Metric
import java.util.*
import ru.ifmo.ibragimov.weatherretrofitroom.api.Weather as ApiWeather
import ru.ifmo.ibragimov.weatherretrofitroom.api.DailyForecast as ApiDailyForecast
import ru.ifmo.ibragimov.weatherretrofitroom.api.Day as ApiDay
import ru.ifmo.ibragimov.weatherretrofitroom.api.Temperature as ApiTemperature
import ru.ifmo.ibragimov.weatherretrofitroom.domain.DailyForecast as DomainDailyForecast
import ru.ifmo.ibragimov.weatherretrofitroom.domain.Day as DomainDay
import ru.ifmo.ibragimov.weatherretrofitroom.domain.Temperature as DomainTemperature
import ru.ifmo.ibragimov.weatherretrofitroom.domain.Weather as DomainWeather

fun to(from: ApiWeather): DomainWeather {
    return DomainWeather(
        0, from.dailyForecasts.map { to(it) }
    )
}

fun to(from: ApiDailyForecast): DomainDailyForecast {
    return DomainDailyForecast(
        0, 0, from.epochDate,
        to(from.temperature),
        to(from.day)
    )
}

fun to(from: ApiTemperature): DomainTemperature {
    return DomainTemperature(from.minimum.value, from.maximum.value)
}

fun to(from: ApiDay): DomainDay {
    return DomainDay(from.icon, from.iconPhrase)
}

fun from(from: DomainWeather): ApiWeather {
    return ApiWeather(
        from.dailyForecasts.map { from(it) }
    )
}

fun from(from: DomainDailyForecast): ApiDailyForecast {
    return ApiDailyForecast(
        Date(from.epochDate * 1000), from.epochDate,
        from(from.temperature),
        from(from.day)
    )
}

fun from(from: DomainTemperature): ApiTemperature {
    return ApiTemperature(Metric(from.minimum), Metric(from.maximum))
}

fun from(to: DomainDay): ApiDay {
    return ApiDay(to.icon, to.iconPhrase)
}


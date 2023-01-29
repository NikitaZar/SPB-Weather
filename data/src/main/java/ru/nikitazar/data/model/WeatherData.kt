package ru.nikitazar.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherData(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: MainData,
    val visibility: Float,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
) {
    fun toDomain() = WeatherDataDomain(
        coord = coord.toDomain(),
        weather = weather.toDomain(),
        base = base,
        main = main.toDomain(),
        visibility = visibility,
        wind = wind.toDomain(),
        clouds = clouds.toDomain(),
        dt = dt * 1000,
        sys = sys.toDomain(),
        timezone = timezone,
        id = id,
        name = name,
    )

    private fun List<Weather>.toDomain() = map { it.toDomain() }
}


data class Coord(
    val lon: Float,
    val lat: Float,
) {
    fun toDomain() = CoordDomain(
        lon = lon,
        lat = lat,
    )
}

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
) {
    fun toDomain() = WeatherDomain(
        id = id,
        main = main,
        description = description,
        icon = icon,
    )
}

private const val KELVIN_COEFFICIENT = 272.1f

data class MainData(
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLike: Float,
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("temp_max")
    val tempMax: Float,
    val pressure: Float,
    val humidity: Float,
) {
    fun toDomain() = MainDataDomain(
        temp = temp / KELVIN_COEFFICIENT,
        feelsLike = feelsLike / KELVIN_COEFFICIENT,
        tempMin = tempMin / KELVIN_COEFFICIENT,
        tempMax = tempMax / KELVIN_COEFFICIENT,
        pressure = pressure,
        humidity = humidity
    )
}

data class Wind(
    val speed: Float,
    val deg: Float,
) {
    fun toDomain() = WindDomain(
        speed = speed,
        deg = deg,
    )
}

data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
) {
    fun toDomain() = SysDomain(
        type = type,
        id = id,
        country = country,
        sunrise = sunrise * 1000,
        sunset = sunset * 1000,
    )
}

data class Clouds(
    val all: Int,
) {
    fun toDomain() = CloudsDomain(all)
}





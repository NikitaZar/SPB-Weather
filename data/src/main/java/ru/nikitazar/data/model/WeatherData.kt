package ru.nikitazar.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: MainData,
    val visibility: Float,
    val wind: Wind,
    val dt: Int,
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
        dt = dt,
        sys = sys.toDomain(),
        timezone = timezone,
        id = id,
        name = name,
    )
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
    val math: String,
    val description: String,
    val icon: String,
) {
    fun toDomain() = WeatherDomain(
        id = id,
        math = math,
        description = description,
        icon = icon,
    )
}

fun List<Weather>.toDomain() = map { it.toDomain() }

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
    val sunrise: Int,
    val sunset: Int,
) {
    fun toDomain() = SysDomain(
        type = type,
        id = id,
        country = country,
        sunrise = sunrise,
        sunset = sunset,
    )
}





package ru.nikitazar.domain.model

data class WeatherDataDomain(
    val coord: CoordDomain,
    val weather: List<WeatherDomain>,
    val base: String,
    val main: MainDataDomain,
    val visibility: Float,
    val wind: WindDomain,
    val clouds: CloudsDomain,
    val dt: Long,
    val sys: SysDomain,
    val timezone: Int,
    val id: Int,
    val name: String,
)

data class CoordDomain(
    val lon: Float,
    val lat: Float,
)

data class WeatherDomain(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

data class MainDataDomain(
    val temp: Float,
    val feelsLike: Float,
    val tempMin: Float,
    val tempMax: Float,
    val pressure: Float,
    val humidity: Float,
)

data class WindDomain(
    val speed: Float,
    val deg: Float,
)

data class SysDomain(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)

data class CloudsDomain(
    val all: Int,
)





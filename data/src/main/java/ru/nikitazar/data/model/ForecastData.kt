package ru.nikitazar.data.model

import com.google.gson.annotations.SerializedName
import ru.nikitazar.domain.model.*

data class ForecastData(
    val code: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastDataItem>
) {
    fun toDomain() = ForecastDataDomain(
        list = list.toDomain()
    )
}

fun List<ForecastDataItem>.toDomain() = map { it.toDomain() }

private const val KELVIN_COEFFICIENT = 272.1f

data class ForecastDataItem(
    val dt: Long,
    val main: ForecastMainData,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: ForecastWind,
    val visibility: Int,
    val pop: Float,
    val sys: ForecastSys,
    @SerializedName("dt_txt")
    val dtText: String
) {
    fun toDomain() = ForecastDataItemDomain(
        dt = dt * 1000,
        temp = main.temp / KELVIN_COEFFICIENT
    )
}


data class ForecastMainData(
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLike: Float,
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("temp_max")
    val tempMax: Float,
    val pressure: Float,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("grnd_level")
    val grndLevel: Int,
    val humidity: Float,
    @SerializedName("temp_kf")
    val tempKf: Float,
)

data class ForecastWind(
    val speed: Float,
    val deg: Float,
    val gust: Float,
)

data class ForecastSys(
    val pod: String,
)
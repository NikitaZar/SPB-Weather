package ru.nikitazar.domain.model

data class ForecastDataDomain(
    val list: List<ForecastDataItemDomain>
)

data class ForecastDataItemDomain(
    val dt: Long,
    val temp: Float,
)
package ru.nikitazar.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.nikitazar.domain.model.WeatherDataDomain

interface WeatherRepository {
    suspend fun getWeather(): Flow<WeatherDataDomain>
}
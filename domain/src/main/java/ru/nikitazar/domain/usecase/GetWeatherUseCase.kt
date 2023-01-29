package ru.nikitazar.domain.usecase

import android.util.Log
import ru.nikitazar.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend fun execute() = repository.getWeather()
}


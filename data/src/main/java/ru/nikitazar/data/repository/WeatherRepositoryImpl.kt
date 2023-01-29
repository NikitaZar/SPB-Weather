package ru.nikitazar.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.nikitazar.data.api.ApiService
import ru.nikitazar.data.error.ApiError
import ru.nikitazar.data.error.NetworkError
import ru.nikitazar.data.error.UnknownError
import ru.nikitazar.domain.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

private const val CITY = "Sankt-Peterburg"
private const val APP_ID = "d9e6fe2ca9bd114df14262b014663852"

@Singleton
class WeatherRepositoryImpl @Inject constructor(private val api: ApiService) : WeatherRepository {

    override suspend fun getWeather() = flow {
        try {
            val response = api.getWeather(city = CITY, appId = APP_ID)
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            emit(body.toDomain())
        } catch (e: ApiError) {
            throw e
        } catch (e: NetworkError) {
            throw e
        }
    }.flowOn(Dispatchers.IO)
}
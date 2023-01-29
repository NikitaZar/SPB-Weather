package ru.nikitazar.data.api

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikitazar.data.BuildConfig
import ru.nikitazar.data.model.ForecastData
import ru.nikitazar.domain.model.ForecastDataDomain
import ru.nikitazar.domain.model.WeatherData

interface ApiService {
    @GET("weather?")
    suspend fun getWeather(@Query("q") city: String, @Query("appid") appId: String): Response<WeatherData>

    @GET("forecast?")
    suspend fun getForecast(@Query("q") city: String, @Query("appid") appId: String): Response<ForecastData>
}

fun okhttp(): OkHttpClient = OkHttpClient.Builder().build()

fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BuildConfig.BASE_URL)
    .client(client)
    .build()
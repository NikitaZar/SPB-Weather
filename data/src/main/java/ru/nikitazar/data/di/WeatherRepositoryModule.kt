package ru.nikitazar.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikitazar.data.repository.WeatherRepositoryImpl
import ru.nikitazar.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(imp: WeatherRepositoryImpl): WeatherRepository
}
package ru.nikitazar.spbweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CalendarModule {
    @Provides
    @Singleton
    fun provideCalendar(): Calendar = Calendar.getInstance()
}
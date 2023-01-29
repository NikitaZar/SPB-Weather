package ru.nikitazar.spbweather.viewModel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.nikitazar.domain.model.*
import ru.nikitazar.domain.usecase.GetWeatherUseCase
import javax.inject.Inject

private val empty = WeatherDataDomain(
    coord = CoordDomain(-100f, -100f),
    weather = emptyList(),
    base = "",
    main = MainDataDomain(-100f, -100f, -100f, -100f, -100f, -100f),
    visibility = -100f,
    wind = WindDomain(-100f, -100f),
    clouds = CloudsDomain(-100),
    dt = -100,
    sys = SysDomain(0, 0, "", -100, -100),
    timezone = 0,
    id = 0,
    name = ""
)

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _dataWeather = MutableLiveData(empty)

    @OptIn(ExperimentalCoroutinesApi::class)
    val dataWeather: LiveData<WeatherDataDomain>
        get() = _dataWeather
            .asFlow()
            .flatMapLatest { getWeatherUseCase.execute() }
            .asLiveData(Dispatchers.Default)
}
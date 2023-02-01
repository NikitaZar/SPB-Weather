package ru.nikitazar.spbweather.viewModel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.nikitazar.data.error.ApiError
import ru.nikitazar.data.error.NetworkError
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

const val REQ_ERR = 400
const val NW_ERR = 1
const val NO_ERR = 0

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _dataWeather = MutableLiveData(empty)

    val errState: LiveData<Int>
        get() = _errState
    private val _errState = MutableLiveData(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val dataWeather: LiveData<WeatherDataDomain>
        get() = _dataWeather
            .asFlow()
            .flatMapLatest {
                getWeatherUseCase.execute()
            }
            .catch { e ->
                when (e) {
                    is NetworkError -> _errState.postValue(NW_ERR)
                    is ApiError -> _errState.postValue(REQ_ERR)
                    is UnknownError -> _errState.postValue(REQ_ERR)
                }
            }
            .asLiveData(Dispatchers.Default)
}
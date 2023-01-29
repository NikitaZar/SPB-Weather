package ru.nikitazar.spbweather.viewModel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import ru.nikitazar.domain.model.*
import ru.nikitazar.domain.usecase.GetForecastUseCase
import javax.inject.Inject

private val empty = ForecastDataDomain(
    list = emptyList()
)

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _dataForecast = MutableLiveData(empty)

    @OptIn(ExperimentalCoroutinesApi::class)
    val dataForecast: LiveData<ForecastDataDomain>
        get() = _dataForecast
            .asFlow()
            .flatMapLatest { getForecastUseCase.execute() }
            .asLiveData(Dispatchers.Default)
}
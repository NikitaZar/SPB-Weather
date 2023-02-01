package ru.nikitazar.spbweather.viewModel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import ru.nikitazar.data.error.ApiError
import ru.nikitazar.data.error.NetworkError
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

    val errState: LiveData<Int>
        get() = _errState
    private val _errState = MutableLiveData(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val dataForecast: LiveData<ForecastDataDomain>
        get() = _dataForecast
            .asFlow()
            .flatMapLatest {
                getForecastUseCase.execute()
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
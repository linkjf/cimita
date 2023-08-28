package com.linkjf.climita.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkjf.climita.domain.interactor.ForecastUseCase
import com.linkjf.climita.domain.interactor.LocationSearchUseCase
import com.linkjf.climita.domain.models.Forecast
import com.linkjf.climita.domain.models.Location
import com.linkjf.climita.remote.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MIN_QUERY_LENGTH = 3

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val locationSearchUseCase: LocationSearchUseCase,
    private val forecastUseCase: ForecastUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _showError = MutableLiveData(false)
    val showError: LiveData<Boolean> get() = _showError

    private val _showEmpty = MutableLiveData(false)
    val showEmpty: LiveData<Boolean> get() = _showEmpty

    private val _query = MutableLiveData("")
    val query: LiveData<String> get() = _query

    private val _forecast = MutableLiveData<Forecast>()
    val forecast: LiveData<Forecast> get() = _forecast

    private var _locationPredictions = MutableLiveData<List<Location>>()
    val locationPredictions: LiveData<List<Location>> get() = _locationPredictions

    fun getLocationPredictions(query: String) {
        _query.value = query
        _isLoading.postValue(true)
        if (query.isEmpty() || query.length < MIN_QUERY_LENGTH) {
            _isLoading.postValue(false)
            _showEmpty.postValue(false)
            _locationPredictions.postValue(emptyList())
            return
        }

        viewModelScope.launch {
            locationSearchUseCase(query).collect { locations ->
                _isLoading.postValue(false)
                _showError.postValue(false)
                _showEmpty.postValue(false)

                when (locations) {
                    is Result.Success -> {
                        if (locations.data.isEmpty())
                            _showEmpty.postValue(true)
                        _locationPredictions.postValue(locations.data)
                    }

                    is Result.Error -> _showError.postValue(true)
                    is Result.Exception -> _showError.postValue(true)

                }
            }
        }
    }

    fun clear() {
        _query.postValue("")
        _locationPredictions.postValue(emptyList())
    }
}

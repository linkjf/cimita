package com.linkjf.climita.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkjf.climita.domain.interactor.LocationSearchUseCase
import com.linkjf.climita.domain.models.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val useCase: LocationSearchUseCase
) : ViewModel() {

    private val _query = MutableLiveData("")
    val query: LiveData<String> get() = _query

    private var _locationPredictions = MutableLiveData<List<Location>>()
    val locationPredictions: LiveData<List<Location>> get() = _locationPredictions

    fun getLocationPredictions(query: String) {
        _query.value = query
        viewModelScope.launch {
            useCase(query).collect {
                _locationPredictions.postValue(it)
            }
        }

    }
}
